package com.brandoncano.resistancecalculator.navigation.circuit

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.brandoncano.resistancecalculator.model.ResistorViewModelFactory
import com.brandoncano.resistancecalculator.model.led.LedCircuitViewModel
import com.brandoncano.resistancecalculator.navigation.Screen
import com.brandoncano.resistancecalculator.navigation.navigateToAbout
import com.brandoncano.resistancecalculator.ui.screens.led.LedResistanceCalculatorScreen

fun NavGraphBuilder.ledCalculatorScreen(
    navHostController: NavHostController,
) {
    composable(
        route = Screen.LedCalculator.route,
        enterTransition = { slideInVertically(initialOffsetY = { it }) },
        exitTransition = { ExitTransition.None },
        popEnterTransition = { EnterTransition.None },
        popExitTransition = { slideOutVertically(targetOffsetY= { it }) },
    ) {
        val context = LocalContext.current
        val focusManager = LocalFocusManager.current
        val openMenu = remember { mutableStateOf(false) }
        val reset = remember { mutableStateOf(false) }
        val viewModel: LedCircuitViewModel = viewModel(factory = ResistorViewModelFactory(context))
        val ledCircuit by viewModel.ledCircuitStateTOStateFlow.collectAsState()

        LedResistanceCalculatorScreen(
            ledCircuit = ledCircuit,
            isError = false,
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
            onValueChanged = { sourceVoltage, ledForwardVoltage, ledCurrent ->
                reset.value = false
                viewModel.updateValues(sourceVoltage, ledForwardVoltage, ledCurrent)
            },
            onOptionSelected = {
                reset.value = false
                viewModel.updateLedType(it)
            },
            onNavBarSelectionChanged = { viewModel.updateNavBarSelection(it)},
            onLearnMoreTapped = {},
        )
    }
}
