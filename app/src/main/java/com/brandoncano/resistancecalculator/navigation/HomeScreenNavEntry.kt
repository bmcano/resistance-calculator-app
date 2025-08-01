package com.brandoncano.resistancecalculator.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.brandoncano.resistancecalculator.ui.screens.HomeScreen
import com.brandoncano.resistancecalculator.util.SendFeedbackWrapper

fun NavGraphBuilder.homeScreen(
    navHostController: NavHostController,
    onOpenAppThemeDialog: () -> Unit,
) {
    composable(
        route = ResistorScreen.Home.route,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None },
    ) {
        val context = LocalContext.current
        HomeScreen(
            onFeedbackTapped = { SendFeedbackWrapper.execute(context) },
            onOpenAppThemeDialog = onOpenAppThemeDialog,
            onAboutTapped = { navigateToAbout(navHostController) },
            onColorToValueTapped = { navigateToColorToValue(navHostController) },
            onValueToColorTapped = { navigateToValueToColor(navHostController) },
            onSmdTapped = { navigateToSmd(navHostController) },
            onSeriesCalculatorTapped = { navigateToSeriesCalculator(navHostController) },
            onParallelCalculatorTapped = { navigateToParallelCalculator(navHostController) },
            onViewColorCodeIecTapped = { navigateToColorCodeIec(navHostController) },
            onViewPreferredValuesIecTapped = { navigateToPreferredValuesIec(navHostController) },
            onViewSmdCodeIecTapped = { navigateToSmdCodeIec(navHostController) },
            onViewCircuitEquationsTapped = { navigateToCircuitEquations(navHostController) },
            onRateThisAppTapped = { navigateToGooglePlay(context) },
            onViewOurAppsTapped = { navigateToOurApps(navHostController) },
            onDonateTapped = { navigateToDonate(navHostController) },
        )
    }
}
