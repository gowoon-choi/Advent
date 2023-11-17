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
        maven(url = "https://devrepo.kakao.com/nexus/content/groups/public/")
    }
}

rootProject.name = "Advent"
include(":app")
include(":core:designsystem")
include(":feature:home")
include(":feature:login")
include(":feature:create_calendar")
include(":core:domain")
include(":feature:calendar:santa")
include(":feature:calendar:gift")
include(":core:common")
include(":feature:common")
include(":core:login_manager")
include(":core:datastore")
include(":core:data")
