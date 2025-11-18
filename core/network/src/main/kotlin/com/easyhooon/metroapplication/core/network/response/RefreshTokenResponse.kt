package com.easyhooon.metroapplication.core.network.response

import kotlinx.serialization.Serializable

@Serializable
data class RefreshTokenResponse(
    val accessToken: String,
    val refreshToken: String,
)