package com.easyhooon.metroapplication.core.datastore.impl.datasource

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import com.easyhooon.metroapplication.core.datastore.api.datasource.OnboardingDataSource
import com.easyhooon.metroapplication.core.datastore.impl.di.OnboardingDataStore
import com.easyhooon.metroapplication.core.di.DataScope
import dev.zacsweers.metro.Inject
import dev.zacsweers.metro.SingleIn
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@SingleIn(DataScope::class)
@Inject
class DefaultOnboardingDataSource(
    @OnboardingDataStore private val dataStore: DataStore<Preferences>,
) : OnboardingDataSource {
    override val onboardingState: Flow<Boolean> = dataStore.data.map { prefs ->
        prefs[ONBOARDING_COMPLETED] ?: false
    }

    override suspend fun setOnboardingCompleted(isCompleted: Boolean) {
        dataStore.edit { prefs ->
            prefs[ONBOARDING_COMPLETED] = isCompleted
        }
    }

    companion object {
        private val ONBOARDING_COMPLETED = booleanPreferencesKey("ONBOARDING_COMPLETED")
    }
}
