package com.brandoncano.resistancecalculator.navigation.calculators

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.brandoncano.resistancecalculator.R
import com.brandoncano.resistancecalculator.model.CircuitViewModel
import com.brandoncano.resistancecalculator.navigation.Screen
import com.brandoncano.resistancecalculator.navigation.navigateToAbout
import com.brandoncano.resistancecalculator.navigation.navigateToCircuitEquations
import com.brandoncano.resistancecalculator.navigation.popBackStackSafely
import com.brandoncano.resistancecalculator.ui.screens.calculators.CircuitCalculatorScreen
import com.brandoncano.resistancecalculator.util.SendFeedback

fun NavGraphBuilder.seriesCalculatorScreen(
    navHostController: NavHostController,
) {
    composable(
        route = Screen.SeriesCalculator.route,
        enterTransition = { slideInHorizontally(initialOffsetX = { it }) },
        exitTransition = { ExitTransition.None },
        popEnterTransition = { EnterTransition.None },
        popExitTransition = { slideOutHorizontally(targetOffsetX = { it }) },
    ) {
        val context = LocalContext.current
        val focusManager = LocalFocusManager.current
        val viewModel: CircuitViewModel = viewModel()
        val circuit by viewModel.circuitStateTOStateFlow.collectAsState()
        // We need to call this update because of shared preferences being used for both
        viewModel.updateValues(circuit.isSameValues, circuit.resistorCount, circuit.units, true)

        CircuitCalculatorScreen(
            circuitTitle = R.string.circuit_title_series,
            circuitVector = R.drawable.img_resistors_series,
            vectorSize = Pair(390.dp, 106.dp),
            circuit = circuit,
            onNavigateBack = { popBackStackSafely(navHostController) },
            onClearSelectionsTapped = {
                viewModel.clear()
                focusManager.clearFocus()
            },
            onFeedbackTapped = { SendFeedback.execute(context) },
            onAboutTapped = { navigateToAbout(navHostController) },
            onOptionSelected = { sameValues, resistorCount, units ->
                viewModel.updateValues(sameValues, resistorCount, units, true)
            },
            onValueChange = { resistance, index ->
                viewModel.updateResistorInput(resistance, index, true)
            },
            onLearnCircuitEquationsTapped = { navigateToCircuitEquations(navHostController) },
        )
    }
}
