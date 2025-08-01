package com.brandoncano.inductancecalculator.navigation.calculators

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
import com.brandoncano.inductancecalculator.BuildConfig
import com.brandoncano.inductancecalculator.model.InductorCtvViewModel
import com.brandoncano.inductancecalculator.navigation.InductorScreen
import com.brandoncano.inductancecalculator.navigation.navigateToAbout
import com.brandoncano.inductancecalculator.navigation.navigateToLearnColorCodes
import com.brandoncano.inductancecalculator.navigation.popBackStackSafely
import com.brandoncano.inductancecalculator.ui.screens.calculators.ColorToValueScreen
import com.brandoncano.inductancecalculator.util.SendFeedbackWrapper
import com.brandoncano.library.util.ShareComposableAsImage
import com.brandoncano.library.util.ShareText

fun NavGraphBuilder.colorToValueScreen(
    navHostController: NavHostController,
) {
    composable(
        route = InductorScreen.ColorToValue.route,
        enterTransition = { slideInHorizontally(initialOffsetX = { it }) },
        exitTransition = { ExitTransition.None },
        popEnterTransition = { EnterTransition.None },
        popExitTransition = { slideOutHorizontally(targetOffsetX = { it }) },
    ) {
        val activity = LocalActivity.current
        val context = LocalContext.current
        val focusManager = LocalFocusManager.current
        val viewModel: InductorCtvViewModel = viewModel<InductorCtvViewModel>()
        val inductor by viewModel.inductorStateTOStateFlow.collectAsState()

        ColorToValueScreen(
            inductor = inductor,
            onNavigateBack = { popBackStackSafely(navHostController) },
            onClearSelectionsTapped = {
                viewModel.clear()
                focusManager.clearFocus()
            },
            onShareImageTapped = {
                if (activity != null) {
                    ShareComposableAsImage.execute(
                        activity = activity,
                        context = context,
                        applicationId = BuildConfig.APPLICATION_ID,
                        content = { it.invoke() },
                    )
                }
            },
            onShareTextTapped = { ShareText.execute(context, it) },
            onFeedbackTapped = { SendFeedbackWrapper.execute(context) },
            onAboutTapped = { navigateToAbout(navHostController) },
            onUpdateBand = { bandNumber, color -> viewModel.updateBand(bandNumber, color) },
            onLearnColorCodesTapped = { navigateToLearnColorCodes(navHostController) },
        )
    }
}
