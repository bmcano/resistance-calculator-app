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
import com.brandoncano.resistancecalculator.data.ESeriesCardContent
import com.brandoncano.resistancecalculator.model.ResistorViewModelFactory
import com.brandoncano.resistancecalculator.model.vtc.ResistorVtcViewModel
import com.brandoncano.resistancecalculator.navigation.Screen
import com.brandoncano.resistancecalculator.navigation.navigateToAbout
import com.brandoncano.resistancecalculator.navigation.navigateToPreferredValuesIec
import com.brandoncano.resistancecalculator.ui.screens.vtc.ValueToColorScreen
import com.brandoncano.resistancecalculator.util.eseries.formatResistanceString

fun NavGraphBuilder.valueToColorScreen(
    navHostController: NavHostController,
) {
    composable(
        route = Screen.ValueToColor.route,
        enterTransition = { slideInVertically(initialOffsetY = { it }) },
        exitTransition = { ExitTransition.None },
        popEnterTransition = { EnterTransition.None },
        popExitTransition = { slideOutVertically(targetOffsetY= { it }) },
    ) {
        val context = LocalContext.current
        val focusManager = LocalFocusManager.current
        val openMenu = remember { mutableStateOf(false) }
        val reset = remember { mutableStateOf(false) }
        val viewModel: ResistorVtcViewModel = viewModel(factory = ResistorViewModelFactory(context))
        val resistor by viewModel.resistorStateTOStateFlow.collectAsState()
        val isError by viewModel.isErrorStateFlow.collectAsState()
        val eSeriesCardContent: ESeriesCardContent by viewModel.eSeriesCardContentStateTOStateFlow.collectAsState()
        val closestStandardValue by viewModel.closestStandardValueStateFlow.collectAsState()

        ValueToColorScreen(
            resistor = resistor,
            isError = isError,
            openMenu = openMenu,
            reset = reset,
            eSeriesCardContent = eSeriesCardContent,
            onNavigateBack = { navHostController.popBackStack() },
            onClearSelectionsTapped = {
                openMenu.value = false
                reset.value = true
                viewModel.updateCardContent(ESeriesCardContent.DefaultContent)
                viewModel.clear()
                focusManager.clearFocus()
            },
            onAboutTapped = {
                openMenu.value = false
                navigateToAbout(navHostController)
            },
            onValueChanged = { resistance, units, band5, band6, clearFocus ->
                reset.value = false
                viewModel.updateCardContent(ESeriesCardContent.DefaultContent)
                viewModel.updateValues(resistance, units, band5, band6)
                if (clearFocus) focusManager.clearFocus()
            },
            onNavBarSelectionChanged = { viewModel.updateNavBarSelection(it) },
            onValidateResistanceTapped = { viewModel.validateResistance() },
            onUseValueTapped = {
                viewModel.updateCardContent(ESeriesCardContent.DefaultContent)
                val sigFigs = if (resistor.navBarSelection <= 1) 2 else 3
                val resistance = closestStandardValue.formatResistanceString(sigFigs)
                viewModel.updateValues(resistance, resistor.units, resistor.band5, resistor.band6)
                return@ValueToColorScreen resistance
            },
            onLearnMoreTapped = { navigateToPreferredValuesIec(navHostController) },
        )
    }
}
