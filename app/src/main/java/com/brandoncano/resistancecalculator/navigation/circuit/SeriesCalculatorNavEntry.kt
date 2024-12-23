package com.brandoncano.resistancecalculator.navigation.circuit

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalFocusManager
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.brandoncano.resistancecalculator.R
import com.brandoncano.resistancecalculator.model.circuit.CircuitViewModel
import com.brandoncano.resistancecalculator.navigation.Screen
import com.brandoncano.resistancecalculator.navigation.navigateToAbout
import com.brandoncano.resistancecalculator.ui.screens.circuit.CircuitCalculatorScreen

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
        val viewModel: CircuitViewModel = viewModel()
        val circuit by viewModel.circuit.collectAsState()

        CircuitCalculatorScreen(
            circuitTitle = R.string.title_series_resistors,
            circuitVector = R.drawable.img_series_resistors,
            circuit = circuit,
            openMenu = openMenu,
            reset = reset,
            onNavigateBack = { navHostController.popBackStack() },
            onClearSelectionsTapped = {
                openMenu.value = false
                reset.value = true
                viewModel.clear()
                focusManager.clearFocus()
            },
            onAboutTapped = {
                openMenu.value = false
                navigateToAbout(navHostController)
            },
            onValueChanged = { sameValues, resistorCount, units ->
                reset.value = false
                viewModel.updateValues(sameValues, resistorCount, units, true)
            },
        )
    }
}
