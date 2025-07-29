package com.brandoncano.resistancecalculator.navigation

/**
 * Note: Keep screens in alphabetical order
 */
sealed class Screen(val route: String) {
    data object About : Screen("about")
    data object ColorToValue: Screen("color_to_value")
    data object Donate : Screen("donate")
    data object Home : Screen("home")
    data object LearnCircuitEquations : Screen("circuit/learn")
    data object LearnColorCodes : Screen("color_to_value/learn")
    data object LearnPreferredValues : Screen("value_to_color/learn")
    data object LearnSmdCodes : Screen("smd/learn")
    data object ParallelCalculator : Screen("parallel_calculator")
    data object Smd : Screen("smd")
    data object SeriesCalculator : Screen("series_calculator")
    data object ValueToColor : Screen("value_to_color")
    data object ViewOurApps : Screen("view_our_apps")
}
