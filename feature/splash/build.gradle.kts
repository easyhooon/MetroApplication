plugins {
    alias(libs.plugins.metro.android.feature)
}

ksp {
    arg("circuit.codegen.mode", "metro")
}

android {
    namespace = "com.easyhooon.metroapplication.feature.splash"
}

dependencies {
    implementation(project(":core:di"))
    implementation(project(":feature:screens"))

    implementation("androidx.core:core:1.13.1")
    implementation(libs.circuit.foundation)
    implementation(libs.circuit.runtime)
    api(libs.circuit.codegen.annotation)
    ksp(libs.circuit.codegen.ksp)

    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.bundles.androidx.compose)
}