package com.easyhooon.metroapplication.core.datastore.impl.datasource

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.easyhooon.metroapplication.core.datastore.api.datasource.NotificationDataSource
import com.easyhooon.metroapplication.core.datastore.impl.di.NotificationDataStore
import com.easyhooon.metroapplication.core.di.DataScope
import dev.zacsweers.metro.Inject
import dev.zacsweers.metro.SingleIn
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@SingleIn(DataScope::class)
@Inject
class DefaultNotificationDataSource(
    @NotificationDataStore private val dataStore: DataStore<Preferences>,
) : NotificationDataSource {
    override val fcmToken: Flow<String> = dataStore.data.map { prefs ->
        prefs[FCM_TOKEN] ?: ""
    }

    override suspend fun setFcmToken(fcmToken: String) {
        dataStore.edit { prefs ->
            prefs[FCM_TOKEN] = fcmToken
        }
    }

    override val isUserNotificationEnabled: Flow<Boolean> = dataStore.data.map { prefs ->
        prefs[USER_NOTIFICATION_ENABLED] ?: true
    }

    override suspend fun setUserNotificationEnabled(isEnabled: Boolean) {
        dataStore.edit { prefs ->
            prefs[USER_NOTIFICATION_ENABLED] = isEnabled
        }
    }

    override val lastSyncedNotificationEnabled: Flow<Boolean?> = dataStore.data.map { prefs ->
        prefs[LAST_SYNCED_NOTIFICATION_ENABLED]
    }

    override suspend fun setLastSyncedNotificationEnabled(isEnabled: Boolean) {
        dataStore.edit { prefs ->
            prefs[LAST_SYNCED_NOTIFICATION_ENABLED] = isEnabled
        }
    }

    override suspend fun clearNotificationDataStore() {
        dataStore.edit { prefs ->
            prefs.clear()
        }
    }

    companion object {
        private val FCM_TOKEN = stringPreferencesKey("FCM_TOKEN")
        private val USER_NOTIFICATION_ENABLED = booleanPreferencesKey("USER_NOTIFICATION_ENABLED")
        private val LAST_SYNCED_NOTIFICATION_ENABLED = booleanPreferencesKey("LAST_SYNCED_NOTIFICATION_ENABLED")
    }
}
