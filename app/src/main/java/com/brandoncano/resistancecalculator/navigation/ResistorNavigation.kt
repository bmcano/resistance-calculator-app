package com.brandoncano.resistancecalculator.navigation

import android.content.Context
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.brandoncano.resistancecalculator.firebase.FirebaseAnalyticsScreenLogger
import com.brandoncano.resistancecalculator.firebase.FirebaseAnalyticsEvent
import com.brandoncano.resistancecalculator.keys.FirebaseRemoteConfigKeys
import com.brandoncano.resistancecalculator.firebase.getStringOrEmpty
import com.brandoncano.resistancecalculator.navigation.calculators.colorToValueScreen
import com.brandoncano.resistancecalculator.navigation.calculators.parallelCalculatorScreen
import com.brandoncano.resistancecalculator.navigation.calculators.seriesCalculatorScreen
import com.brandoncano.resistancecalculator.navigation.calculators.smdScreen
import com.brandoncano.resistancecalculator.navigation.calculators.valueToColorScreen
import com.brandoncano.resistancecalculator.navigation.learn.learnCircuitEquations
import com.brandoncano.resistancecalculator.navigation.learn.learnColorCodes
import com.brandoncano.resistancecalculator.navigation.learn.learnPreferredValues
import com.brandoncano.resistancecalculator.navigation.learn.learnSmdCodes
import com.brandoncano.library.util.OpenLink

/**
 * Note: Keep each navigation route in alphabetical order
 *
 * TODO - Convert to Nav3 when it is ready and stable
 */

private const val TAG = "Navigation"

@Composable
fun ResistorNavigation(onOpenAppThemeDialog: () -> Unit) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = ResistorScreen.Home.route,
    ) {
        aboutScreen(navController)
        colorToValueScreen(navController)
        donateScreen(navController)
        homeScreen(navController, onOpenAppThemeDialog)
        learnCircuitEquations(navController)
        learnColorCodes(navController)
        learnPreferredValues(navController)
        learnSmdCodes(navController)
        parallelCalculatorScreen(navController)
        seriesCalculatorScreen(navController)
        smdScreen(navController)
        valueToColorScreen(navController)
        viewOurAppsScreen(navController)
    }
}

fun popBackStackSafely(navController: NavHostController) {
    if (navController.previousBackStackEntry != null) {
        navController.popBackStack()
    } else {
        Log.e(TAG, "Attempted navController.popBackStack(), but no BackStackEntry exists.")
    }
}

fun navigateToAbout(navController: NavHostController) {
    navController.navigate(ResistorScreen.About.route)
}

fun navigateToColorToValue(navController: NavHostController) {
    FirebaseAnalyticsScreenLogger.execute(FirebaseAnalyticsEvent.SCREEN_COLOR_TO_VALUE)
    navController.navigate(ResistorScreen.ColorToValue.route)
}

fun navigateToValueToColor(navController: NavHostController) {
    FirebaseAnalyticsScreenLogger.execute(FirebaseAnalyticsEvent.SCREEN_VALUE_TO_COLOR)
    navController.navigate(ResistorScreen.ValueToColor.route)
}

fun navigateToSmd(navController: NavHostController) {
    FirebaseAnalyticsScreenLogger.execute(FirebaseAnalyticsEvent.SCREEN_SMD)
    navController.navigate(ResistorScreen.Smd.route)
}

fun navigateToSeriesCalculator(navController: NavController) {
    FirebaseAnalyticsScreenLogger.execute(FirebaseAnalyticsEvent.SCREEN_CIRCUIT_SERIES)
    navController.navigate(ResistorScreen.SeriesCalculator.route)
}

fun navigateToParallelCalculator(navController: NavController) {
    FirebaseAnalyticsScreenLogger.execute(FirebaseAnalyticsEvent.SCREEN_CIRCUIT_PARALLEL)
    navController.navigate(ResistorScreen.ParallelCalculator.route)
}

fun navigateToCircuitEquations(navController: NavHostController) {
    FirebaseAnalyticsScreenLogger.execute(FirebaseAnalyticsEvent.SCREEN_INFO_CIRCUIT_EQUATIONS)
    navController.navigate(ResistorScreen.LearnCircuitEquations.route)
}

fun navigateToColorCodeIec(navController: NavHostController) {
    FirebaseAnalyticsScreenLogger.execute(FirebaseAnalyticsEvent.SCREEN_INFO_COLOR_CODES)
    navController.navigate(ResistorScreen.LearnColorCodes.route)
}

fun navigateToPreferredValuesIec(navController: NavHostController) {
    FirebaseAnalyticsScreenLogger.execute(FirebaseAnalyticsEvent.SCREEN_INFO_PREFERRED_VALUES)
    navController.navigate(ResistorScreen.LearnPreferredValues.route)
}

fun navigateToSmdCodeIec(navController: NavHostController) {
    FirebaseAnalyticsScreenLogger.execute(FirebaseAnalyticsEvent.SCREEN_INFO_SMD_CODES)
    navController.navigate(ResistorScreen.LearnSmdCodes.route)
}

fun navigateToOurApps(navController: NavHostController) {
    FirebaseAnalyticsScreenLogger.execute(FirebaseAnalyticsEvent.SCREEN_VIEW_APPS)
    navController.navigate(ResistorScreen.ViewOurApps.route)
}

fun navigateToDonate(navController: NavHostController) {
    FirebaseAnalyticsScreenLogger.execute(FirebaseAnalyticsEvent.SCREEN_DONATE)
    navController.navigate(ResistorScreen.Donate.route)
}

fun navigateToGooglePlay(context: Context) {
    val playstoreResistorLink = FirebaseRemoteConfigKeys.PLAYSTORE_RESISTOR.getStringOrEmpty()
    OpenLink.execute(context, playstoreResistorLink)
}
