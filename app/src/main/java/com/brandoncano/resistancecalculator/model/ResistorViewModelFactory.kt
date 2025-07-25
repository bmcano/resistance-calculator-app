package com.brandoncano.resistancecalculator.model

import android.content.Context
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.brandoncano.resistancecalculator.model.smd.SmdResistorViewModel

/**
 * Note: We need this since we have context as an input
 */
class ResistorViewModelFactory(private val context: Context): AbstractSavedStateViewModelFactory() {

    override fun <T : ViewModel> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        @Suppress("UNCHECKED_CAST")
        return when (modelClass.canonicalName) {
            SmdResistorViewModel::class.java.canonicalName -> SmdResistorViewModel(handle, context) as T
            else -> throw IllegalArgumentException("Unknown ViewModel class: $modelClass")
        }
    }
}
