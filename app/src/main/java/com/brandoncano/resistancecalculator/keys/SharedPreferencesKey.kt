package com.brandoncano.resistancecalculator.keys

enum class SharedPreferencesKey(val key: String) {

    // App preferences
    KEY_APP_APPEARANCE("resistancecalculator.app.appearance"),
    // Language (if we ever add any)

    // Calculator data
    KEY_COLOR_TO_VALUE("resistancecalculator.color.to.value")
}
