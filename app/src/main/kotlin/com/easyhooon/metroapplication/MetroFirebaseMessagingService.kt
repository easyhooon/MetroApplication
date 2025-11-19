package com.easyhooon.metroapplication

import android.app.Service
import android.util.Log
import com.easyhooon.metroapplication.core.di.ServiceKey
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.ContributesIntoMap
import dev.zacsweers.metro.Inject
import dev.zacsweers.metro.binding

@ContributesIntoMap(AppScope::class, binding = binding<Service>())
@ServiceKey(MetroFirebaseMessagingService::class)
@Inject
class MetroFirebaseMessagingService : FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d(TAG, "FCM Token: $token")
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        Log.d(TAG, "Message received from: ${message.from}")
    }

    companion object {
        private const val TAG = "MetroFCMService"
    }
}
