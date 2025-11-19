plugins {
    alias(libs.plugins.metro.android.library)
}

android {
    namespace = "com.easyhooon.metroapplication.core.datastore.api"
}

dependencies {
    implementation(libs.kotlinx.coroutines.core)
}
