plugins {
    alias(libs.plugins.metro.android.library)
    alias(libs.plugins.metro)
}

android {
    namespace = "com.easyhooon.metroapplication.core.data.impl"

    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    implementations(
        projects.core.di,
        projects.core.data.api,
        projects.core.datastore.api,
    )
}
