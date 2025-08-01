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
import com.brandoncano.resistancecalculator.ui.screens.ViewOurAppsScreen
import com.brandoncano.library.util.OpenLink

fun NavGraphBuilder.viewOurAppsScreen(
    navHostController: NavHostController,
) {
    composable(
        route = ResistorScreen.ViewOurApps.route,
        enterTransition = { slideInHorizontally(initialOffsetX = { it }) },
        exitTransition = { ExitTransition.None },
        popEnterTransition = { EnterTransition.None },
        popExitTransition = { slideOutHorizontally(targetOffsetX = { it }) },
    ) {
        val context = LocalContext.current
        val developerProfileLink = FirebaseRemoteConfigKeys.PLAYSTORE_DEVELOPER_PROFILE.getStringOrEmpty()
        ViewOurAppsScreen(
            onNavigateBack = { popBackStackSafely(navHostController) },
            onFeatureCardTapped = { navigateToGooglePlay(context) },
            onMobileAppCardTapped = { OpenLink.execute(context, it) },
            onViewMoreAppsTapped = { OpenLink.execute(context, developerProfileLink) },
        )
    }
}
