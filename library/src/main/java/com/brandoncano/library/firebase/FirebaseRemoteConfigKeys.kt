package com.brandoncano.library.firebase

/**
 * Job: Defined the remote config keys from the Firebase console.
 */
enum class FirebaseRemoteConfigKeys(val pathName: String) {
    // Privacy Policies
    PRIVACY_POLICY_CAPACITOR("android_url_privacy_policy_capacitor"),
    PRIVACY_POLICY_INDUCTOR("android_url_privacy_policy_inductor"),
    PRIVACY_POLICY_OHMS("android_url_privacy_policy_ohms"),
    PRIVACY_POLICY_RESISTOR("android_url_privacy_policy_resistor"),
    // Google Play Store
    PLAYSTORE_CAPACITOR("android_url_playstore_capacitor"),
    PLAYSTORE_INDUCTOR("android_url_playstore_inductor"),
    PLAYSTORE_OHMS("android_url_playstore_ohms"),
    PLAYSTORE_RESISTOR("android_url_playstore_resistor"),
    PLAYSTORE_DEVELOPER_PROFILE("android_url_playstore_developer_profile");
}
