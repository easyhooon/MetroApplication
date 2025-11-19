plugins {
    alias(libs.plugins.metro.android.library)
    alias(libs.plugins.metro)
}

android {
    namespace = "com.easyhooon.metroapplication.core.common"
}

dependencies {
    implementation(project(":core:model"))
    implementation(project(":core:network"))
}
