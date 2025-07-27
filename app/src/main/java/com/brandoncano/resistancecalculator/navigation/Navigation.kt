package com.brandoncano.resistancecalculator.navigation

import android.content.Context
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.brandoncano.resistancecalculator.constants.Links
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
    navController.navigate(Screen.ViewOurApps.route)
}

fun navigateToDonate(navController: NavHostController) {
    navController.navigate(Screen.Donate.route)
}

fun navigateToGooglePlay(context: Context) {
    OpenLink.execute(context, Links.RESISTOR_PLAYSTORE)
}
