package com.brandoncano.resistancecalculator.navigation

import android.content.Context
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.brandoncano.resistancecalculator.constants.Links
import com.brandoncano.resistancecalculator.navigation.calculators.colorToValueScreen
import com.brandoncano.resistancecalculator.navigation.calculators.smdScreen
import com.brandoncano.resistancecalculator.navigation.calculators.valueToColorScreen
import com.brandoncano.resistancecalculator.navigation.circuit.seriesCalculatorScreen
import com.brandoncano.resistancecalculator.navigation.learn.learnColorCodes
import com.brandoncano.resistancecalculator.navigation.learn.learnPreferredValues
import com.brandoncano.resistancecalculator.navigation.learn.learnSmdCodes
import com.brandoncano.sharedcomponents.data.Apps
import com.brandoncano.sharedcomponents.screen.ViewOurAppsScreen
import com.brandoncano.sharedcomponents.utils.OpenLink

/**
 * Note: Keep each navigation route in alphabetical order
 */

@Composable
fun Navigation(onOpenThemeDialog: () -> Unit) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        aboutScreen(navController)
        colorToValueScreen(navController, onOpenThemeDialog)
        homeScreen(navController, onOpenThemeDialog)
        learnColorCodes(navController)
        learnPreferredValues(navController)
        learnSmdCodes(navController)
        seriesCalculatorScreen(navController)
        smdScreen(navController, onOpenThemeDialog)
        valueToColorScreen(navController, onOpenThemeDialog)
        // from shared library
        composable(
            route = Screen.ViewOurApps.route,
            enterTransition = { slideInVertically(initialOffsetY = { it }) },
            exitTransition = { slideOutVertically(targetOffsetY = { it }) },
        ) {
            ViewOurAppsScreen(
                context = LocalContext.current,
                app = Apps.Resistor,
                onNavigateBack = { navController.popBackStack() },
            )
        }
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

fun navigateToGooglePlay(context: Context) {
    OpenLink.execute(context, Links.RESISTOR_PLAYSTORE)
}
