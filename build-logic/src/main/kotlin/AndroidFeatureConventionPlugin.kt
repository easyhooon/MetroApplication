import com.android.build.gradle.LibraryExtension
import com.easyhooon.metroapplication.convention.Plugins
import com.easyhooon.metroapplication.convention.applyPlugins
import com.easyhooon.metroapplication.convention.configureAndroid
import com.easyhooon.metroapplication.convention.configureCompose
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

internal class AndroidFeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            applyPlugins(
                Plugins.ANDROID_LIBRARY,
                Plugins.KOTLIN_ANDROID,
                Plugins.KOTLIN_COMPOSE,
                Plugins.KSP,
                Plugins.METRO,
            )

            extensions.configure<LibraryExtension> {
                configureAndroid(this)
                configureCompose(this)
            }
        }
    }
}