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
import com.brandoncano.resistancecalculator.model.smd.SmdResistorViewModel
import com.brandoncano.resistancecalculator.navigation.Screen
import com.brandoncano.resistancecalculator.navigation.navigateToAbout
import com.brandoncano.resistancecalculator.navigation.navigateToSmdCodeIec
import com.brandoncano.resistancecalculator.ui.screens.smd.SmdScreen

fun NavGraphBuilder.smdScreen(
    navHostController: NavHostController,
    onOpenThemeDialog: () -> Unit,
) {
    composable(
        route = Screen.Smd.route,
        enterTransition = { slideInHorizontally(initialOffsetX = { it }) },
        exitTransition = { slideOutHorizontally(targetOffsetX = { it }) },
    ) {
        val context = LocalContext.current
        val focusManager = LocalFocusManager.current
        val openMenu = remember { mutableStateOf(false) }
        val reset = remember { mutableStateOf(false) }
        val viewModel: SmdResistorViewModel = viewModel(factory = ResistorViewModelFactory(context))
        val resistor by viewModel.resistor.collectAsState()
        val isError by viewModel.isError.collectAsState()

        SmdScreen(
            openMenu = openMenu,
            reset = reset,
            resistor = resistor,
            isError = isError,
            onOpenThemeDialog = onOpenThemeDialog,
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
            onNavBarSelectionChanged = { selection ->
                viewModel.saveNavBarSelection(selection)
            },
            navBarPosition = viewModel.getNavBarSelection(),
            onLearnSmdCodesTapped = { navigateToSmdCodeIec(navHostController) },
        )
    }
}
