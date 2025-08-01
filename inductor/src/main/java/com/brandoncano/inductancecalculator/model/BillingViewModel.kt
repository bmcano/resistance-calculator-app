package com.brandoncano.inductancecalculator.model

import android.app.Activity
import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.brandoncano.inductancecalculator.ui.InductorApplication
import com.brandoncano.library.DonationBillingAdapter
import com.brandoncano.library.util.GetProductIdForAmount
import com.brandoncano.inductancecalculator.R
import kotlinx.coroutines.launch

class BillingViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {

    private companion object {
        private const val TAG = "BillingViewModel"
        private const val KEY_ERROR_MESSAGES_STATE = "KEY_ERROR_MESSAGES_STATE"
    }

    private val application = InductorApplication.instance
    private val billingAdapter = DonationBillingAdapter(application)

    val errorMessagesStateFlow = savedStateHandle.getStateFlow(KEY_ERROR_MESSAGES_STATE, emptySet<String>())

    init {
        Log.d(TAG, "Init: $this")
        billingAdapter.startConnection {
            viewModelScope.launch {
                Log.e(TAG, "Connection error.")
                val errorMessage = application.getString(R.string.error_donate_screen)
                savedStateHandle[KEY_ERROR_MESSAGES_STATE] = setOf(errorMessage)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        Log.d(TAG, "onCleared: $this")
        billingAdapter.endConnection()
        savedStateHandle[KEY_ERROR_MESSAGES_STATE] = emptySet<String>()
    }

    fun donate(amount: Int, activity: Activity) {
        try {
            val productId = GetProductIdForAmount.execute(amount)
            billingAdapter.launchPurchaseFlow(activity, productId)
        } catch (ex: NullPointerException) {
            Log.e(TAG, Log.getStackTraceString(ex))
            val errorMessage = application.getString(R.string.error_donate_screen)
            savedStateHandle[KEY_ERROR_MESSAGES_STATE] = setOf(errorMessage)
        }
    }
}
