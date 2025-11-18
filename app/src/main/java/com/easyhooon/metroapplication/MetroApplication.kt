package com.easyhooon.metroapplication

import android.app.Application
import com.easyhooon.metroapplication.di.AppGraph
import dev.zacsweers.metro.createGraphFactory

class MetroApplication : Application() {
    val appGraph by lazy { createGraphFactory<AppGraph.Factory>().create(this) }
}