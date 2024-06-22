pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        maven { url = uri("https://www.jitpack.io") }
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://www.jitpack.io") }
    }
}

rootProject.name = "Engineer's metal calcs"
include(":app")
include(":api")
include(":core")
include(":core:api")
include(":core:impl")
include(":core-ui")
include(":feature-listing-all-calcs")
include(":feature-calc-cargo-weight")
include(":feature-listing-all-saves")
include(":db-room")
include(":feature-calc-temp-likvidus")
include(":feature-refguide")
