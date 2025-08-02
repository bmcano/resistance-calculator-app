import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import java.io.FileInputStream
import java.util.Properties

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
    signingConfigs {
        val keystorePropsFile = rootProject.file("local.properties")
        val keystoreProps = Properties().apply {
            load(FileInputStream(keystorePropsFile))
        }
        create("release") {
            storeFile = file(keystoreProps["resistor.storeFile"] as String)
            storePassword = keystoreProps["resistor.storePassword"] as String
            keyAlias = keystoreProps["resistor.keyAlias"] as String
            keyPassword = keystoreProps["resistor.keyPassword"] as String
        }
    }
    buildTypes {
        release {
            signingConfig = signingConfigs.getByName("release")
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

@Suppress("deprecation")
tasks.register("installReleaseApk") {
    group = "custom"
    description = "Uninstall existing release, build new release APK, and install it on device"
    dependsOn("assembleRelease")
    doLast {
        val pkg = android.defaultConfig.applicationId
        exec {
            commandLine("adb", "uninstall", pkg)
            isIgnoreExitValue = true
        }
        exec {
            commandLine("adb", "install", "-r", "${project.buildDir}/outputs/apk/release/app-release.apk")
        }
    }
}
