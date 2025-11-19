package com.easyhooon.metroapplication

import android.app.Application
import com.easyhooon.metroapplication.di.AppGraph
import com.skydoves.compose.stability.runtime.ComposeStabilityAnalyzer
import dev.zacsweers.metro.createGraphFactory

class MetroApplication : Application() {
    val appGraph by lazy { createGraphFactory<AppGraph.Factory>().create(this) }
}
