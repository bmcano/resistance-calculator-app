package com.brandoncano.resistancecalculator.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.brandoncano.resistancecalculator.keys.FirebaseRemoteConfigKeys
import com.brandoncano.resistancecalculator.firebase.getStringOrEmpty
import com.brandoncano.resistancecalculator.ui.screens.AboutScreen
import com.brandoncano.library.util.OpenLink

fun NavGraphBuilder.aboutScreen(
    navHostController: NavHostController,
) {
    composable(
        route = ResistorScreen.About.route,
        enterTransition = { slideInHorizontally(initialOffsetX = { it }) },
        exitTransition = { ExitTransition.None },
        popEnterTransition = { EnterTransition.None },
        popExitTransition = { slideOutHorizontally(targetOffsetX = { it }) },
    ) {
        val context = LocalContext.current
        val privacyPolicyLink = FirebaseRemoteConfigKeys.PRIVACY_POLICY.getStringOrEmpty()
        AboutScreen(
            onNavigateBack = { popBackStackSafely(navHostController) },
            onViewPrivacyPolicyTapped = { OpenLink.execute(context, privacyPolicyLink) },
            onViewColorCodeIecTapped = { navigateToColorCodeIec(navHostController) },
            onViewPreferredValuesIecTapped = { navigateToPreferredValuesIec(navHostController) },
            onViewSmdCodeIecTapped = { navigateToSmdCodeIec(navHostController) },
            onViewCircuitEquationsTapped = { navigateToCircuitEquations(navHostController) },
            onRateThisAppTapped = { navigateToGooglePlay(context) },
            onViewOurAppsTapped = { navigateToOurApps(navHostController) },
            onDonateTapped = { navigateToDonate(navHostController) },
        )
    }
}
