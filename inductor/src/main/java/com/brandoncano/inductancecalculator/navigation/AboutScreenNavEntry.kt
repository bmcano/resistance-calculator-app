package com.brandoncano.inductancecalculator.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable

import com.brandoncano.inductancecalculator.ui.screens.AboutScreen
import com.brandoncano.library.firebase.FirebaseAnalyticsEvent
import com.brandoncano.library.firebase.FirebaseAnalyticsScreenLogger
import com.brandoncano.library.firebase.FirebaseRemoteConfigKeys
import com.brandoncano.library.firebase.getStringOrEmpty
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
        val privacyPolicyLink = FirebaseRemoteConfigKeys.PRIVACY_POLICY_INDUCTOR.getStringOrEmpty()

        LaunchedEffect(Unit) {
            FirebaseAnalyticsScreenLogger.execute(
                context = context,
                event = FirebaseAnalyticsEvent.SCREEN_INDUCTOR_COLOR_TO_VALUE,
            )
        }

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
