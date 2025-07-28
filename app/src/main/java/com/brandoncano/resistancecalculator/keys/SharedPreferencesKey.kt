package com.brandoncano.resistancecalculator.keys

import com.brandoncano.resistancecalculator.BuildConfig

enum class SharedPreferencesKey(val key: String) {

    // App preferences
    KEY_APP_APPEARANCE("${BuildConfig.APPLICATION_ID}.app.appearance"),
    KEY_DYNAMIC_COLOR("${BuildConfig.APPLICATION_ID}.dynamic.color"), // Android 12+
    // KEY_LANGUAGE("${BuildConfig.APPLICATION_ID}.language"), // If we add it

    // Calculator data
    KEY_COLOR_TO_VALUE("${BuildConfig.APPLICATION_ID}.color.to.value"),
    KEY_VALUE_TO_COLOR("${BuildConfig.APPLICATION_ID}.value.to.color"),
    KEY_SMD_RESISTOR("${BuildConfig.APPLICATION_ID}.smd.resistor"),
    KEY_CIRCUIT("${BuildConfig.APPLICATION_ID}.circuit");
}
