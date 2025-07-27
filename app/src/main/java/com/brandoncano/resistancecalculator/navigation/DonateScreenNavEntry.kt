package com.brandoncano.resistancecalculator.navigation

import androidx.activity.compose.LocalActivity
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.brandoncano.resistancecalculator.model.BillingViewModel
import com.brandoncano.resistancecalculator.ui.screens.DonateScreen

fun NavGraphBuilder.donateScreen(
    navHostController: NavHostController,
) {
    composable(
        route = Screen.Donate.route,
        enterTransition = { slideInVertically(initialOffsetY = { it }) },
        exitTransition = { ExitTransition.None },
        popEnterTransition = { EnterTransition.None },
        popExitTransition = { slideOutVertically(targetOffsetY= { it }) },
    ) {
        val viewModel: BillingViewModel = viewModel<BillingViewModel>()
        val errorMessages by viewModel.errorMessages.collectAsState()
        val snackbarHostState = remember { SnackbarHostState() }

        LaunchedEffect(errorMessages) {
            errorMessages.forEach { message ->
                snackbarHostState.showSnackbar(message)
            }
        }

        val activity = LocalActivity.current ?: return@composable
        DonateScreen(
            onNavigateBack = { popBackStackSafely(navHostController) },
            onContinueToPaymentTapped = { amount -> viewModel.donate(amount, activity) },
            snackbarHostState = snackbarHostState,
        )
    }
}
