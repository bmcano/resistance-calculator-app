package com.brandoncano.resistancecalculator.keys

import com.brandoncano.resistancecalculator.constants.Links

enum class SharedPreferencesKey(val key: String) {

    // App preferences
    KEY_APP_APPEARANCE("${Links.APPLICATION_ID}.app.appearance"),
    KEY_DYNAMIC_COLOR("${Links.APPLICATION_ID}.dynamic.color"), // Android 12+
    // KEY_LANGUAGE("${Links.APPLICATION_ID}.language"), // If we add it

    // Calculator data
    KEY_COLOR_TO_VALUE("${Links.APPLICATION_ID}.color.to.value"),
    KEY_VALUE_TO_COLOR("${Links.APPLICATION_ID}.value.to.color"),
    KEY_SMD_RESISTOR("${Links.APPLICATION_ID}.smd.resistor"),
    KEY_CIRCUIT("${Links.APPLICATION_ID}.circuit"),
}
