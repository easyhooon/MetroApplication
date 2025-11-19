package com.easyhooon.metroapplication.core.datastore.api.datasource

interface TokenDataSource {
    suspend fun getAccessToken(): String
    suspend fun setAccessToken(token: String)
    suspend fun getRefreshToken(): String
    suspend fun setRefreshToken(token: String)
    suspend fun clearTokens()
}