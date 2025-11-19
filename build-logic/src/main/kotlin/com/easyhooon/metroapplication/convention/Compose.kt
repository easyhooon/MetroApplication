package com.easyhooon.metroapplication.convention

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.kotlin.compose.compiler.gradle.ComposeCompilerGradlePluginExtension

internal fun Project.configureCompose(
    extension: CommonExtension<*, *, *, *, *, *>
) {
    extension.apply {
        buildFeatures {
            compose = true
        }
    }

    configure<ComposeCompilerGradlePluginExtension> {
        includeSourceInformation.set(true)

        stabilityConfigurationFiles.addAll(
            rootProject.layout.projectDirectory.file("stability.config.conf")
        )
    }

    dependencies {
        implementation(platform(libs.androidx.compose.bom))
        implementation(libs.androidx.compose.ui)
        implementation(libs.androidx.compose.material3)
    }
}