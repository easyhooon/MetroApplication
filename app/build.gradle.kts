plugins {
    alias(libs.plugins.metro.android.application)
    alias(libs.plugins.metro.android.application.compose)
    alias(libs.plugins.metro)
    alias(libs.plugins.ksp)
    alias(libs.plugins.compose.stability.analyzer)
}

android {
    namespace = "com.easyhooon.metroapplication"

    defaultConfig {
        applicationId = "com.easyhooon.metroapplication"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    buildFeatures {
        buildConfig = true
    }
}

composeStabilityAnalyzer {
    enabled.set(true)
}

ksp {
    arg("circuit.codegen.mode", "metro")
}

dependencies {
    implementations(
        projects.core.di,
        projects.core.datastore.api,
        projects.core.datastore.impl,
        projects.core.network,
        projects.core.data.api,
        projects.core.data.impl,
        projects.core.ocr,

        projects.feature.screens,
        projects.feature.splash,
        projects.feature.main,

        libs.circuit.foundation,
        libs.circuit.runtime,
        libs.compose.effects,
    )

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
}