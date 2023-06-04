@file:Suppress("COMPATIBILITY_WARNING")
ext {
    extra["minSdk"] = 27
    extra["sdkVersion"] = 33
    extra["versionCode"] = 1

    extra["versionName"] = "1.0"
    extra["namespace"] = "com.bed.go.sale"
}

buildscript {
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.21")
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.46.1")
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.5.3")
    }
}

plugins {
    id("com.android.application") version "8.0.2" apply false

    id("io.gitlab.arturbosch.detekt") version "1.23.0" apply true

    id("org.jetbrains.kotlin.jvm") version "1.8.21" apply false
    id("org.jetbrains.kotlin.android") version "1.8.21" apply false
    id("org.jetbrains.kotlin.plugin.serialization") version "1.8.21" apply false
}
