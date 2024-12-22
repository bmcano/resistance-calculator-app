package com.brandoncano.resistancecalculator.model

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.brandoncano.resistancecalculator.model.ctv.ResistorCtvViewModel
import com.brandoncano.resistancecalculator.model.smd.SmdResistorViewModel
import com.brandoncano.resistancecalculator.model.vtc.ResistorVtcViewModel

/**
 * Note: We need this since we have context as an input
 */
class ResistorViewModelFactory(private val context: Context): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return when (modelClass.canonicalName) {
            ResistorCtvViewModel::class.java.canonicalName -> ResistorCtvViewModel(context) as T
            ResistorVtcViewModel::class.java.canonicalName -> ResistorVtcViewModel(context) as T
            SmdResistorViewModel::class.java.canonicalName -> SmdResistorViewModel(context) as T
            else -> throw IllegalArgumentException("Unknown ViewModel class: $modelClass")
        }
    }
}
