plugins {
    alias(libs.plugins.metro.android.library)
    alias(libs.plugins.metro)
}

android {
    namespace = "com.easyhooon.metroapplication.core.common"
}

dependencies {
    implementation(projects.core.model)
    implementation(projects.core.network)
    implementation(projects.core.di)

    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics)
}
