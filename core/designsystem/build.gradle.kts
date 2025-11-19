plugins {
    alias(libs.plugins.metro.android.library)
    alias(libs.plugins.metro.android.library.compose)
}

android {
    namespace = "com.easyhooon.metroapplication.core.designsystem"
}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:model"))
}
