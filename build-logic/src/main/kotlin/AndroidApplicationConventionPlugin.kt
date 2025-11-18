import com.android.build.api.dsl.ApplicationExtension
import com.easyhooon.metroapplication.convention.Plugins
import com.easyhooon.metroapplication.convention.applyPlugins
import com.easyhooon.metroapplication.convention.configureAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

internal class AndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            applyPlugins(
                Plugins.ANDROID_APPLICATION,
                Plugins.KOTLIN_ANDROID,
            )

            extensions.configure<ApplicationExtension> {
                configureAndroid(this)

                defaultConfig {
                    targetSdk = 36
                    versionName = "1.0"
                    versionCode = 1
                }
            }
        }
    }
}