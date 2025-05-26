plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin)
    alias(libs.plugins.jetbrains.kotlin.compose)
}

android {
    namespace = "com.brandoncano.resistancecalculator"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.brandoncano.resistancecalculator"
        minSdk = 21
        targetSdk = 35
        versionCode = 34 // for 4.1.0
        versionName = "4.1.0-develop"

        vectorDrawables {
            useSupportLibrary = true
        }
    }
    applicationVariants.configureEach {
        val suffix = if (buildType.name == "debug") ", DEBUG" else ""
        resValue("string", "version", "$versionName$suffix")
        resValue("string", "last_updated", "5/25/2025")
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
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }

    testOptions {
        unitTests.isReturnDefaultValues = true
    }
}

composeCompiler {
    enableStrongSkippingMode = true
}

dependencies {
    // androidx
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
    // androidx.compose
    implementation(libs.androidx.compose.runtime.livedata)
    implementation(libs.androidx.compose.material.icons)
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
