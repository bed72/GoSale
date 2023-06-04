import java.util.Properties

import java.io.File
import java.io.FileInputStream

plugins {
    id("kotlin-kapt")
    id("kotlinx-serialization")
    id("com.android.application")
    id("dagger.hilt.android.plugin")
    id("org.jetbrains.kotlin.android")
    id("androidx.navigation.safeargs")
    id("io.gitlab.arturbosch.detekt")
    id("kotlin-parcelize")
}

val libs = rootProject.extra
val keys = Properties().apply {
    load(FileInputStream(File(rootProject.rootDir, "key.properties")))
}

android {
    compileSdk = libs["sdkVersion"] as Int
    namespace = libs["namespace"] as String

    defaultConfig {
        minSdk = libs["minSdk"] as Int
        targetSdk = libs["sdkVersion"] as Int
        versionCode = libs["versionCode"] as Int
        versionName = libs["versionName"] as String
        applicationId = libs["namespace"] as String

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        vectorDrawables {
            useSupportLibrary = true
        }

        buildConfigField("String", "BASE_URL", keys.getProperty("BASE_URL"))
    }

    @Suppress("UnstableApiUsage")
    buildTypes {
        getByName("debug") {
            isDebuggable = true
            applicationIdSuffix = ".debug"
        }

        register("profile") {
            isMinifyEnabled = true
            isShrinkResources = true
            applicationIdSuffix = ".profile"
            initWith(getByName("debug"))
            signingConfig = signingConfigs.getByName("debug")
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules-staging.pro"
            )
        }

        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true
            signingConfig = signingConfigs.getByName("debug")
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
        freeCompilerArgs = listOf("-opt-in=kotlin.RequiresOptIn")
    }

    @Suppress("UnstableApiUsage")
    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.7"
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(project(":core"))

    implementation("androidx.core:core-splashscreen:1.0.1")
    implementation("androidx.compose.material3:material3:1.1.0")

    val composeVersion = "1.4.3"
    implementation("androidx.compose.ui:ui:$composeVersion")
    implementation("androidx.compose.ui:ui-graphics:$composeVersion")
    implementation("androidx.compose.ui:ui-tooling-preview:$composeVersion")

    implementation("androidx.navigation:navigation-compose:2.5.3")

    implementation("androidx.core:core-ktx:1.10.1")
    implementation("androidx.activity:activity-compose:1.7.2")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
    implementation(platform("androidx.compose:compose-bom:2023.05.01"))

    // Hilt
    val hiltVersion = "2.46.1"
    kapt("com.google.dagger:hilt-android-compiler:$hiltVersion")
    implementation("com.google.dagger:hilt-android:$hiltVersion")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")

    // Ktor
    val ktorVersion = "2.3.0"
    implementation("io.ktor:ktor-client-core:$ktorVersion")
    implementation("io.ktor:ktor-client-okhttp:$ktorVersion")
    implementation("io.ktor:ktor-client-logging:$ktorVersion")
    implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")

    // Other libs
    implementation("com.squareup.okhttp3:logging-interceptor:4.11.0")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.1")

    // Lint
    detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:1.23.0")

    // Debug
    debugImplementation("androidx.compose.ui:ui-tooling:$composeVersion")
    debugImplementation("androidx.compose.ui:ui-test-manifest:$composeVersion")
    debugImplementation("com.squareup.leakcanary:leakcanary-android:2.11")

    // Tests
    testImplementation(project(":test"))

    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.05.01"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:$composeVersion")
}

project.afterEvaluate {
    tasks.named("preBuild") {
        dependsOn("detekt")
    }
}

detekt {
    toolVersion = "1.23.0"
    basePath = rootDir.toString()

    debug = true
    parallel = true

    allRules = false
    ignoreFailures = false
    buildUponDefaultConfig = false
    disableDefaultRuleSets = false

    source = files("src/main/java", "src/main/kotlin")
    config = files("$rootDir/config/detekt/detekt.yml")
    baseline = file("$rootDir/config/detekt/baseline.xml")
}
