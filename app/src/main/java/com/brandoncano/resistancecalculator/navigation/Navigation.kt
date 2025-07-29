package com.brandoncano.resistancecalculator.navigation

import android.content.Context
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.brandoncano.resistancecalculator.firebase.FirebaseAnalyticsEventDispatcher
import com.brandoncano.resistancecalculator.firebase.FirebaseAnalyticsEvent
import com.brandoncano.resistancecalculator.firebase.FirebaseRemoteConfigKeys
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
import com.brandoncano.resistancecalculator.util.OpenLink

/**
 * Note: Keep each navigation route in alphabetical order
 *
 * TODO - Convert to Nav3 when it is ready and stable
 */

@Composable
fun Navigation(onOpenAppThemeDialog: () -> Unit) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
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
        Log.e("Navigation", "Attempted navController.popBackStack(), but no BackStackEntry exists.")
    }
}

fun navigateToAbout(navController: NavHostController) {
    navController.navigate(Screen.About.route)
}

fun navigateToColorToValue(navController: NavHostController) {
    FirebaseAnalyticsEventDispatcher.execute(FirebaseAnalyticsEvent.SCREEN_COLOR_TO_VALUE)
    navController.navigate(Screen.ColorToValue.route) {
        popUpTo(Screen.Home.route)
    }
}

fun navigateToValueToColor(navController: NavHostController) {
    FirebaseAnalyticsEventDispatcher.execute(FirebaseAnalyticsEvent.SCREEN_VALUE_TO_COLOR)
    navController.navigate(Screen.ValueToColor.route) {
        popUpTo(Screen.Home.route)
    }
}

fun navigateToSmd(navController: NavHostController) {
    FirebaseAnalyticsEventDispatcher.execute(FirebaseAnalyticsEvent.SCREEN_SMD)
    navController.navigate(Screen.Smd.route) {
        popUpTo(Screen.Home.route)
    }
}

fun navigateToSeriesCalculator(navController: NavController) {
    FirebaseAnalyticsEventDispatcher.execute(FirebaseAnalyticsEvent.SCREEN_CIRCUIT_SERIES)
    navController.navigate(Screen.SeriesCalculator.route) {
        popUpTo(Screen.Home.route)
    }
}

fun navigateToParallelCalculator(navController: NavController) {
    FirebaseAnalyticsEventDispatcher.execute(FirebaseAnalyticsEvent.SCREEN_CIRCUIT_PARALLEL)
    navController.navigate(Screen.ParallelCalculator.route) {
        popUpTo(Screen.Home.route)
    }
}

fun navigateToCircuitEquations(navController: NavHostController) {
    FirebaseAnalyticsEventDispatcher.execute(FirebaseAnalyticsEvent.SCREEN_INFO_CIRCUIT_EQUATIONS)
    navController.navigate(Screen.LearnCircuitEquations.route)
}

fun navigateToColorCodeIec(navController: NavHostController) {
    FirebaseAnalyticsEventDispatcher.execute(FirebaseAnalyticsEvent.SCREEN_INFO_COLOR_CODES)
    navController.navigate(Screen.LearnColorCodes.route)
}

fun navigateToPreferredValuesIec(navController: NavHostController) {
    FirebaseAnalyticsEventDispatcher.execute(FirebaseAnalyticsEvent.SCREEN_INFO_PREFERRED_VALUES)
    navController.navigate(Screen.LearnPreferredValues.route)
}

fun navigateToSmdCodeIec(navController: NavHostController) {
    FirebaseAnalyticsEventDispatcher.execute(FirebaseAnalyticsEvent.SCREEN_INFO_SMD_CODES)
    navController.navigate(Screen.LearnSmdCodes.route)
}

fun navigateToOurApps(navController: NavHostController) {
    FirebaseAnalyticsEventDispatcher.execute(FirebaseAnalyticsEvent.SCREEN_VIEW_APPS)
    navController.navigate(Screen.ViewOurApps.route)
}

fun navigateToDonate(navController: NavHostController) {
    FirebaseAnalyticsEventDispatcher.execute(FirebaseAnalyticsEvent.SCREEN_DONATE)
    navController.navigate(Screen.Donate.route)
}

fun navigateToGooglePlay(context: Context) {
    val playstoreResistorLink = FirebaseRemoteConfigKeys.PLAYSTORE_RESISTOR.getStringOrEmpty()
    OpenLink.execute(context, playstoreResistorLink)
}
