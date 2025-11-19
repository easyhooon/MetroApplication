plugins {
    alias(libs.plugins.metro.android.feature)
    alias(libs.plugins.kotlin.parcelize)
}

android {
    namespace = "com.easyhooon.metroapplication.feature.detail"
}

dependencies {
    implementation(projects.core.data.api)
}
