pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()

        maven {
            url = uri("https://jcenter.bintray.com")
        }
        maven {
            url = uri("https://storage.googleapis.com/r8-releases/raw")
        }
        jcenter()
        mavenLocal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

buildCache {
    local {
        isEnabled = true
        directory = File(rootDir, "build-cache")
        removeUnusedEntriesAfterDays = 30
    }
}

rootProject.name = "BambaSwap"
include(":app")
 