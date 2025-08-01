package com.brandoncano.resistancecalculator.navigation.learn

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.brandoncano.resistancecalculator.navigation.ResistorScreen
import com.brandoncano.resistancecalculator.navigation.popBackStackSafely
import com.brandoncano.resistancecalculator.ui.screens.info.LearnSmdCodesScreen

fun NavGraphBuilder.learnSmdCodes(
    navHostController: NavHostController,
) {
    composable(
        route = ResistorScreen.LearnSmdCodes.route,
        enterTransition = { slideInHorizontally(initialOffsetX = { it }) },
        exitTransition = { ExitTransition.None },
        popEnterTransition = { EnterTransition.None },
        popExitTransition = { slideOutHorizontally(targetOffsetX = { it }) },
    ) {
        LearnSmdCodesScreen(
            onNavigateBack = { popBackStackSafely(navHostController) },
        )
    }
}