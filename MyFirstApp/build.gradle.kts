// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
}

buildscript {
    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        // Use version 2.5.0 or higher for navigation-safe-args
        val navVersion = "2.5.0"  // Choose the latest stable version if possible
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:$navVersion")

        // Ensure Kotlin version is compatible with the rest of the setup
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.21")  // Or a newer version if required
    }
}
