package com.easyhooon.metroapplication.core.network

import android.util.Log
import com.easyhooon.metroapplication.core.datastore.api.datasource.TokenDataSource
import com.easyhooon.metroapplication.core.di.DataScope
import com.easyhooon.metroapplication.core.network.request.RefreshTokenRequest
import com.easyhooon.metroapplication.core.network.service.ApiService
import dev.zacsweers.metro.Inject
import dev.zacsweers.metro.SingleIn
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

@SingleIn(DataScope::class)
@Inject
class TokenAuthenticator(
    private val tokenDataSource: TokenDataSource,
    // Provider를 사용하면 매번 새 인스턴스를 가져오게 되는 문제가 있을 수 있음
    // private val serviceProvider: Provider<ApiService>,
    // Lazy를 사용하면 첫 호출 시 한 번만 초기화되고 재사용됨 (순환 의존성 방지)
    private val serviceLazy: Lazy<ApiService>,
) : Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? {
        return runBlocking {
            try {
                val refreshToken = tokenDataSource.getRefreshToken()

                if (refreshToken.isBlank()) {
                    Log.d("TokenAuthenticator", "No refresh token available")
                    tokenDataSource.clearTokens()
                    return@runBlocking null
                }

                val refreshTokenRequest = RefreshTokenRequest(refreshToken)
                // Provider를 사용하는 경우:
                // val refreshResponse = serviceProvider().refreshToken(refreshTokenRequest)

                // Lazy를 사용하는 경우 (Reed 방식):
                val refreshResponse = serviceLazy.value.refreshToken(refreshTokenRequest)

                tokenDataSource.apply {
                    setAccessToken(refreshResponse.accessToken)
                    setRefreshToken(refreshResponse.refreshToken)
                }

                Log.d("TokenAuthenticator", "Token refreshed successfully")

                response.request.newBuilder()
                    .header("Authorization", "Bearer ${refreshResponse.accessToken}")
                    .build()
            } catch (e: Exception) {
                Log.e("TokenAuthenticator", "Token refresh failed: ${e.message}")
                tokenDataSource.clearTokens()

                // refresh token이 만료되었거나 잘못된 경우, 재시도하지 않음
                return@runBlocking null
            }
        }
    }
}