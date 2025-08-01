package com.brandoncano.resistancecalculator.navigation

/**
 * Note: Keep screens in alphabetical order
 */
sealed class ResistorScreen(val route: String) {
    data object About : ResistorScreen("resistor/about")
    data object ColorToValue: ResistorScreen("resistor/color_to_value")
    data object Donate : ResistorScreen("resistor/donate")
    data object Home : ResistorScreen("resistor/home")
    data object LearnCircuitEquations : ResistorScreen("resistor/circuit/learn")
    data object LearnColorCodes : ResistorScreen("resistor/color_to_value/learn")
    data object LearnPreferredValues : ResistorScreen("resistor/value_to_color/learn")
    data object LearnSmdCodes : ResistorScreen("resistor/smd/learn")
    data object ParallelCalculator : ResistorScreen("resistor/parallel_calculator")
    data object Smd : ResistorScreen("resistor/smd")
    data object SeriesCalculator : ResistorScreen("resistor/series_calculator")
    data object ValueToColor : ResistorScreen("resistor/value_to_color")
    data object ViewOurApps : ResistorScreen("resistor/view_our_apps")
}
