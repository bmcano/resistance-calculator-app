import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin)
    alias(libs.plugins.jetbrains.kotlin.compose)
}

android {
    namespace = "com.brandoncano.resistancecalculator"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.brandoncano.resistancecalculator"
        minSdk = 21
        targetSdk = 36
        versionCode = 34 // for 4.1.0
        versionName = "4.1.0"

        // Notes for 4.1.0
        // :Started:
        // - App theme controlled in app not shared lib
        // - New SharedPreferenceAdapter
        // - Create app wide Application for universal context retrieval
        // - Started bringing M3 components & menu items into app
        // - About screen is in app
        // - Update to target SDK 36
        // - Removing openMenu and Reset Mutable state variables from screens
        // - Rewrote menu item composables
        // - Update Dependencies to be organized better
        // - Removed shared lib, no longer needed
        // :Not Started:
        // - Bring donate into app code, remove from shared lib
        // - Bring billing manager into app code
        // - App obfuscation? R8?
        // - "Pro" version - can pay 1.99 for access to PDFs

        vectorDrawables {
            useSupportLibrary = true
        }
    }
    applicationVariants.configureEach {
        val suffix = if (buildType.name == "debug") ", DEBUG" else ""
        resValue("string", "version", "$versionName$suffix")
        resValue("string", "last_updated", "7/24/2025")
    }
    buildTypes {
        release {
            isMinifyEnabled = false
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
    }
    testOptions {
        unitTests.isReturnDefaultValues = true
    }
}

dependencies {
    // AndroidX
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.browser)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.navigation.compose)
    // androidx.compose.ui
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    implementation(libs.androidx.compose.runtime.livedata)
    implementation(libs.androidx.compose.material.icons.extended)
    implementation(libs.androidx.compose.material3)
    // com.google
    implementation(libs.gson)
    // unit testing
    testImplementation(libs.mockk)
    testImplementation(libs.junit)
    // external libraries
    implementation(libs.ostermiller.util)
    implementation(libs.bmcano.util)
}
