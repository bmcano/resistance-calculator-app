package com.brandoncano.resistancecalculator.navigation.calculators

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
import com.brandoncano.resistancecalculator.model.smd.SmdResistorViewModel
import com.brandoncano.resistancecalculator.navigation.Screen
import com.brandoncano.resistancecalculator.navigation.navigateToAbout
import com.brandoncano.resistancecalculator.navigation.navigateToSmdCodeIec
import com.brandoncano.resistancecalculator.ui.screens.smd.SmdScreen

fun NavGraphBuilder.smdScreen(
    navHostController: NavHostController,
) {
    composable(
        route = Screen.Smd.route,
        enterTransition = { slideInVertically(initialOffsetY = { it }) },
        exitTransition = { ExitTransition.None },
        popEnterTransition = { EnterTransition.None },
        popExitTransition = { slideOutVertically(targetOffsetY = { it }) },
    ) {
        val context = LocalContext.current
        val focusManager = LocalFocusManager.current
        val openMenu = remember { mutableStateOf(false) }
        val reset = remember { mutableStateOf(false) }
        val viewModel: SmdResistorViewModel = viewModel(factory = ResistorViewModelFactory(context))
        val resistor by viewModel.resistorStateTOStateFlow.collectAsState()
        val isError by viewModel.isErrorStateFlow.collectAsState()

        SmdScreen(
            openMenu = openMenu,
            reset = reset,
            resistor = resistor,
            isError = isError,
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
            onValueChanged = { code, units, clearFocus ->
                reset.value = false
                viewModel.updateValues(code, units)
                if (clearFocus) focusManager.clearFocus()
            },
            onNavBarSelectionChanged = { viewModel.updateNavBarSelection(it) },
            onLearnSmdCodesTapped = { navigateToSmdCodeIec(navHostController) },
        )
    }
}
