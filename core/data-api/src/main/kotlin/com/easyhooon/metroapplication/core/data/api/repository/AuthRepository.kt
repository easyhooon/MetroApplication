package com.easyhooon.metroapplication.core.data.api.repository

interface AuthRepository {
    suspend fun login(email: String, password: String): Result<String>
    suspend fun logout(): Result<Unit>
    suspend fun refreshToken(): Result<String>
}
