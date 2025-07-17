pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {

    repositories {
        google()
        mavenCentral()
        maven("https://maven.google.com")
    }
}

rootProject.name = "Compose"
include(":app")
 