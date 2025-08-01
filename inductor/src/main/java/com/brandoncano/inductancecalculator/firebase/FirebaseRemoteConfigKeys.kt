package com.brandoncano.inductancecalculator.firebase

/**
 * Job: Defined the remote config keys from the Firebase console.
 */
enum class FirebaseRemoteConfigKeys(val pathName: String) {
    PRIVACY_POLICY("android_url_privacy_policy_inductor"),
    PLAYSTORE_RESISTOR("android_url_playstore_resistor"),
    PLAYSTORE_CAPACITOR("android_url_playstore_capacitor"),
    PLAYSTORE_INDUCTOR("android_url_playstore_inductor"),
    PLAYSTORE_OHMS("android_url_playstore_ohms"),
    PLAYSTORE_DEVELOPER_PROFILE("android_url_playstore_developer_profile");
}
