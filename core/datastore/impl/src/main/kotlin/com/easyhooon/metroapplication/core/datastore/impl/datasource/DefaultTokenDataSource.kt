package com.easyhooon.metroapplication.core.datastore.impl.datasource

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.easyhooon.metroapplication.core.common.crypto.CryptoManager
import com.easyhooon.metroapplication.core.datastore.api.datasource.TokenDataSource
import com.easyhooon.metroapplication.core.datastore.impl.di.TokenDataStore
import com.easyhooon.metroapplication.core.di.DataScope
import dev.zacsweers.metro.Inject
import dev.zacsweers.metro.SingleIn
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

@SingleIn(DataScope::class)
@Inject
class DefaultTokenDataSource(
    @TokenDataStore private val dataStore: DataStore<Preferences>,
    private val cryptoManager: CryptoManager,
) : TokenDataSource {

    override suspend fun getAccessToken(): String {
        return dataStore.data.map { preferences ->
            val encrypted = preferences[ACCESS_TOKEN_KEY] ?: ""
            if (encrypted.isEmpty()) "" else cryptoManager.decrypt(encrypted)
        }.first()
    }

    override suspend fun setAccessToken(token: String) {
        dataStore.edit { preferences ->
            preferences[ACCESS_TOKEN_KEY] = cryptoManager.encrypt(token)
        }
    }

    override suspend fun getRefreshToken(): String {
        return dataStore.data.map { preferences ->
            val encrypted = preferences[REFRESH_TOKEN_KEY] ?: ""
            if (encrypted.isEmpty()) "" else cryptoManager.decrypt(encrypted)
        }.first()
    }

    override suspend fun setRefreshToken(token: String) {
        dataStore.edit { preferences ->
            preferences[REFRESH_TOKEN_KEY] = cryptoManager.encrypt(token)
        }
    }

    override suspend fun clearTokens() {
        dataStore.edit { preferences ->
            preferences.remove(ACCESS_TOKEN_KEY)
            preferences.remove(REFRESH_TOKEN_KEY)
        }
    }

    companion object {
        private val ACCESS_TOKEN_KEY = stringPreferencesKey("access_token")
        private val REFRESH_TOKEN_KEY = stringPreferencesKey("refresh_token")
    }
}