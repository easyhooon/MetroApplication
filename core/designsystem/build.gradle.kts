plugins {
    alias(libs.plugins.metro.android.library)
    alias(libs.plugins.metro.android.library.compose)
}

android {
    namespace = "com.easyhooon.metroapplication.core.designsystem"
}

dependencies {
    implementations(
        projects.core.common,
        projects.core.model,
    )
}
