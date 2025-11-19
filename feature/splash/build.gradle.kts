plugins {
    alias(libs.plugins.metro.android.feature)
}

android {
    namespace = "com.easyhooon.metroapplication.feature.splash"
}

dependencies {
    implementation(project(":core:data-api"))
}