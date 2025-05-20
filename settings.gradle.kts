pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
    plugins {
        id("com.android.application") version "8.4.0" apply false
        id("org.jetbrains.kotlin.android") version "2.0.0" apply false
        id("org.jetbrains.kotlin.kapt") version "2.0.0" apply false
        id("org.jetbrains.kotlin.plugin.compose") version "2.0.0" apply false // <-- ESTE ES EL NECESARIO
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "MiMedidor"
include(":app")

 