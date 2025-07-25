package com.brandoncano.resistancecalculator.keys

enum class SharedPreferencesKey(val key: String) {

    // App preferences
    KEY_APP_APPEARANCE("resistancecalculator.app.appearance"),
    // Language (if we ever add any)

    // Calculator data
    KEY_COLOR_TO_VALUE("resistancecalculator.color.to.value"),
    KEY_VALUE_TO_COLOR("resistancecalculator.value.to.color"),
    KEY_SMD("resistancecalculator.smd"),
    KEY_CIRCUIT_SERIES("resistancecalculator.circuit.series"),
    KET_CIRCUIT_PARALLEL("resistancecalculator.circuit.parallel")
}
