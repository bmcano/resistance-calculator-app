package com.brandoncano.inductancecalculator.navigation

import androidx.activity.compose.LocalActivity
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.brandoncano.inductancecalculator.model.BillingViewModel
import com.brandoncano.inductancecalculator.ui.screens.DonateScreen
import com.brandoncano.library.firebase.FirebaseAnalyticsEvent
import com.brandoncano.library.firebase.FirebaseAnalyticsScreenLogger

fun NavGraphBuilder.donateScreen(
    navHostController: NavHostController,
) {
    composable(
        route = InductorScreen.Donate.route,
        enterTransition = { slideInHorizontally(initialOffsetX = { it }) },
        exitTransition = { ExitTransition.None },
        popEnterTransition = { EnterTransition.None },
        popExitTransition = { slideOutHorizontally(targetOffsetX = { it }) },
    ) {
        val context = LocalContext.current
        val viewModel: BillingViewModel = viewModel<BillingViewModel>()
        val errorMessages by viewModel.errorMessagesStateFlow.collectAsState()
        val snackbarHostState = remember { SnackbarHostState() }

        LaunchedEffect(errorMessages) {
            errorMessages.forEach { message ->
                snackbarHostState.showSnackbar(message)
            }
        }

        LaunchedEffect(Unit) {
            FirebaseAnalyticsScreenLogger.execute(
                context = context,
                event = FirebaseAnalyticsEvent.SCREEN_RESISTOR_DONATE,
            )
        }

        val activity = LocalActivity.current ?: return@composable
        DonateScreen(
            onNavigateBack = { popBackStackSafely(navHostController) },
            onContinueToPaymentTapped = { amount -> viewModel.donate(amount, activity) },
            snackbarHostState = snackbarHostState,
        )
    }
}
