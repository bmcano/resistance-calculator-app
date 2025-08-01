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
import com.brandoncano.inductancecalculator.ui.screens.ViewOurAppsScreen
import com.brandoncano.library.firebase.FirebaseAnalyticsEvent
import com.brandoncano.library.firebase.FirebaseAnalyticsScreenLogger
import com.brandoncano.library.firebase.FirebaseRemoteConfigKeys
import com.brandoncano.library.firebase.getStringOrEmpty
import com.brandoncano.library.util.OpenLink

fun NavGraphBuilder.viewOurAppsScreen(
    navHostController: NavHostController,
) {
    composable(
        route = InductorScreen.ViewOurApps.route,
        enterTransition = { slideInHorizontally(initialOffsetX = { it }) },
        exitTransition = { ExitTransition.None },
        popEnterTransition = { EnterTransition.None },
        popExitTransition = { slideOutHorizontally(targetOffsetX = { it }) },
    ) {
        val context = LocalContext.current
        val developerProfileLink = FirebaseRemoteConfigKeys.PLAYSTORE_DEVELOPER_PROFILE.getStringOrEmpty()

        LaunchedEffect(Unit) {
            FirebaseAnalyticsScreenLogger.execute(
                context = context,
                event = FirebaseAnalyticsEvent.SCREEN_INDUCTOR_VIEW_APPS
            )
        }

        ViewOurAppsScreen(
            onNavigateBack = { popBackStackSafely(navHostController) },
            onFeatureCardTapped = { navigateToGooglePlay(context) },
            onMobileAppCardTapped = { OpenLink.execute(context, it) },
            onViewMoreAppsTapped = { OpenLink.execute(context, developerProfileLink) },
        )
    }
}