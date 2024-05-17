pluginManagement {
    repositories {
        google()
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

rootProject.name = "Engineer's metal calcs"
include(":app")
include(":api")
include(":core")
include(":core:api")
include(":core:impl")
include(":core-ui")
include(":feature-listing-all-calcs:api")
include(":feature-listing-all-calcs:impl")
include(":feature-calc-cargo-weight")
