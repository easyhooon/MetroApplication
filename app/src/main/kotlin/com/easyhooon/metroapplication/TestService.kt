package com.easyhooon.metroapplication

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.easyhooon.metroapplication.core.data.api.repository.UserRepository
import com.easyhooon.metroapplication.core.di.ServiceKey
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.ContributesIntoMap
import dev.zacsweers.metro.Inject
import dev.zacsweers.metro.binding

/**
 * Test service for Metro bug reproduction.
 * This service is injected in AppScope and depends on UserRepository from DataScope,
 * which triggers the Metro compiler crash.
 */
@ContributesIntoMap(AppScope::class, binding = binding<Service>())
@ServiceKey(TestService::class)
@Inject
class TestService(
    private val userRepository: UserRepository,
) : Service() {
    override fun onBind(intent: Intent?): IBinder? = null
}
