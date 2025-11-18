package com.easyhooon.metroapplication.convention

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.configureCompose(
    extension: CommonExtension<*, *, *, *, *, *>
) {
    extension.apply {
        buildFeatures {
            compose = true
        }
    }

    dependencies {
        add("implementation", platform(libs.androidx.compose.bom))
        add("implementation", libs.bundles.androidx.compose)
        add("debugImplementation", libs.androidx.compose.ui.tooling)
    }
}