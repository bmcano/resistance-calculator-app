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
import com.brandoncano.resistancecalculator.model.SmdResistorViewModel
import com.brandoncano.resistancecalculator.navigation.Screen
import com.brandoncano.resistancecalculator.navigation.navigateToAbout
import com.brandoncano.resistancecalculator.navigation.navigateToSmdCodeIec
import com.brandoncano.resistancecalculator.navigation.popBackStackSafely
import com.brandoncano.resistancecalculator.ui.screens.calculators.SmdScreen
import com.brandoncano.resistancecalculator.util.SendFeedback
import com.brandoncano.resistancecalculator.util.share.ShareResistor
import com.brandoncano.resistancecalculator.util.share.ShareText

fun NavGraphBuilder.smdScreen(
    navHostController: NavHostController,
) {
    composable(
        route = Screen.Smd.route,
        enterTransition = { slideInHorizontally(initialOffsetX = { it }) },
        exitTransition = { ExitTransition.None },
        popEnterTransition = { EnterTransition.None },
        popExitTransition = { slideOutHorizontally(targetOffsetX = { it }) },
    ) {
        val activity = LocalActivity.current
        val context = LocalContext.current
        val focusManager = LocalFocusManager.current
        val viewModel: SmdResistorViewModel = viewModel<SmdResistorViewModel>()
        val resistor by viewModel.resistorStateTOStateFlow.collectAsState()
        val isError by viewModel.isErrorStateFlow.collectAsState()

        SmdScreen(
            resistor = resistor,
            isError = isError,
            onNavigateBack = { popBackStackSafely(navHostController) },
            onClearSelectionsTapped = {
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
            onValueChanged = { viewModel.updateValues(it, resistor.units) },
            onOptionSelected = {
                viewModel.updateValues(resistor.code, it)
                focusManager.clearFocus()
            },
            onNavBarSelectionChanged = { viewModel.updateNavBarSelection(it) },
            onLearnSmdCodesTapped = { navigateToSmdCodeIec(navHostController) },
        )
    }
}
