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
import com.brandoncano.library.util.ShareComposableAsImage
import com.brandoncano.library.util.ShareText
import com.brandoncano.resistancecalculator.BuildConfig
import com.brandoncano.resistancecalculator.model.ResistorCtvViewModel
import com.brandoncano.resistancecalculator.navigation.ResistorScreen
import com.brandoncano.resistancecalculator.navigation.navigateToAbout
import com.brandoncano.resistancecalculator.navigation.navigateToColorCodeIec
import com.brandoncano.resistancecalculator.navigation.popBackStackSafely
import com.brandoncano.resistancecalculator.ui.screens.calculators.ColorToValueScreen
import com.brandoncano.resistancecalculator.util.SendFeedbackWrapper

fun NavGraphBuilder.colorToValueScreen(
    navHostController: NavHostController,
) {
    composable(
        route = ResistorScreen.ColorToValue.route,
        enterTransition = { slideInHorizontally(initialOffsetX = { it }) },
        exitTransition = { ExitTransition.None },
        popEnterTransition = { EnterTransition.None },
        popExitTransition = { slideOutHorizontally(targetOffsetX = { it }) },
    ) {
        val activity = LocalActivity.current
        val context = LocalContext.current
        val focusManager = LocalFocusManager.current
        val viewModel: ResistorCtvViewModel = viewModel<ResistorCtvViewModel>()
        val resistor by viewModel.resistorStateTOStateFlow.collectAsState()

        ColorToValueScreen(
            resistor = resistor,
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
            onUpdateBand = { bandNumber, color ->
                viewModel.updateBand(bandNumber, color)
            },
            onNavBarSelectionChanged = { viewModel.updateNavBarSelection(it) },
            onLearnColorCodesTapped = { navigateToColorCodeIec(navHostController) },
        )
    }
}
