plugins {
    alias(libs.plugins.metro.android.application)
    alias(libs.plugins.metro.android.application.compose)
    alias(libs.plugins.metro)
    alias(libs.plugins.ksp)
    alias(libs.plugins.compose.stability.analyzer)
    alias(libs.plugins.google.service)
    alias(libs.plugins.firebase.crashlytics)
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
                "proguard-rules.pro",
            )
        }
    }

    buildFeatures {
        buildConfig = true
    }

    packaging {
        resources {
            excludes += "/META-INF/versions/9/OSGI-INF/MANIFEST.MF"
        }
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
        projects.core.common,
        projects.core.designsystem,
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

        libs.androidx.core.ktx,
        libs.androidx.lifecycle.runtime.ktx,
        libs.androidx.activity.compose,
        libs.androidx.startup,
        platform(libs.androidx.compose.bom),
        libs.androidx.compose.ui,
        libs.androidx.compose.ui.graphics,
        libs.androidx.compose.ui.tooling.preview,
        libs.androidx.compose.material3,

        platform(libs.firebase.bom),
        libs.firebase.analytics,
        libs.firebase.crashlytics,
        libs.firebase.messaging,
    )

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
}
