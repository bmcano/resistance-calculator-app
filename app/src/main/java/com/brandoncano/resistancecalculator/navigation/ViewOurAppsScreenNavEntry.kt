package com.brandoncano.resistancecalculator.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.brandoncano.resistancecalculator.constants.Links
import com.brandoncano.resistancecalculator.ui.screens.ViewOurAppsScreen
import com.brandoncano.resistancecalculator.util.OpenLink

fun NavGraphBuilder.viewOurAppsScreen(
    navHostController: NavHostController,
) {
    composable(
        route = Screen.ViewOurApps.route,
        enterTransition = { slideInHorizontally(initialOffsetX = { it }) },
        exitTransition = { ExitTransition.None },
        popEnterTransition = { EnterTransition.None },
        popExitTransition = { slideOutHorizontally(targetOffsetX = { it }) },
    ) {
        val context = LocalContext.current
        ViewOurAppsScreen(
            onNavigateBack = { popBackStackSafely(navHostController) },
            onFeatureCardTapped = { OpenLink.execute(context, Links.RESISTOR_PLAYSTORE) },
            onMobileAppCardTapped = { OpenLink.execute(context, it) },
            onViewMoreAppsTapped = { OpenLink.execute(context, Links.DEVELOPER_PROFILE) },
        )
    }
}
