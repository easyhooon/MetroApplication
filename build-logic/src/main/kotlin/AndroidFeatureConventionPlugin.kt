import com.google.devtools.ksp.gradle.KspExtension
import com.easyhooon.metroapplication.convention.api
import com.easyhooon.metroapplication.convention.applyPlugins
import com.easyhooon.metroapplication.convention.implementation
import com.easyhooon.metroapplication.convention.ksp
import com.easyhooon.metroapplication.convention.libs
import com.easyhooon.metroapplication.convention.project
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

internal class AndroidFeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            applyPlugins(
                "metro.android.library",
                "metro.android.library.compose",
                "dev.zacsweers.metro",
                "com.google.devtools.ksp",
            )

            extensions.configure<KspExtension> {
                arg("circuit.codegen.mode", "metro")
            }

            dependencies {
                implementation(project(path = ":core:common"))
                implementation(project(path = ":core:model"))
                implementation(project(path = ":core:di"))
                implementation(project(path = ":feature:screens"))

                implementation(libs.circuit.foundation)
                implementation(libs.circuit.runtime)

                api(libs.circuit.codegen.annotation)
                ksp(libs.circuit.codegen.ksp)
            }
        }
    }
}