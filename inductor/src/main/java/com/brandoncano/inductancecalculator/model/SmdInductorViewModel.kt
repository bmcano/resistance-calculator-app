package com.brandoncano.inductancecalculator.model

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.brandoncano.inductancecalculator.adapter.SharedPreferencesAdapter
import com.brandoncano.inductancecalculator.to.SmdInductor
import com.brandoncano.inductancecalculator.util.formatInductance
import com.brandoncano.inductancecalculator.util.isSmdInputInvalid

class SmdInductorViewModel(private val savedStateHandle: SavedStateHandle): ViewModel() {

    private companion object {
        private const val TAG = "InductorSmdViewModel"
        private const val KEY_SCREEN_STATE_TO = "KEY_SCREEN_STATE_TO"
        private const val KEY_ERROR_STATE_BOOL = "KEY_ERROR_STATE_BOOL"
    }

    private val sharedPreferencesAdapter = SharedPreferencesAdapter()
    val inductorStateTOStateFlow = savedStateHandle.getStateFlow(KEY_SCREEN_STATE_TO, SmdInductor())
    val isErrorStateFlow = savedStateHandle.getStateFlow(KEY_ERROR_STATE_BOOL, false)

    init {
        Log.d(TAG, "Init: $this")
        setInitialScreenState()
    }

    private fun setInitialScreenState() {
        Log.d(TAG, "setInitialScreenState")
        savedStateHandle[KEY_SCREEN_STATE_TO] = deriveContentTO()
    }

    private fun deriveContentTO(): SmdInductor {
        val inductor = sharedPreferencesAdapter.getSmdInductorPreference()
        deriveErrorState(inductor)
        return inductor
    }

    private fun deriveErrorState(inductor: SmdInductor) {
        val isInvalid = inductor.isSmdInputInvalid()
        savedStateHandle[KEY_ERROR_STATE_BOOL] = isInvalid
    }

    fun clear() {
        val blankInductor = SmdInductor()

        sharedPreferencesAdapter.setSmdInductorPreference(blankInductor)
        savedStateHandle[KEY_SCREEN_STATE_TO] = blankInductor
        savedStateHandle[KEY_ERROR_STATE_BOOL] = false
    }

    fun updateValues(code: String, tolerance: String) {
        val currentInductor = inductorStateTOStateFlow.value
        val updatedInductor = currentInductor.copy(code = code, tolerance = tolerance)

        deriveErrorState(updatedInductor)
        if (!isErrorStateFlow.value) {
            updatedInductor.formatInductance()
        }

        sharedPreferencesAdapter.setSmdInductorPreference(updatedInductor)
        savedStateHandle[KEY_SCREEN_STATE_TO] = updatedInductor
    }
}
