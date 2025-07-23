package com.brandoncano.resistancecalculator.keys

enum class AppAppearance {
    SYSTEM_DEFAULT,
    LIGHT,
    DARK;

    fun displayText(): String {
        return when (this) {
            SYSTEM_DEFAULT -> "System default"
            LIGHT -> "Light theme"
            DARK -> "Dark theme"
        }
    }
}
