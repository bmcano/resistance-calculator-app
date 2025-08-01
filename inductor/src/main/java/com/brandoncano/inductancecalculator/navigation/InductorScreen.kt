package com.brandoncano.inductancecalculator.navigation

/**
 * Note: Keep screens in alphabetical order
 */
sealed class InductorScreen(val route: String) {
    data object About : InductorScreen("inductor/about")
    data object ColorToValue: InductorScreen("inductor/color_to_value")
    data object Donate : InductorScreen("inductor/donate")
    data object Home : InductorScreen("inductor/home")
    data object LearnColorCodes : InductorScreen("inductor/color_to_value/learn")
    data object LearnSmdCodes : InductorScreen("inductor/smd/learn")
    data object Smd: InductorScreen("inductor/smd")
    data object ValueToColor: InductorScreen("inductor/value_to_color")
    data object ViewOurApps : InductorScreen("inductor/view_our_apps")
}
