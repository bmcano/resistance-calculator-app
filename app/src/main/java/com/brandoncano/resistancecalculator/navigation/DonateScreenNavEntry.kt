package com.brandoncano.resistancecalculator.navigation

import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.brandoncano.resistancecalculator.ui.screens.donate.DonateScreen

fun NavGraphBuilder.donateScreen(
    navHostController: NavHostController,
) {
    composable(
        route = Screen.Donate.route,
        enterTransition = { slideInVertically(initialOffsetY = { it }) },
        exitTransition = { slideOutVertically(targetOffsetY = { it }) },
    ) {

        DonateScreen(
            onNavigateBack = { navHostController.popBackStack() },
        )
    }
}
