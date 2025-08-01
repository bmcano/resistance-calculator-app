package com.brandoncano.inductancecalculator.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.brandoncano.inductancecalculator.firebase.FirebaseRemoteConfigKeys
import com.brandoncano.inductancecalculator.firebase.getStringOrEmpty
import com.brandoncano.inductancecalculator.ui.screens.AboutScreen
import com.brandoncano.library.util.OpenLink

fun NavGraphBuilder.aboutScreen(
    navHostController: NavHostController,
) {
    composable(
        route = InductorScreen.About.route,
        enterTransition = { slideInHorizontally(initialOffsetX = { it }) },
        exitTransition = { ExitTransition.None },
        popEnterTransition = { EnterTransition.None },
        popExitTransition = { slideOutHorizontally(targetOffsetX = { it }) },
    ) {
        val context = LocalContext.current
        val privacyPolicyLink = FirebaseRemoteConfigKeys.PRIVACY_POLICY.getStringOrEmpty()
        AboutScreen(
            onNavigateBack = { navHostController.popBackStack() },
            onViewPrivacyPolicyTapped = { OpenLink.execute(context, privacyPolicyLink) },
            onViewColorCodeIecTapped = { navigateToLearnColorCodes(navHostController) },
            onViewSmdCodeIecTapped = { navigateToLearnSmdCodes(navHostController) },
            onRateThisAppTapped = { navigateToGooglePlay(context) },
            onViewOurAppsTapped = { navigateToOurApps(navHostController) },
            onDonateTapped = { navigateToDonate(navHostController) },
        )
    }
}
