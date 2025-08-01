package com.brandoncano.inductancecalculator.adapter

import android.app.Activity
import android.util.Log
import com.android.billingclient.api.AcknowledgePurchaseParams
import com.android.billingclient.api.BillingClient
import com.android.billingclient.api.BillingClientStateListener
import com.android.billingclient.api.BillingFlowParams
import com.android.billingclient.api.BillingResult
import com.android.billingclient.api.ConsumeParams
import com.android.billingclient.api.PendingPurchasesParams
import com.android.billingclient.api.Purchase
import com.android.billingclient.api.PurchasesUpdatedListener
import com.android.billingclient.api.QueryProductDetailsParams
import com.brandoncano.inductancecalculator.ui.InductorApplication

/**
 * Job: Handles the in-app purchases for the donation screen
 */
class BillingAdapter() {

    private companion object {
        const val TAG = "BillingAdapter"
    }

    val application = InductorApplication.instance
    private val purchaseListener = PurchasesUpdatedListener { billingResult, purchases ->
        when (billingResult.responseCode) {
            BillingClient.BillingResponseCode.OK -> {
                purchases?.forEach { handlePurchase(it) }
            }
            BillingClient.BillingResponseCode.USER_CANCELED -> {
                Log.i(TAG, "User canceled the purchase")
            }
            BillingClient.BillingResponseCode.ITEM_ALREADY_OWNED -> {
                // Note: This should never occur but will be here just in case
                Log.i(TAG, "ITEM_ALREADY_OWNED")
            }
            else -> {
                Log.e(TAG, "Error during purchase: ${billingResult.debugMessage}")
            }
        }
    }

    private val pendingPurchasesParams = PendingPurchasesParams.newBuilder()
        .enableOneTimeProducts() // Covers consumable one-time purchases (donations)
        .build()
    private val billingClient = BillingClient.newBuilder(application)
        .setListener(purchaseListener)
        .enablePendingPurchases(pendingPurchasesParams)
        .build()

    fun startConnection(onError: () -> Unit) {
        billingClient.startConnection(object : BillingClientStateListener {
            override fun onBillingSetupFinished(billingResult: BillingResult) {
                if (billingResult.responseCode == BillingClient.BillingResponseCode.OK) {
                    Log.i(TAG, "Billing client connected successfully")
                } else {
                    Log.e(TAG, "Error connecting billing client: ${billingResult.debugMessage}")
                    onError()
                }
            }

            override fun onBillingServiceDisconnected() {
                Log.e(TAG, "Billing service disconnected. Retrying connection...")
                billingClient.startConnection(this)
            }
        })
    }

    fun endConnection() {
        billingClient.endConnection()
    }

    fun launchPurchaseFlow(activity: Activity, productId: String) {
        val product = QueryProductDetailsParams.Product
            .newBuilder()
            .setProductId(productId)
            .setProductType(BillingClient.ProductType.INAPP)
            .build()
        billingClient.queryProductDetailsAsync(
            QueryProductDetailsParams.newBuilder()
                .setProductList(listOf(product))
                .build()
        ) { billingResult, queryProductDetailsResult ->
            val productDetailsList = queryProductDetailsResult.productDetailsList
            if (billingResult.responseCode == BillingClient.BillingResponseCode.OK && productDetailsList.isNotEmpty()) {
                val productDetails = productDetailsList[0]
                if (productDetails == null) {
                    Log.e(TAG, "Missing productDetails for $productId")
                    return@queryProductDetailsAsync
                }
                if (productDetails.oneTimePurchaseOfferDetails == null) {
                    Log.e(TAG, "Missing oneTimePurchaseOfferDetails for $productId")
                    return@queryProductDetailsAsync
                }

                val productDetailsParams = BillingFlowParams.ProductDetailsParams
                    .newBuilder()
                    .setProductDetails(productDetails)
                    .build()
                val billingFlowParams = BillingFlowParams.newBuilder()
                    .setProductDetailsParamsList(listOf(productDetailsParams))
                    .build()

                val launchResult = billingClient.launchBillingFlow(activity, billingFlowParams)
                if (launchResult.responseCode != BillingClient.BillingResponseCode.OK) {
                    Log.e(TAG, "Failed to launch billing flow: ${launchResult.responseCode} - ${launchResult.debugMessage}")
                }
            } else {
                Log.e(TAG, "Error launching purchase flow: ${billingResult.responseCode} - ${billingResult.debugMessage}")
                Log.e(TAG, "Error launching purchase flow: productDetailsList is empty: ${productDetailsList.isEmpty()}")
            }
        }
    }

    private fun handlePurchase(purchase: Purchase) {
        Log.i(TAG, "Processing purchase: ${purchase.orderId}")
        if (!purchase.isAcknowledged) {
            val acknowledgePurchaseParams = AcknowledgePurchaseParams.newBuilder()
                .setPurchaseToken(purchase.purchaseToken)
                .build()

            billingClient.acknowledgePurchase(acknowledgePurchaseParams) { billingResult ->
                if (billingResult.responseCode == BillingClient.BillingResponseCode.OK) {
                    Log.i(TAG, "Purchase acknowledged successfully")
                    consumePurchase(purchase)
                } else {
                    Log.e(TAG, "Failed to acknowledge purchase: ${billingResult.debugMessage}")
                }
            }
        }
    }

    private fun consumePurchase(purchase: Purchase) {
        val consumeParams = ConsumeParams.newBuilder()
            .setPurchaseToken(purchase.purchaseToken)
            .build()

        billingClient.consumeAsync(consumeParams) { billingResult, _ ->
            if (billingResult.responseCode == BillingClient.BillingResponseCode.OK) {
                Log.i(TAG, "Purchase consumed, donation available again.")
            } else {
                Log.e(TAG, "Failed to consume purchase: ${billingResult.debugMessage}")
            }
        }
    }
}
