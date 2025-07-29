package com.brandoncano.resistancecalculator.model

import android.app.Activity
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.brandoncano.resistancecalculator.R
import com.brandoncano.resistancecalculator.adapter.BillingAdapter
import com.brandoncano.resistancecalculator.ui.MainApplication
import com.brandoncano.resistancecalculator.util.GetProductIdForAmount
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class BillingViewModel : ViewModel() {

    private companion object {
        private const val TAG = "BillingViewModel"
    }

    private val application = MainApplication.instance
    private val billingAdapter = BillingAdapter()

    private val _errorMessages = MutableStateFlow<MutableList<String>>(mutableListOf())
    val errorMessages: StateFlow<List<String>> = _errorMessages

    init {
        Log.d(TAG, "Init: $this")
        billingAdapter.startConnection {
            viewModelScope.launch {
                Log.e(TAG, "Connection error.")
                val errorMessage = application.getString(R.string.error_donate_screen)
                _errorMessages.value.add(errorMessage)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        Log.d(TAG, "onCleared: $this")
        billingAdapter.endConnection()
    }

    fun donate(amount: Int, activity: Activity) {
        try {
            val productId = GetProductIdForAmount.execute(amount)
            billingAdapter.launchPurchaseFlow(activity, productId)
        } catch (ex: NullPointerException) {
            Log.e(TAG, Log.getStackTraceString(ex))
            val errorMessage = application.getString(R.string.error_donate_screen)
            _errorMessages.value.add(errorMessage)
        }
    }
}
