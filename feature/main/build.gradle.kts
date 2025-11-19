plugins {
    alias(libs.plugins.metro.android.feature)
}

android {
    namespace = "com.easyhooon.metroapplication.feature.main"

    lint {
        abortOnError = false
    }
}

dependencies {
    implementation(libs.androidx.activity.compose)
}