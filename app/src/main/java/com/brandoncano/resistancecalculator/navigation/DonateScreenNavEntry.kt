package com.brandoncano.resistancecalculator.navigation

import android.app.Activity
import androidx.activity.compose.LocalActivity
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.brandoncano.resistancecalculator.R
import com.brandoncano.resistancecalculator.adapter.BillingAdapter
import com.brandoncano.resistancecalculator.ui.screens.DonateScreen
import com.brandoncano.resistancecalculator.util.GetProductIdForAmount
import kotlinx.coroutines.launch

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
        val context = LocalContext.current
        val activity = LocalActivity.current ?: return@composable

        val scope = rememberCoroutineScope()
        val snackbarHostState = remember { SnackbarHostState() }
        val billingManager = remember { BillingAdapter() }

        billingManager.startConnection(
            onError = {
                scope.launch {
                    snackbarHostState.showSnackbar(
                        message = context.getString(R.string.error_donate_screen)
                    )
                }
            }
        )

        DonateScreen(
            onNavigateBack = { popBackStackSafely(navHostController) },
            onContinueToPaymentTapped = {
                val productId = GetProductIdForAmount.execute(it)
                billingManager.launchPurchaseFlow(activity, productId)
            },
            snackbarHostState = snackbarHostState,
        )
    }
}
