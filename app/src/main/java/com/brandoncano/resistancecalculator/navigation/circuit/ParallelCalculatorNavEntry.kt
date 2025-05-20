package com.brandoncano.resistancecalculator.navigation.circuit

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.brandoncano.resistancecalculator.R
import com.brandoncano.resistancecalculator.model.circuit.CircuitViewModel
import com.brandoncano.resistancecalculator.navigation.Screen
import com.brandoncano.resistancecalculator.navigation.navigateToAbout
import com.brandoncano.resistancecalculator.ui.screens.circuit.CircuitCalculatorScreen

fun NavGraphBuilder.parallelCalculatorScreen(
    navHostController: NavHostController,
) {
    composable(
        route = Screen.ParallelCalculator.route,
        enterTransition = { slideInVertically(initialOffsetY = { it }) },
        exitTransition = { ExitTransition.None },
        popEnterTransition = { EnterTransition.None },
        popExitTransition = { slideOutVertically(targetOffsetY= { it }) },
    ) {
        val focusManager = LocalFocusManager.current
        val openMenu = remember { mutableStateOf(false) }
        val reset = remember { mutableStateOf(false) }
        val viewModel: CircuitViewModel = viewModel()
        val circuit by viewModel.circuit.collectAsState()

        CircuitCalculatorScreen(
            circuitTitle = R.string.circuit_title_parallel,
            circuitVector = R.drawable.img_resistors_parallel,
            vectorSize = Pair(301.dp, 177.dp),
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
                viewModel.updateValues(sameValues, resistorCount, units, false)
            },
        )
    }
}
