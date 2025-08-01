package com.brandoncano.library.firebase

/**
 * Job: Hold a list of analytic events
 *
 * Notes:
 * + Screens should be logged as "screen_<name>"
 * + Actions should be logged as "action_<name>"
 * + Event names can only contain number, letters, and underscores
 */
enum class FirebaseAnalyticsEvent(val analyticName: String) {
    // Screens - Resistor
    SCREEN_RESISTOR_ABOUT("resistor_about"),
    SCREEN_RESISTOR_COLOR_TO_VALUE("resistor_color_to_value"),
    SCREEN_RESISTOR_VALUE_TO_COLOR("resistor_value_to_color"),
    SCREEN_RESISTOR_SMD("resistor_smd"),
    SCREEN_RESISTOR_CIRCUIT_SERIES("resistor_circuit_series"),
    SCREEN_RESISTOR_CIRCUIT_PARALLEL("resistor_circuit_parallel"),
    SCREEN_RESISTOR_INFO_COLOR_CODES("resistor_info_color_codes"),
    SCREEN_RESISTOR_INFO_PREFERRED_VALUES("resistor_info_preferred_values"),
    SCREEN_RESISTOR_INFO_SMD_CODES("resistor_info_smd_codes"),
    SCREEN_RESISTOR_INFO_CIRCUIT_EQUATIONS("resistor_info_circuit_equations"),
    SCREEN_RESISTOR_DONATE("resistor_donate"),
    SCREEN_RESISTOR_VIEW_APPS("resistor_view_apps"),
    // Screens - Inductor
    SCREEN_INDUCTOR_ABOUT("inductor_about"),
    SCREEN_INDUCTOR_COLOR_TO_VALUE("inductor_color_to_value"),
    SCREEN_INDUCTOR_VALUE_TO_COLOR("inductor_value_to_color"),
    SCREEN_INDUCTOR_SMD("inductor_smd"),
    SCREEN_INDUCTOR_INFO_COLOR_CODES("inductor_info_color_codes"),
    SCREEN_INDUCTOR_INFO_SMD_CODES("inductor_info_smd_codes"),
    SCREEN_INDUCTOR_DONATE("inductor_donate"),
    SCREEN_INDUCTOR_VIEW_APPS("inductor_view_apps"),
    // Screens - Capacitor
    // Screens - Ohms Law

    // Actions
    ACTION_VALIDATE_E_SERIES("action_validate_e_series"),
}
