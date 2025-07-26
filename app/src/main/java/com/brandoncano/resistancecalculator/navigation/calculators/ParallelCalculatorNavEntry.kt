package com.brandoncano.resistancecalculator.navigation.calculators

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
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
import com.brandoncano.resistancecalculator.constants.Links
import com.brandoncano.resistancecalculator.model.CircuitViewModel
import com.brandoncano.resistancecalculator.navigation.Screen
import com.brandoncano.resistancecalculator.navigation.navigateToAbout
import com.brandoncano.resistancecalculator.navigation.navigateToCircuitEquations
import com.brandoncano.resistancecalculator.ui.screens.calculators.CircuitCalculatorScreen
import com.brandoncano.sharedcomponents.utils.SendFeedback

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
        val context = LocalContext.current
        val focusManager = LocalFocusManager.current
        val viewModel: CircuitViewModel = viewModel<CircuitViewModel>()
        val circuit by viewModel.circuitStateTOStateFlow.collectAsState()
        // We need to call this update because of shared preferences being used for both
        viewModel.updateValues(circuit.isSameValues, circuit.resistorCount, circuit.units, false)

        CircuitCalculatorScreen(
            circuitTitle = R.string.circuit_title_parallel,
            circuitVector = R.drawable.img_resistors_parallel,
            vectorSize = Pair(301.dp, 177.dp),
            circuit = circuit,
            onNavigateBack = { navHostController.popBackStack() },
            onClearSelectionsTapped = {
                viewModel.clear()
                focusManager.clearFocus()
            },
            onFeedbackTapped = { SendFeedback.execute(context, Links.APP_NAME) },
            onAboutTapped = { navigateToAbout(navHostController) },
            onOptionSelected = { sameValues, resistorCount, units ->
                viewModel.updateValues(sameValues, resistorCount, units, false)
            },
            onLearnCircuitEquationsTapped = { navigateToCircuitEquations(navHostController) },
        )
    }
}
