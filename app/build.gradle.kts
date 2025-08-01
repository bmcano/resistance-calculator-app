import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.jetbrains.kotlin.plugin.compose)
    alias(libs.plugins.google.services)
    alias(libs.plugins.firebase.crashlytics)
}

android {
    namespace = "com.brandoncano.resistancecalculator"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.brandoncano.resistancecalculator"
        minSdk = 23 // Android 6.0
        targetSdk = 36
        versionCode = 42 // for 4.2.4
        versionName = "4.2.4"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }
    applicationVariants.configureEach {
        val suffix = if (buildType.name == "debug") ", DEBUG" else ""
        resValue("string", "version", "$versionName$suffix")
        resValue("string", "last_updated", "7/30/2025")
    }
    buildTypes {
        release {
            // adb uninstall com.brandoncano.resistancecalculator
            // adb install -r .\app\release\app-release.apk
            isMinifyEnabled = true
            isShrinkResources = true
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
    kotlin {
        compilerOptions {
            jvmTarget = JvmTarget.JVM_17
        }
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    testOptions {
        unitTests.isReturnDefaultValues = true
    }
}

dependencies {
    implementation(project(":library"))
    implementation(platform(libs.androidx.compose.bom))
    implementation(platform(libs.firebase.bom))
    implementation(libs.bundles.core.implementation)
    // test/debug
    testImplementation(libs.bundles.test.implementation)
    debugImplementation(libs.bundles.debug.implementation)
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    // external libraries
    implementation(libs.ostermiller.utils)
}
