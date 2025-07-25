package com.brandoncano.resistancecalculator.keys

enum class SharedPreferencesKey(val key: String) {

    // App preferences
    KEY_APP_APPEARANCE("resistancecalculator.app.appearance"),
    KEY_MATERIAL_YOU("resistancecalculator.material.you"),
    KEY_LANGUAGE("resistancecalculator.language"), // If we add it

    // Calculator data
    KEY_COLOR_TO_VALUE("resistancecalculator.color.to.value"),
    KEY_VALUE_TO_COLOR("resistancecalculator.value.to.color"),
    KEY_SMD_RESISTOR("resistancecalculator.smd.resistor"),
    KEY_CIRCUIT("resistancecalculator.circuit"),
}
