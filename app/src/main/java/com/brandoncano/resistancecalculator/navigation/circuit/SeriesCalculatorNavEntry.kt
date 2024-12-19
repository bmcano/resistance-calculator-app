package com.brandoncano.resistancecalculator.navigation.circuit

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalFocusManager
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.brandoncano.resistancecalculator.navigation.Screen
import com.brandoncano.resistancecalculator.navigation.navigateToAbout
import com.brandoncano.resistancecalculator.ui.screens.circuit.SeriesCalculatorScreen
import com.brandoncano.resistancecalculator.util.circuit.TotalResistanceSeries

fun NavGraphBuilder.seriesCalculatorScreen(
    navHostController: NavHostController,
) {
    composable(
        route = Screen.SeriesCalculator.route,
        enterTransition = { slideInHorizontally(initialOffsetX = { it }) },
        exitTransition = { slideOutHorizontally(targetOffsetX = { it }) },
    ) {
        val focusManager = LocalFocusManager.current
        val openMenu = remember { mutableStateOf(false) }
        val reset = remember { mutableStateOf(false) }
        var totalResistance = remember { mutableStateOf("0.0") }
        val resistorInputs = remember { mutableStateListOf(*Array(8) { "" }) }

        SeriesCalculatorScreen(
            openMenu = openMenu,
            reset = reset,
            onNavigateBack = { navHostController.popBackStack() },
            onClearSelectionsTapped = {
                openMenu.value = false
                reset.value = true
                totalResistance.value = "0.0"
                focusManager.clearFocus()
            },
            onAboutTapped = {
                openMenu.value = false
                navigateToAbout(navHostController)
            },
            onValueChanged = { sameValues, resistorCount ->
                totalResistance.value = if (sameValues) {
                    TotalResistanceSeries.execute(
                        numberOfResistors = resistorCount,
                        resistorValue = resistorInputs[0]
                    )
                } else {
                    TotalResistanceSeries.execute(
                        resistorValues = resistorInputs.take(resistorCount)
                            .filter { it.isNotEmpty() }
                    )
                }
            },
            totalResistance = totalResistance,
            resistorInputs = resistorInputs,
        )
    }
}


