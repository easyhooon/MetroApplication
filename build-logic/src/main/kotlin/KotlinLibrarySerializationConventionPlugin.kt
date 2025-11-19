import com.easyhooon.metroapplication.convention.Plugins
import com.easyhooon.metroapplication.convention.applyPlugins
import com.easyhooon.metroapplication.convention.implementation
import com.easyhooon.metroapplication.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal class KotlinLibrarySerializationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            applyPlugins(
                Plugins.KOTLINX_SERIALIZATION
            )

            dependencies {
                implementation(libs.kotlinx.serialization.json)
            }
        }
    }
}
