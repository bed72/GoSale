plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    api("javax.inject:javax.inject:1")
    api("io.arrow-kt:arrow-core:1.2.0-RC")
    api("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1")

    testImplementation(project(":test"))
}