package com.easyhooon.metroapplication.core.datastore.impl.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.easyhooon.metroapplication.core.datastore.api.datasource.TokenDataSource
import com.easyhooon.metroapplication.core.datastore.impl.datasource.DefaultTokenDataSource
import com.easyhooon.metroapplication.core.di.ApplicationContext
import com.easyhooon.metroapplication.core.di.DataScope
import dev.zacsweers.metro.Binds
import dev.zacsweers.metro.ContributesTo
import dev.zacsweers.metro.Provides

private const val TOKEN_DATASTORE_NAME = "TOKENS_DATASTORE"
private const val ONBOARDING_DATASTORE_NAME = "ONBOARDING_DATASTORE"

private val Context.tokenDataStore by preferencesDataStore(name = TOKEN_DATASTORE_NAME)
private val Context.onboardingDataStore by preferencesDataStore(name = ONBOARDING_DATASTORE_NAME)

@ContributesTo(DataScope::class)
interface DataStoreGraph {

    @TokenDataStore
    @Provides
    fun provideTokenDataStore(
        @ApplicationContext context: Context,
    ): DataStore<Preferences> = context.tokenDataStore

    @OnboardingDataStore
    @Provides
    fun provideOnboardingDataStore(
        @ApplicationContext context: Context,
    ): DataStore<Preferences> = context.onboardingDataStore

    @Binds
    val DefaultTokenDataSource.bind: TokenDataSource
}