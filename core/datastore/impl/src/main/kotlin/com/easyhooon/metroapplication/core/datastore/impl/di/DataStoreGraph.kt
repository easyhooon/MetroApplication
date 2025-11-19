package com.easyhooon.metroapplication.core.datastore.impl.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.easyhooon.metroapplication.core.datastore.api.datasource.NotificationDataSource
import com.easyhooon.metroapplication.core.datastore.api.datasource.OnboardingDataSource
import com.easyhooon.metroapplication.core.datastore.api.datasource.TokenDataSource
import com.easyhooon.metroapplication.core.datastore.impl.datasource.DefaultNotificationDataSource
import com.easyhooon.metroapplication.core.datastore.impl.datasource.DefaultOnboardingDataSource
import com.easyhooon.metroapplication.core.datastore.impl.datasource.DefaultTokenDataSource
import com.easyhooon.metroapplication.core.di.ApplicationContext
import com.easyhooon.metroapplication.core.di.DataScope
import dev.zacsweers.metro.Binds
import dev.zacsweers.metro.ContributesTo
import dev.zacsweers.metro.Provides

private const val TOKEN_DATASTORE_NAME = "TOKENS_DATASTORE"
private const val ONBOARDING_DATASTORE_NAME = "ONBOARDING_DATASTORE"
private const val BOOK_RECENT_SEARCH_DATASTORE_NAME = "BOOK_RECENT_SEARCH_DATASTORE"
private const val LIBRARY_RECENT_SEARCH_DATASTORE_NAME = "LIBRARY_RECENT_SEARCH_DATASTORE"
private const val NOTIFICATION_DATASTORE_NAME = "NOTIFICATION_DATASTORE"

private val Context.tokenDataStore by preferencesDataStore(name = TOKEN_DATASTORE_NAME)
private val Context.onboardingDataStore by preferencesDataStore(name = ONBOARDING_DATASTORE_NAME)
private val Context.bookRecentSearchDataStore by preferencesDataStore(name = BOOK_RECENT_SEARCH_DATASTORE_NAME)
private val Context.libraryRecentSearchDataStore by preferencesDataStore(name = LIBRARY_RECENT_SEARCH_DATASTORE_NAME)
private val Context.notificationDataStore by preferencesDataStore(name = NOTIFICATION_DATASTORE_NAME)

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

    @BookRecentSearchDataStore
    @Provides
    fun provideBookRecentSearchDataStore(
        @ApplicationContext context: Context,
    ): DataStore<Preferences> = context.bookRecentSearchDataStore

    @LibraryRecentSearchDataStore
    @Provides
    fun provideLibraryRecentSearchDataStore(
        @ApplicationContext context: Context,
    ): DataStore<Preferences> = context.libraryRecentSearchDataStore

    @NotificationDataStore
    @Provides
    fun provideNotificationDataStore(
        @ApplicationContext context: Context,
    ): DataStore<Preferences> = context.notificationDataStore

    @Binds
    val DefaultTokenDataSource.bind: TokenDataSource

    @Binds
    val DefaultNotificationDataSource.bind: NotificationDataSource

    @Binds
    val DefaultOnboardingDataSource.bind: OnboardingDataSource
}