package com.brandoncano.resistancecalculator.navigation.calculators

import androidx.activity.compose.LocalActivity
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.brandoncano.resistancecalculator.BuildConfig
import com.brandoncano.resistancecalculator.data.ESeriesCardContent
import com.brandoncano.resistancecalculator.firebase.FirebaseAnalyticsEvent
import com.brandoncano.resistancecalculator.firebase.FirebaseAnalyticsEventLogger
import com.brandoncano.resistancecalculator.model.ResistorVtcViewModel
import com.brandoncano.resistancecalculator.navigation.Screen
import com.brandoncano.resistancecalculator.navigation.navigateToAbout
import com.brandoncano.resistancecalculator.navigation.navigateToPreferredValuesIec
import com.brandoncano.resistancecalculator.navigation.popBackStackSafely
import com.brandoncano.resistancecalculator.ui.screens.calculators.ValueToColorScreen
import com.brandoncano.resistancecalculator.util.SendFeedback
import com.brandoncano.resistancecalculator.util.eseries.formatResistanceString
import com.brandoncano.resistancecalculator.util.share.ShareResistor
import com.brandoncano.resistancecalculator.util.share.ShareText

fun NavGraphBuilder.valueToColorScreen(
    navHostController: NavHostController,
) {
    composable(
        route = Screen.ValueToColor.route,
        enterTransition = { slideInHorizontally(initialOffsetX = { it }) },
        exitTransition = { ExitTransition.None },
        popEnterTransition = { EnterTransition.None },
        popExitTransition = { slideOutHorizontally(targetOffsetX = { it }) },
    ) {
        val activity = LocalActivity.current
        val context = LocalContext.current
        val focusManager = LocalFocusManager.current
        val viewModel: ResistorVtcViewModel = viewModel<ResistorVtcViewModel>()
        val resistor by viewModel.resistorStateTOStateFlow.collectAsState()
        val isError by viewModel.isErrorStateFlow.collectAsState()
        val eSeriesCardContent: ESeriesCardContent by viewModel.eSeriesCardContentStateTOStateFlow.collectAsState()
        val closestStandardValue by viewModel.closestStandardValueStateFlow.collectAsState()

        ValueToColorScreen(
            resistor = resistor,
            isError = isError,
            eSeriesCardContent = eSeriesCardContent,
            onNavigateBack = { popBackStackSafely(navHostController) },
            onClearSelectionsTapped = {
                viewModel.updateCardContentState(ESeriesCardContent.DefaultContent)
                viewModel.clear()
                focusManager.clearFocus()
            },
            onShareImageTapped = {
                if (activity != null) {
                    ShareResistor.execute(
                        activity = activity,
                        context = context,
                        applicationId = BuildConfig.APPLICATION_ID,
                        content = { it.invoke() },
                    )
                }
            },
            onShareTextTapped = { ShareText.execute(context, it) },
            onFeedbackTapped = { SendFeedback.execute(context) },
            onAboutTapped = { navigateToAbout(navHostController) },
            onValueChanged = { resistance ->
                viewModel.updateCardContentState(ESeriesCardContent.DefaultContent)
                viewModel.updateValues(resistance, resistor.units, resistor.band5, resistor.band6)
            },
            onOptionSelected = { units, band5, band6 ->
                viewModel.updateCardContentState(ESeriesCardContent.DefaultContent)
                viewModel.updateValues(resistor.resistance, units, band5, band6)
                focusManager.clearFocus()
            },
            onNavBarSelectionChanged = { viewModel.updateNavBarSelection(it) },
            onValidateResistanceTapped = {
                FirebaseAnalyticsEventLogger.execute(FirebaseAnalyticsEvent.ACTION_VALIDATE_E_SERIES)
                viewModel.validateResistance()
            },
            onUseValueTapped = {
                viewModel.updateCardContentState(ESeriesCardContent.DefaultContent)
                val sigFigs = if (resistor.navBarSelection <= 1) 2 else 3
                val resistance = closestStandardValue.formatResistanceString(sigFigs)
                viewModel.updateValues(resistance, resistor.units, resistor.band5, resistor.band6)
                return@ValueToColorScreen resistance
            },
            onLearnMoreTapped = { navigateToPreferredValuesIec(navHostController) },
        )
    }
}
