plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlin)
    alias(libs.plugins.jetbrainsKotlinCompose)
}

android {
    namespace = "com.brandoncano.resistancecalculator"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.brandoncano.resistancecalculator"
        minSdk = 24
        targetSdk = 34
        versionCode = 17
        versionName = "3.1.0-develop"

        vectorDrawables {
            useSupportLibrary = true
        }
    }
    applicationVariants.configureEach {
        resValue("string", "version", versionName)
        resValue("string", "last_updated", "6/17/2024")
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
    tasks.register("prepareKotlinBuildScriptModel")
    @Suppress("UnstableApiUsage")
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
    // androidTestImplementation("androidx.test.ext:junit:1.2.1")
    // external libraries
    implementation(files("libs/ostermillerutils_1_07_00.jar"))
}