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
        versionCode = 33 // for 4.0.1
        versionName = "4.0.1"

        // Notes for 4.1.0
        // :Started:
        // - App theme controlled in app not shared lib
        // - New SharedPreferenceAdapter
        // - Create app wide Application for universal context retrieval
        // - Started bringing M3 components & menu items into app
        // - About screen is in app
        // - Update to target SDK 36

        // :Not Started:
        // - Update Dependencies to be organized better
        // - Bring donate and view apps screens into app code, remove from shared lib
        // - Bring billing manager into app code
        // - Update shared lib to be just pre-defined M3 components
        // - Update remaining elements using old structure (menu items)
        // - val openMenu = remember { mutableStateOf(false) } and val reset = remember { mutableStateOf(false) } - we want to remove MutableState<Boolean> from a param type if possible
        // - Make app wide VM more useful for more menu related states (or app wide states)
        // - App obfuscation? R8?
        // - "Pro" version - can pay 1.99 for access to PDFs

        // Overall, this should make it easier to do the simple apps and their features without lots of redundancies in the app itself

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
