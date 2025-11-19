plugins {
    alias(libs.plugins.metro.android.feature)
    alias(libs.plugins.kotlin.parcelize)
}

android {
    namespace = "com.easyhooon.metroapplication.feature.settings"
}

dependencies {
    implementation(projects.core.data.api)
}
