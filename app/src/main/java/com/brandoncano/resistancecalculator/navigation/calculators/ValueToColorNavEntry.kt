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
import com.brandoncano.resistancecalculator.constants.Symbols
import com.brandoncano.resistancecalculator.data.ESeriesCardContent
import com.brandoncano.resistancecalculator.model.ResistorViewModelFactory
import com.brandoncano.resistancecalculator.model.vtc.ResistorVtcViewModel
import com.brandoncano.resistancecalculator.navigation.Screen
import com.brandoncano.resistancecalculator.navigation.navigateToAbout
import com.brandoncano.resistancecalculator.navigation.navigateToPreferredValuesIec
import com.brandoncano.resistancecalculator.ui.screens.vtc.ValueToColorScreen
import com.brandoncano.resistancecalculator.util.MultiplierFromUnits
import com.brandoncano.resistancecalculator.util.eseries.DeriveESeries
import com.brandoncano.resistancecalculator.util.eseries.DeriveESeriesString
import com.brandoncano.resistancecalculator.util.eseries.FindClosestStandardValue
import com.brandoncano.resistancecalculator.util.eseries.GenerateStandardValues
import com.brandoncano.resistancecalculator.util.eseries.ParseResistanceValue
import com.brandoncano.resistancecalculator.util.eseries.tolerancePercentage
import kotlin.math.abs

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
                viewModel.updateCardContent(ESeriesCardContent.NoContent)
                viewModel.clear()
                focusManager.clearFocus()
            },
            onAboutTapped = {
                openMenu.value = false
                navigateToAbout(navHostController)
            },
            onValueChanged = { resistance, units, band5, band6, clearFocus ->
                reset.value = false
                viewModel.updateCardContent(ESeriesCardContent.NoContent)
                viewModel.updateValues(resistance, units, band5, band6)
                if (clearFocus) focusManager.clearFocus()
            },
            onNavBarSelectionChanged = { viewModel.updateNavBarSelection(it) },
            onValidateResistanceTapped = {
                // TODO - Extract to viewmodel as we want to have more logic be in that section of the architecture
                if (resistor.isEmpty() || isError) return@ValueToColorScreen
                val units = resistor.units
                val resistanceValue = ParseResistanceValue.execute(resistor.resistance, units) ?: return@ValueToColorScreen
                val tolerance = if (resistor.navBarSelection == 0) "${Symbols.PM}20%" else resistor.band5

                focusManager.clearFocus()

                validateTolerance(tolerance, resistor.navBarSelection)?.let {
                    viewModel.updateCardContent(it)
                    return@ValueToColorScreen
                }

                val tolerancePercentage = tolerance.tolerancePercentage() ?: return@ValueToColorScreen
                val eSeriesList = DeriveESeries.execute(tolerancePercentage, resistor.navBarSelection + 3)
                if (eSeriesList.isNullOrEmpty()) {
                    viewModel.updateCardContent(ESeriesCardContent.InvalidTolerance("${resistor.navBarSelection + 3}"))
                    return@ValueToColorScreen
                }

                val (cardContent, closestValue) = calculateClosestStandardValue(resistanceValue, units, eSeriesList)
                viewModel.updateCardContent(cardContent)
                viewModel.updateClosestStandardValue(closestValue)
            },
            onUseValueTapped = {
                viewModel.updateCardContent(ESeriesCardContent.NoContent)
                // TODO - Defect: When say we have 123 and the suggestion is 124, it uses 124.0 which is an error.
                val resistance = closestStandardValue.toString()
                viewModel.updateValues(resistance, resistor.units, resistor.band5, resistor.band6)
                return@ValueToColorScreen resistance
            },
            onLearnMoreTapped = { navigateToPreferredValuesIec(navHostController) },
        )
    }
}

// TODO - create utils for these functions
fun validateTolerance(tolerance: String, navBarSelection: Int): ESeriesCardContent? {
    return if (tolerance.isEmpty()) {
        ESeriesCardContent.InvalidTolerance("${navBarSelection + 3}")
    } else {
        null
    }
}

fun calculateClosestStandardValue(resistanceValue: Double, units: String, eSeriesList: List<Int>): Pair<ESeriesCardContent, Double> {
    val standardValues = GenerateStandardValues.execute(eSeriesList)
    val closestValueOhms = FindClosestStandardValue.execute(resistanceValue, standardValues)
    val closestValue = if (resistanceValue > 0.0) {
        closestValueOhms / MultiplierFromUnits.execute(units)
    } else {
        0.1
    }

    val difference = abs(resistanceValue - closestValueOhms)
    val eSeriesName = DeriveESeriesString.execute(eSeriesList)
    val content = if (difference == 0.0) {
        ESeriesCardContent.ValidResistance(eSeriesName)
    } else {
        ESeriesCardContent.InvalidResistance("$closestValue $units")
    }

    return Pair(content, closestValue)
}
