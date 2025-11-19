package com.easyhooon.metroapplication.core.data.impl.repository

import com.easyhooon.metroapplication.core.data.api.repository.AuthRepository
import com.easyhooon.metroapplication.core.di.DataScope
import dev.zacsweers.metro.Inject
import dev.zacsweers.metro.SingleIn

@SingleIn(DataScope::class)
@Inject
class DefaultAuthRepository : AuthRepository {
    override suspend fun login(email: String, password: String): Result<String> {
        return Result.success("auth_token")
    }

    override suspend fun logout(): Result<Unit> {
        return Result.success(Unit)
    }

    override suspend fun refreshToken(): Result<String> {
        return Result.success("new_token")
    }
}
