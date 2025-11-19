plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.metro)
}

android {
    namespace = "com.easyhooon.metroapplication.core.data.impl"
    compileSdk = 35

    defaultConfig {
        minSdk = 28
        buildConfigField(
            "String",
            "VERSION_NAME",
            "\"1.0.0\""
        )
    }

    buildFeatures {
        buildConfig = true
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
        projects.core.data.api,
        projects.core.datastore.api,

        platform(libs.firebase.bom),
        libs.firebase.config,
        libs.firebase.messaging,
        libs.firebase.installations,
    )
}
