package com.brandoncano.resistancecalculator.navigation.calculators

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
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
import com.brandoncano.resistancecalculator.model.ctv.ResistorCtvViewModel
import com.brandoncano.resistancecalculator.navigation.Screen
import com.brandoncano.resistancecalculator.navigation.navigateToAbout
import com.brandoncano.resistancecalculator.navigation.navigateToColorCodeIec
import com.brandoncano.resistancecalculator.ui.screens.ctv.ColorToValueScreen

fun NavGraphBuilder.colorToValueScreen(
    navHostController: NavHostController,
) {
    composable(
        route = Screen.ColorToValue.route,
        enterTransition = { slideInHorizontally(initialOffsetX = { it }) }, // from right
        exitTransition = { slideOutHorizontally(targetOffsetX = { -it }) }, // to left
        popEnterTransition = { slideInHorizontally(initialOffsetX = { -it }) }, // from left (back nav)
        popExitTransition = { slideOutHorizontally(targetOffsetX = { it }) }, // to right (back nav)
    ) {
        val context = LocalContext.current
        val focusManager = LocalFocusManager.current
        val openMenu = remember { mutableStateOf(false) }
        val reset = remember { mutableStateOf(false) }
        val viewModel: ResistorCtvViewModel = viewModel(factory = ResistorViewModelFactory(context))
        val resistor by viewModel.resistorStateTOStateFlow.collectAsState()

        ColorToValueScreen(
            openMenu = openMenu,
            reset = reset,
            resistor = resistor,
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
            onUpdateBand = { bandNumber, color ->
                reset.value = false
                viewModel.updateBand(bandNumber, color)
            },
            onNavBarSelectionChanged = { selection ->
                viewModel.updateNavBarSelection(selection)
            },
            onLearnColorCodesTapped = { navigateToColorCodeIec(navHostController) },
        )
    }
}
