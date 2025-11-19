plugins {
    alias(libs.plugins.metro.android.library)
    alias(libs.plugins.metro)
}

android {
    namespace = "com.easyhooon.metroapplication.core.datastore.impl"
}

dependencies {
    implementations(
        projects.core.di,
        projects.core.datastore.api,

        libs.androidx.datastore.preferences,
    )
}
