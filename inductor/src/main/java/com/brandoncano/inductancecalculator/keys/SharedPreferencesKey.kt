package com.brandoncano.inductancecalculator.keys

/**
 * Job: Defines all shared preference keys
 */
enum class SharedPreferencesKey(val key: String) {

    // App preferences
    KEY_RESET_PREFERENCES("com.brandoncano.inductancecalculator.reset.preferences.01"),
    KEY_APP_APPEARANCE("com.brandoncano.inductancecalculator.app.appearance"),
    KEY_DYNAMIC_COLOR("com.brandoncano.inductancecalculator.dynamic.color"), // Android 12+
    // KEY_LANGUAGE("$com.brandoncano.inductancecalculator.language"), // If we add it

    // Calculator data
    KEY_COLOR_TO_VALUE("com.brandoncano.inductancecalculator.color.to.value"),
    KEY_VALUE_TO_COLOR("com.brandoncano.inductancecalculator.value.to.color"),
    KEY_SMD_INDUCTOR("com.brandoncano.inductancecalculator.smd.inductor"),
}
