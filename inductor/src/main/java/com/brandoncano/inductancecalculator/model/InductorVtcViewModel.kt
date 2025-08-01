package com.brandoncano.inductancecalculator.model

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.brandoncano.inductancecalculator.adapter.SharedPreferencesAdapter
import com.brandoncano.inductancecalculator.to.InductorVtc
import com.brandoncano.inductancecalculator.util.formatInductor
import com.brandoncano.inductancecalculator.util.isInvalidInput

class InductorVtcViewModel(private val savedStateHandle: SavedStateHandle): ViewModel() {

    private companion object {
        private const val TAG = "InductorVtcViewModel"
        private const val KEY_SCREEN_STATE_TO = "KEY_SCREEN_STATE_TO"
        private const val KEY_ERROR_STATE_BOOL = "KEY_ERROR_STATE_BOOL"
    }

    private val sharedPreferencesAdapter = SharedPreferencesAdapter()
    val inductorStateTOStateFlow = savedStateHandle.getStateFlow(KEY_SCREEN_STATE_TO, InductorVtc())
    val isErrorStateFlow = savedStateHandle.getStateFlow(KEY_ERROR_STATE_BOOL, false)

    init {
        Log.d(TAG, "Init: $this")
        setInitialScreenState()
    }

    private fun setInitialScreenState() {
        Log.d(TAG, "setInitialScreenState")
        savedStateHandle[KEY_SCREEN_STATE_TO] = deriveContentTO()
    }

    private fun deriveContentTO(): InductorVtc {
        val inductor = sharedPreferencesAdapter.getInductorVtcPreference()
        deriveErrorState(inductor)
        return inductor
    }

    private fun deriveErrorState(inductor: InductorVtc) {
        val isInvalid = inductor.isInvalidInput()
        savedStateHandle[KEY_ERROR_STATE_BOOL] = isInvalid
    }

    fun clear() {
        val blankInductor = InductorVtc()
        sharedPreferencesAdapter.setInductorVtcPreference(blankInductor)
        savedStateHandle[KEY_SCREEN_STATE_TO] = blankInductor
    }

    fun updateValues(inductance: String, units: String, tolerance: String) {
        val currentInductor = inductorStateTOStateFlow.value
        val updatedInductor = currentInductor.copy(
            inductance = inductance,
            units = units,
            tolerance = tolerance
        )

        deriveErrorState(updatedInductor)
        if (!isErrorStateFlow.value) {
            updatedInductor.formatInductor()
        }

        sharedPreferencesAdapter.setInductorVtcPreference(updatedInductor)
        savedStateHandle[KEY_SCREEN_STATE_TO] = updatedInductor
    }
}
