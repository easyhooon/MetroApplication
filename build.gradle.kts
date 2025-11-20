// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.gradle.dependency.handler.extensions)
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.metro) apply false
}

allprojects {
    apply {
        plugin(rootProject.libs.plugins.gradle.dependency.handler.extensions.get().pluginId)
    }
}