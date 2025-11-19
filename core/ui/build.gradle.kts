plugins {
    alias(libs.plugins.metro.android.library)
    alias(libs.plugins.metro.android.library.compose)
}

android {
    namespace = "com.easyhooon.metroapplication.core.ui"
}

dependencies {
    implementations(
        projects.core.designsystem,
        projects.core.common,
    )
}
