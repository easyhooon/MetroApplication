package com.easyhooon.metroapplication.initializer

import android.content.Context
import androidx.startup.Initializer
import com.easyhooon.metroapplication.BuildConfig
import com.skydoves.compose.stability.runtime.ComposeStabilityAnalyzer

class ComposeStabilityAnalyzerInitializer : Initializer<Unit> {
    override fun create(context: Context) {
        ComposeStabilityAnalyzer.setEnabled(BuildConfig.DEBUG)
    }

    override fun dependencies(): List<Class<out Initializer<*>>> {
        return emptyList()
    }
}
