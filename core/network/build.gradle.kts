plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.metro.android.retrofit)
    alias(libs.plugins.metro)
    kotlin("plugin.serialization") version "2.2.21"
}

android {
    namespace = "com.easyhooon.metroapplication.core.network"
    compileSdk = 35

    defaultConfig {
        minSdk = 28
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    implementations(
        projects.core.di,
        projects.core.datastore.api,

        libs.kotlinx.coroutines.core,
    )
}
