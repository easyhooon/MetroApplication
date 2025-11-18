package com.easyhooon.metroapplication.core.network

import com.easyhooon.metroapplication.core.datastore.api.datasource.TokenDataSource
import com.easyhooon.metroapplication.core.di.DataScope
import dev.zacsweers.metro.Inject
import dev.zacsweers.metro.SingleIn
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response

@SingleIn(DataScope::class)
@Inject
class TokenInterceptor(
    private val tokenDataSource: TokenDataSource,
) : Interceptor {

    private val noAuthEndpoints = setOf(
        "api/v1/auth/signin",
        "api/v1/auth/refresh",
    )

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val url = request.url.toString()

        val isNoAuthEndpoint = noAuthEndpoints.any { url.contains(it) }

        return if (isNoAuthEndpoint) {
            chain.proceed(request)
        } else {
            val accessToken = runBlocking { tokenDataSource.getAccessToken() }
            chain.proceed(
                request.newBuilder()
                    .addHeader("Authorization", "Bearer $accessToken")
                    .build(),
            )
        }
    }
}