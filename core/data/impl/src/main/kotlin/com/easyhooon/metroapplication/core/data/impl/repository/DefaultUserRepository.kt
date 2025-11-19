package com.easyhooon.metroapplication.core.data.impl.repository

import com.easyhooon.metroapplication.core.data.api.repository.UserRepository
import com.easyhooon.metroapplication.core.datastore.api.datasource.NotificationDataSource
import com.easyhooon.metroapplication.core.di.DataScope
import dev.zacsweers.metro.Inject
import dev.zacsweers.metro.SingleIn

@SingleIn(DataScope::class)
@Inject
class DefaultUserRepository(
    private val notificationDataSource: NotificationDataSource,
) : UserRepository {
    override suspend fun getUserProfile(): Result<String> {
        return Result.success("User Profile")
    }
}