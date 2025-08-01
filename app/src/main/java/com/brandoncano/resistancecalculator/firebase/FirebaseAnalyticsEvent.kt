package com.brandoncano.resistancecalculator.firebase

/**
 * Job: Hold a list of analytic events
 *
 * Notes:
 * + Screens should be logged as "screen_<name>"
 * + Actions should be logged as "action_<name>"
 * + Event names can only contain number, letters, and underscores
 */
enum class FirebaseAnalyticsEvent(val analyticName: String) {
    // Screens
    SCREEN_COLOR_TO_VALUE("screen_color_to_value"),
    SCREEN_VALUE_TO_COLOR("screen_value_to_color"),
    SCREEN_SMD("screen_smd"),
    SCREEN_CIRCUIT_SERIES("screen_circuit_series"),
    SCREEN_CIRCUIT_PARALLEL("screen_circuit_parallel"),
    SCREEN_INFO_COLOR_CODES("screen_info_color_codes"),
    SCREEN_INFO_PREFERRED_VALUES("screen_info_preferred_values"),
    SCREEN_INFO_SMD_CODES("screen_info_smd_codes"),
    SCREEN_INFO_CIRCUIT_EQUATIONS("screen_info_circuit_equations"),
    SCREEN_DONATE("screen_donate"),
    SCREEN_VIEW_APPS("screen_view_apps"),

    // Actions
    ACTION_VALIDATE_E_SERIES("action_validate_e_series"),

}
