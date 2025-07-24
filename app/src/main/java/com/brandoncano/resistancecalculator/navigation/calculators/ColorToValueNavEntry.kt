package com.brandoncano.resistancecalculator.navigation.calculators

import androidx.activity.compose.LocalActivity
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
import com.brandoncano.resistancecalculator.constants.Links
import com.brandoncano.resistancecalculator.model.ResistorViewModelFactory
import com.brandoncano.resistancecalculator.model.ctv.ResistorCtvViewModel
import com.brandoncano.resistancecalculator.navigation.Screen
import com.brandoncano.resistancecalculator.navigation.navigateToAbout
import com.brandoncano.resistancecalculator.navigation.navigateToColorCodeIec
import com.brandoncano.resistancecalculator.ui.screens.ctv.ColorToValueScreen
import com.brandoncano.resistancecalculator.util.share.ShareResistor
import com.brandoncano.sharedcomponents.utils.SendFeedback
import com.brandoncano.sharedcomponents.utils.ShareText

fun NavGraphBuilder.colorToValueScreen(
    navHostController: NavHostController,
) {
    composable(
        route = Screen.ColorToValue.route,
        enterTransition = { slideInVertically(initialOffsetY = { it }) },
        exitTransition = { ExitTransition.None },
        popEnterTransition = { EnterTransition.None },
        popExitTransition = { slideOutVertically(targetOffsetY= { it }) },
    ) {
        val activity = LocalActivity.current
        val context = LocalContext.current
        val focusManager = LocalFocusManager.current
        val viewModel: ResistorCtvViewModel = viewModel<ResistorCtvViewModel>()
        val resistor by viewModel.resistorStateTOStateFlow.collectAsState()

        ColorToValueScreen(
            resistor = resistor,
            onNavigateBack = { navHostController.popBackStack() },
            onClearSelectionsTapped = {
                viewModel.clear()
                focusManager.clearFocus()
            },
            onShareImageTapped = {
                if (activity != null) {
                    ShareResistor.execute(
                        activity = activity,
                        context = context,
                        applicationId = Links.APPLICATION_ID,
                        content = { it.invoke() },
                    )
                }
            },
            onShareTextTapped = { ShareText.execute(context, it) },
            onFeedbackTapped = { SendFeedback.execute(context, Links.APP_NAME) },
            onAboutTapped = { navigateToAbout(navHostController) },
            onUpdateBand = { bandNumber, color ->
                viewModel.updateBand(bandNumber, color)
            },
            onNavBarSelectionChanged = { viewModel.updateNavBarSelection(it) },
            onLearnColorCodesTapped = { navigateToColorCodeIec(navHostController) },
        )
    }
}
