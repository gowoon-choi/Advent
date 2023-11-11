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

rootProject.name = "Advent"
include(":app")
include(":core:designsystem")
include(":feature:home")
include(":feature:login")
include(":core:common")
include(":feature:create_calendar")
include(":core:domain")
include(":feature:calendar:common")
include(":feature:calendar:santa")
include(":feature:calendar:gift")
