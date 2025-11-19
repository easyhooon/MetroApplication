plugins {
    alias(libs.plugins.metro.android.library)
    alias(libs.plugins.metro.android.retrofit)
    alias(libs.plugins.metro)
}

android {
    namespace = "com.easyhooon.metroapplication.core.ocr"
}

dependencies {
    implementations(
        projects.core.common,
        projects.core.di,
    )
}
