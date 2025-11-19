package com.easyhooon.metroapplication.core.datastore.api.datasource

import kotlinx.coroutines.flow.Flow

interface OnboardingDataSource {
    val onboardingState: Flow<Boolean>
    suspend fun setOnboardingCompleted(isCompleted: Boolean)
}
