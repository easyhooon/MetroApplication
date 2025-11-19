plugins {
    alias(libs.plugins.metro.android.feature)
}

android {
    namespace = "com.easyhooon.metroapplication.feature.main"
}

dependencies {
    implementation(libs.androidx.activity.compose)
}