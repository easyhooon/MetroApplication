package com.easyhooon.metroapplication.core.data.impl.repository

import com.easyhooon.metroapplication.core.data.api.repository.UserRepository
import com.easyhooon.metroapplication.core.datastore.api.datasource.NotificationDataSource
import com.easyhooon.metroapplication.core.di.DataScope
import dev.zacsweers.metro.Inject
import dev.zacsweers.metro.SingleIn

/**
 * DefaultUserRepository implementation.
 *
 * NOTE: Injecting NotificationDataSource triggers Metro compiler crash.
 * To verify that Repository injection itself works fine,
 * the NotificationDataSource dependency is commented out below.
 *
 * Uncomment the following line to reproduce the Metro bug:
 * private val notificationDataSource: NotificationDataSource,
 */
@SingleIn(DataScope::class)
@Inject
class DefaultUserRepository(
    // Uncomment this line to reproduce Metro bug:
    private val notificationDataSource: NotificationDataSource,
) : UserRepository {
    override suspend fun getUserProfile(): Result<String> {
        return Result.success("User Profile")
    }
}
