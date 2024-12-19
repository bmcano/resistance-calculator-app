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
import com.brandoncano.resistancecalculator.R
import com.brandoncano.resistancecalculator.navigation.Screen
import com.brandoncano.resistancecalculator.navigation.navigateToAbout
import com.brandoncano.resistancecalculator.ui.screens.circuit.CircuitCalculatorScreen
import com.brandoncano.resistancecalculator.util.circuit.TotalResistanceParallel

fun NavGraphBuilder.parallelCalculatorScreen(
    navHostController: NavHostController,
) {
    composable(
        route = Screen.ParallelCalculator.route,
        enterTransition = { slideInHorizontally(initialOffsetX = { it }) },
        exitTransition = { slideOutHorizontally(targetOffsetX = { it }) },
    ) {
        val focusManager = LocalFocusManager.current
        val openMenu = remember { mutableStateOf(false) }
        val reset = remember { mutableStateOf(false) }
        var totalResistance = remember { mutableStateOf("0.0") }
        val resistorInputs = remember { mutableStateListOf(*Array(8) { "" }) }

        CircuitCalculatorScreen(
            circuitVector = R.drawable.img_parallel_resistors,
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
                    TotalResistanceParallel.execute(
                        numberOfResistors = resistorCount,
                        resistorValue = resistorInputs[0]
                    )
                } else {
                    TotalResistanceParallel.execute(
                        resistorValues = resistorInputs
                            .take(resistorCount)
                            .filter { it.isNotEmpty() }
                    )
                }
            },
            totalResistance = totalResistance,
            resistorInputs = resistorInputs,
        )
    }
}
