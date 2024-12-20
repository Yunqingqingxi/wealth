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
        maven { url = uri("https://jitpack.io") }
    }
}

rootProject.name = "wealth"
include(":app")
include(":app:presentation")
include(":app:common")
include(":app:network")
include(":app:network:FinanceReport")
include(":app:network:FinanceBudget")
include(":app:network:FinanceStats")
include(":app:network:FinanceUser")
