package com.easyhooon.metroapplication.convention

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinProjectExtension

internal fun Project.configureAndroid(extension: CommonExtension<*, *, *, *, *, *>) {
    extension.apply {
        compileSdk = 35

        defaultConfig {
            minSdk = 28
        }

        compileOptions {
            sourceCompatibility = ApplicationConstants.javaVersion
            targetCompatibility = ApplicationConstants.javaVersion
        }

        extensions.configure<KotlinProjectExtension> {
            jvmToolchain(ApplicationConstants.JAVA_VERSION_INT)
        }
    }
}