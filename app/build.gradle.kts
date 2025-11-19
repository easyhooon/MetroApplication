plugins {
    alias(libs.plugins.metro.android.application)
    alias(libs.plugins.metro.android.application.compose)
    alias(libs.plugins.metro)
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

dependencies {
    // Minimal dependencies for Metro bug reproduction
    implementations(
        projects.core.di,
        projects.core.data.api,
        projects.core.data.impl,
        projects.core.datastore.api,
        projects.core.datastore.impl,

        libs.androidx.core.ktx,
        libs.androidx.lifecycle.runtime.ktx,
        libs.androidx.activity.compose,
        platform(libs.androidx.compose.bom),
        libs.androidx.compose.ui,
        libs.androidx.compose.material3,
    )

    testImplementation(libs.junit)
}
