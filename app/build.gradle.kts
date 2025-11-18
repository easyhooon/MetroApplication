plugins {
    alias(libs.plugins.metro.android.application)
    alias(libs.plugins.metro.android.application.compose)
    alias(libs.plugins.metro.metro)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.easyhooon.metroapplication"

    defaultConfig {
        applicationId = "com.easyhooon.metroapplication"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}

ksp {
    arg("circuit.codegen.mode", "metro")
}

dependencies {
    // Core modules
    implementation(project(":core:di"))
    implementation(project(":core:datastore-api"))
    implementation(project(":core:datastore-impl"))
    implementation(project(":core:network"))
    implementation(project(":core:data-api"))
    implementation(project(":core:data-impl"))

    // Feature modules
    implementation(project(":feature:screens"))
    implementation(project(":feature:splash"))
    implementation(project(":feature:main"))

    implementation(libs.circuit.foundation)
    implementation(libs.circuit.runtime)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
}