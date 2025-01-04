package com.brandoncano.resistancecalculator.navigation

import android.content.Context
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.brandoncano.resistancecalculator.constants.Links
import com.brandoncano.resistancecalculator.ui.screens.about.AboutScreen
import com.brandoncano.sharedcomponents.utils.OpenLink

fun NavGraphBuilder.aboutScreen(
    navHostController: NavHostController,
) {
    composable(
        route = Screen.About.route,
        enterTransition = { slideInVertically(initialOffsetY = { it }) },
        exitTransition = { slideOutVertically(targetOffsetY = { it }) },
    ) {
        val context = LocalContext.current
        AboutScreen(
            onNavigateBack = { navHostController.popBackStack() },
            onViewPrivacyPolicyTapped = { navigateToPrivacyPolicy(context) },
            onViewColorCodeIecTapped = { navigateToColorCodeIec(navHostController) },
            onViewPreferredValuesIecTapped = { navigateToPreferredValuesIec(navHostController) },
            onViewSmdCodeIecTapped = { navigateToSmdCodeIec(navHostController) },
            onRateThisAppTapped = { navigateToGooglePlay(context) },
            onViewOurAppsTapped = { navigateToOurApps(navHostController) },
            onDonateTapped = { navigateToDonate(navHostController) },
        )
    }
}

private fun navigateToPrivacyPolicy(context: Context) {
    OpenLink.execute(context, Links.PRIVACY_POLICY)
}
