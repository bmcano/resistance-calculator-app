package com.brandoncano.resistancecalculator.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.brandoncano.resistancecalculator.constants.Links
import com.brandoncano.resistancecalculator.navigation.calculators.colorToValueScreen
import com.brandoncano.resistancecalculator.navigation.calculators.smdScreen
import com.brandoncano.resistancecalculator.navigation.calculators.valueToColorScreen
import com.brandoncano.resistancecalculator.navigation.circuit.ledCalculatorScreen
import com.brandoncano.resistancecalculator.navigation.circuit.parallelCalculatorScreen
import com.brandoncano.resistancecalculator.navigation.circuit.seriesCalculatorScreen
import com.brandoncano.resistancecalculator.navigation.learn.learnCircuitEquations
import com.brandoncano.resistancecalculator.navigation.learn.learnColorCodes
import com.brandoncano.resistancecalculator.navigation.learn.learnPreferredValues
import com.brandoncano.resistancecalculator.navigation.learn.learnSmdCodes
import com.brandoncano.sharedcomponents.data.Apps
import com.brandoncano.sharedcomponents.navigation.SharedScreens
import com.brandoncano.sharedcomponents.navigation.donateScreen
import com.brandoncano.sharedcomponents.navigation.viewOurAppsScreen
import com.brandoncano.sharedcomponents.utils.OpenLink

/**
 * Note: Keep each navigation route in alphabetical order
 */

@Composable
fun Navigation(onOpenThemeDialog: () -> Unit) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
    ) {
        aboutScreen(navController)
        colorToValueScreen(navController)
        homeScreen(navController, onOpenThemeDialog)
        learnCircuitEquations(navController)
        learnColorCodes(navController)
        learnPreferredValues(navController)
        learnSmdCodes(navController)
        ledCalculatorScreen(navController)
        parallelCalculatorScreen(navController)
        seriesCalculatorScreen(navController)
        smdScreen(navController)
        valueToColorScreen(navController)
        // from shared library
        donateScreen(navController)
        viewOurAppsScreen(navController, Apps.Resistor)
    }
}

fun navigateToAbout(navController: NavHostController) {
    navController.navigate(Screen.About.route)
}

fun navigateToColorToValue(navController: NavHostController) {
    navController.navigate(Screen.ColorToValue.route) {
        popUpTo(Screen.Home.route)
    }
}

fun navigateToValueToColor(navController: NavHostController) {
    navController.navigate(Screen.ValueToColor.route) {
        popUpTo(Screen.Home.route)
    }
}

fun navigateToSmd(navController: NavHostController) {
    navController.navigate(Screen.Smd.route) {
        popUpTo(Screen.Home.route)
    }
}

fun navigateToSeriesCalculator(navController: NavController) {
    navController.navigate(Screen.SeriesCalculator.route) {
        popUpTo(Screen.Home.route)
    }
}

fun navigateToParallelCalculator(navController: NavController) {
    navController.navigate(Screen.ParallelCalculator.route) {
        popUpTo(Screen.Home.route)
    }
}

fun navigateToLedCalculator(navController: NavHostController) {
    navController.navigate(Screen.LedCalculator.route) {
        popUpTo(Screen.Home.route)
    }
}

fun navigateToCircuitEquations(navController: NavHostController) {
    navController.navigate(Screen.LearnCircuitEquations.route)
}

fun navigateToColorCodeIec(navController: NavHostController) {
    navController.navigate(Screen.LearnColorCodes.route)
}

fun navigateToPreferredValuesIec(navController: NavHostController) {
    navController.navigate(Screen.LearnPreferredValues.route)
}

fun navigateToSmdCodeIec(navController: NavHostController) {
    navController.navigate(Screen.LearnSmdCodes.route)
}

fun navigateToOurApps(navController: NavHostController) {
    navController.navigate(SharedScreens.ViewOurApps.route)
}

fun navigateToDonate(navController: NavHostController) {
    navController.navigate(SharedScreens.Donate.route)
}

fun navigateToGooglePlay(context: Context) {
    OpenLink.execute(context, Links.RESISTOR_PLAYSTORE)
}
