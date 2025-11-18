package com.easyhooon.metroapplication

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.easyhooon.metroapplication.core.di.ServiceKey
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.ContributesIntoMap
import dev.zacsweers.metro.Inject
import dev.zacsweers.metro.binding

@ContributesIntoMap(AppScope::class, binding = binding<Service>())
@ServiceKey(TestService::class)
@Inject
class TestService : Service() {
    override fun onBind(intent: Intent?): IBinder? = null
}