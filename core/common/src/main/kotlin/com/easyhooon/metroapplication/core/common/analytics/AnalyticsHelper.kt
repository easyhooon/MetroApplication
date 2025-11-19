package com.easyhooon.metroapplication.core.common.analytics

import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.logEvent
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.Inject
import dev.zacsweers.metro.SingleIn

@SingleIn(AppScope::class)
@Inject
class AnalyticsHelper(
    private val firebaseAnalytics: FirebaseAnalytics,
) {

    fun logScreenView(screenName: String) {
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW) {
            param(FirebaseAnalytics.Param.SCREEN_NAME, screenName)
        }
    }

    fun logEvent(eventName: String) {
        firebaseAnalytics.logEvent(eventName) {}
    }
}
