package com.brandoncano.resistancecalculator.navigation.learn

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.brandoncano.library.firebase.FirebaseAnalyticsEvent
import com.brandoncano.library.firebase.FirebaseAnalyticsScreenLogger
import com.brandoncano.resistancecalculator.navigation.ResistorScreen
import com.brandoncano.resistancecalculator.navigation.popBackStackSafely
import com.brandoncano.resistancecalculator.ui.screens.info.LearnColorCodesScreen

fun NavGraphBuilder.learnColorCodes(
    navHostController: NavHostController,
) {
    composable(
        route = ResistorScreen.LearnColorCodes.route,
        enterTransition = { slideInHorizontally(initialOffsetX = { it }) },
        exitTransition = { ExitTransition.None },
        popEnterTransition = { EnterTransition.None },
        popExitTransition = { slideOutHorizontally(targetOffsetX = { it }) },
    ) {
        val context = LocalContext.current
        LaunchedEffect(Unit) {
            FirebaseAnalyticsScreenLogger.execute(
                context = context,
                event = FirebaseAnalyticsEvent.SCREEN_RESISTOR_INFO_COLOR_CODES,
            )
        }

        LearnColorCodesScreen(
            onNavigateBack = { popBackStackSafely(navHostController) },
        )
    }
}
