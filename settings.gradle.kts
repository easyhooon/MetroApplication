rootProject.name = "MetroApplication"

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
enableFeaturePreview("STABLE_CONFIGURATION_CACHE")

pluginManagement {
    includeBuild("build-logic")
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
include(":app")

// Core modules
include(":core:model")
include(":core:common")
include(":core:di")
include(":core:datastore:api")
include(":core:datastore:impl")
include(":core:network")
include(":core:data:api")
include(":core:data:impl")
include(":core:ocr")
include(":core:designsystem")
include(":core:ui")

// Feature modules
include(":feature:screens")
include(":feature:splash")
include(":feature:main")
include(":feature:home")
include(":feature:detail")
include(":feature:search")
include(":feature:settings")
include(":feature:login")
 