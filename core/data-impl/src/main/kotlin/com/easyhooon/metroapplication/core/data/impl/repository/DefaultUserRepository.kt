package com.easyhooon.metroapplication.core.data.impl.repository

import com.easyhooon.metroapplication.core.data.api.repository.UserRepository
import com.easyhooon.metroapplication.core.di.DataScope
import dev.zacsweers.metro.Inject
import dev.zacsweers.metro.SingleIn

@SingleIn(DataScope::class)
@Inject
class DefaultUserRepository : UserRepository {
    override suspend fun getUserProfile(): Result<String> {
        return Result.success("User Profile")
    }
}