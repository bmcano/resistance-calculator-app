package com.brandoncano.inductancecalculator.model

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.brandoncano.inductancecalculator.adapter.SharedPreferencesAdapter
import com.brandoncano.inductancecalculator.to.InductorCtv

class InductorCtvViewModel(private val savedStateHandle: SavedStateHandle): ViewModel() {

    private companion object {
        private const val TAG = "InductorCtvViewModel"
        private const val KEY_SCREEN_STATE_TO = "KEY_SCREEN_STATE_TO"
    }

    private val sharedPreferencesAdapter = SharedPreferencesAdapter()
    val inductorStateTOStateFlow = savedStateHandle.getStateFlow(KEY_SCREEN_STATE_TO, InductorCtv())

    init {
        Log.d(TAG, "Init: $this")
        setInitialScreenState()
    }

    private fun setInitialScreenState() {
        Log.d(TAG, "setInitialScreenState")
        savedStateHandle[KEY_SCREEN_STATE_TO] = deriveContentTO()
    }

    private fun deriveContentTO(): InductorCtv {
        val inductor = sharedPreferencesAdapter.getInductorCtvPreference()
        return inductor
    }

    fun clear() {
        val blankInductor = InductorCtv()
        sharedPreferencesAdapter.setInductorCtvPreference(blankInductor)
        savedStateHandle[KEY_SCREEN_STATE_TO] = blankInductor
    }

    fun updateBand(bandNumber: Int, color: String) {
        val currentInductor = inductorStateTOStateFlow.value
        val updatedInductor = when (bandNumber) {
            1 -> currentInductor.copy(band1 = color)
            2 -> currentInductor.copy(band2 = color)
            3 -> currentInductor.copy(band3 = color)
            4 -> currentInductor.copy(band4 = color)
            else -> currentInductor
        }

        sharedPreferencesAdapter.setInductorCtvPreference(updatedInductor)
        savedStateHandle[KEY_SCREEN_STATE_TO] = updatedInductor
    }
}
