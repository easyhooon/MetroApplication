plugins {
    alias(libs.plugins.metro.android.feature)
}

android {
    namespace = "com.easyhooon.metroapplication.feature.main"

    lint {
        abortOnError = false
    }
}

dependencies {
    implementation(project(":core:di"))
    implementation(project(":feature:screens"))

    implementation("androidx.core:core:1.13.1")
    implementation(libs.circuit.foundation)
    implementation(libs.circuit.runtime)

    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.bundles.androidx.compose)
    implementation(libs.androidx.activity.compose)
}