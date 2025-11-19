plugins {
    alias(libs.plugins.metro.android.library)
    alias(libs.plugins.metro.android.library.compose)
}

android {
    namespace = "com.easyhooon.metroapplication.core.ui"
}

dependencies {
    implementation(project(":core:designsystem"))
    implementation(project(":core:common"))
}
