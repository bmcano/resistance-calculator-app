package com.brandoncano.resistancecalculator.navigation.learn

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.brandoncano.resistancecalculator.navigation.Screen
import com.brandoncano.resistancecalculator.ui.screens.info.LearnCircuitEquationsScreen

fun NavGraphBuilder.learnCircuitEquations(
    navHostController: NavHostController,
) {
    composable(
        route = Screen.LearnCircuitEquations.route,
        enterTransition = { slideInHorizontally(initialOffsetX = { it }) },
        exitTransition = { ExitTransition.None },
        popEnterTransition = { EnterTransition.None },
        popExitTransition = { slideOutHorizontally(targetOffsetX = { it }) },
    ) {
        LearnCircuitEquationsScreen(
            onNavigateBack = { navHostController.popBackStack() },
        )
    }
}
