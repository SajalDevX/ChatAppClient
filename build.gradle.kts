buildscript {
    dependencies {
        classpath( "org.jetbrains.kotlin:kotlin-serialization:1.6.21")
    }
}

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    id ("com.android.library") version "7.3.1" apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
}
