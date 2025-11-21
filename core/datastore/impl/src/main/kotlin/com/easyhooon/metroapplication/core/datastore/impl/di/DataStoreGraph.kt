package com.easyhooon.metroapplication.core.datastore.impl.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.easyhooon.metroapplication.core.datastore.api.datasource.NotificationDataSource
import com.easyhooon.metroapplication.core.datastore.impl.datasource.DefaultNotificationDataSource
import com.easyhooon.metroapplication.core.di.ApplicationContext
import com.easyhooon.metroapplication.core.di.DataScope
import dev.zacsweers.metro.Binds
import dev.zacsweers.metro.ContributesTo
import dev.zacsweers.metro.Provides

private const val NOTIFICATION_DATASTORE_NAME = "NOTIFICATION_DATASTORE"

private val Context.notificationDataStore by preferencesDataStore(name = NOTIFICATION_DATASTORE_NAME)

@ContributesTo(DataScope::class)
interface DataStoreGraph {

    @NotificationDataStore
    @Provides
    fun provideNotificationDataStore(
        @ApplicationContext context: Context,
    ): DataStore<Preferences> = context.notificationDataStore

    @Binds
    val DefaultNotificationDataSource.bind: NotificationDataSource
}
