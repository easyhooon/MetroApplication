import com.android.build.gradle.LibraryExtension
import com.easyhooon.metroapplication.convention.Plugins
import com.easyhooon.metroapplication.convention.applyPlugins
import com.easyhooon.metroapplication.convention.configureCompose
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

internal class AndroidLibraryComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            applyPlugins(
                Plugins.ANDROID_LIBRARY,
                Plugins.KOTLIN_COMPOSE,
            )

            extensions.configure<LibraryExtension> {
                configureCompose(this)
            }
        }
    }
}