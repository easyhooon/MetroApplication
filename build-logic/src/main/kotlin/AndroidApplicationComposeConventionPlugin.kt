import com.android.build.api.dsl.ApplicationExtension
import com.easyhooon.metroapplication.convention.Plugins
import com.easyhooon.metroapplication.convention.applyPlugins
import com.easyhooon.metroapplication.convention.configureCompose
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

internal class AndroidApplicationComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            applyPlugins(
                Plugins.ANDROID_APPLICATION,
                Plugins.KOTLIN_COMPOSE,
            )

            extensions.configure<ApplicationExtension> {
                configureCompose(this)
            }
        }
    }
}