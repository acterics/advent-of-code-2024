pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }

    plugins {
        kotlin("jvm").version(extra["kotlin.version"] as String)
    }
}


dependencyResolutionManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
    }
    versionCatalogs {
        create("deps") {
            from(files("deps.versions.toml"))
        }
    }
}


include(":utils")

include(":day-1")
include(":day-2")
include(":day-3")
include(":day-4")
include(":day-5")
