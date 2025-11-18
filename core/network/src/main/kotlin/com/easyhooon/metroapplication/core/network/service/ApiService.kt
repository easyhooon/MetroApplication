package com.easyhooon.metroapplication.core.network.service

import com.easyhooon.metroapplication.core.network.request.RefreshTokenRequest
import com.easyhooon.metroapplication.core.network.response.RefreshTokenResponse
import com.easyhooon.metroapplication.core.network.response.UserProfileResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @POST("api/v1/auth/refresh")
    suspend fun refreshToken(@Body refreshTokenRequest: RefreshTokenRequest): RefreshTokenResponse

    @GET("api/v1/users/me")
    suspend fun getUserProfile(): UserProfileResponse

    @POST("api/v1/auth/signin")
    suspend fun login(@Body loginRequest: Map<String, String>): Map<String, String>
}