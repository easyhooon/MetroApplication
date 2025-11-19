plugins {
    alias(libs.plugins.metro.android.library)
    alias(libs.plugins.metro)
}

android {
    namespace = "com.easyhooon.metroapplication.core.common"
}

dependencies {
    implementations(
        projects.core.model,
        projects.core.network,
        projects.core.di,

        platform(libs.firebase.bom),
        libs.firebase.analytics,
    )
}
