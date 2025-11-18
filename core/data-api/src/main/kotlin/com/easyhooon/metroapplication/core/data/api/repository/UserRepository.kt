package com.easyhooon.metroapplication.core.data.api.repository

interface UserRepository {
    suspend fun getUserProfile(): Result<String>
}