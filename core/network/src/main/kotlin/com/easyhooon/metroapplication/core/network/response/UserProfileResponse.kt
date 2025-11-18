package com.easyhooon.metroapplication.core.network.response

import kotlinx.serialization.Serializable

@Serializable
data class UserProfileResponse(
    val userId: String,
    val name: String,
    val termsAgreed: Boolean,
)