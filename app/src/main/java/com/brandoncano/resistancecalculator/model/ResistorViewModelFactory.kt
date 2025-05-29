package com.brandoncano.resistancecalculator.model

import android.content.Context
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.brandoncano.resistancecalculator.model.ctv.ResistorCtvViewModel
import com.brandoncano.resistancecalculator.model.led.LedCircuitViewModel
import com.brandoncano.resistancecalculator.model.smd.SmdResistorViewModel
import com.brandoncano.resistancecalculator.model.vtc.ResistorVtcViewModel

/**
 * Note: We need this since we have context as an input
 *
 * TODO - address deprecation, maybe name change?
 */
class ResistorViewModelFactory(private val context: Context): AbstractSavedStateViewModelFactory() {

    override fun <T : ViewModel> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        @Suppress("UNCHECKED_CAST")
        return when (modelClass.canonicalName) {
            ResistorCtvViewModel::class.java.canonicalName -> ResistorCtvViewModel(handle, context) as T
            ResistorVtcViewModel::class.java.canonicalName -> ResistorVtcViewModel(handle, context) as T
            SmdResistorViewModel::class.java.canonicalName -> SmdResistorViewModel(handle, context) as T
            LedCircuitViewModel::class.java.canonicalName -> LedCircuitViewModel(handle, context) as T
            else -> throw IllegalArgumentException("Unknown ViewModel class: $modelClass")
        }
    }
}
