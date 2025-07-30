package com.brandoncano.resistancecalculator.keys

/**
 * Job: Defines all shared preference keys
 */
enum class SharedPreferencesKey(val key: String) {

    // App preferences
    KEY_RESET_PREFERENCES("com.brandoncano.resistancecalculator.reset.preferences.01"),
    KEY_APP_APPEARANCE("com.brandoncano.resistancecalculator.app.appearance"),
    KEY_DYNAMIC_COLOR("com.brandoncano.resistancecalculator.dynamic.color"), // Android 12+
    // KEY_LANGUAGE("$com.brandoncano.resistancecalculator.language"), // If we add it

    // Calculator data
    KEY_COLOR_TO_VALUE("com.brandoncano.resistancecalculator.color.to.value"),
    KEY_VALUE_TO_COLOR("com.brandoncano.resistancecalculator.value.to.color"),
    KEY_SMD_RESISTOR("com.brandoncano.resistancecalculator.smd.resistor"),
    KEY_CIRCUIT("com.brandoncano.resistancecalculator.circuit");
}
