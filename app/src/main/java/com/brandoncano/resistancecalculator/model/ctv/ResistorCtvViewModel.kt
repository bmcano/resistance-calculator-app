package com.brandoncano.resistancecalculator.model.ctv

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.brandoncano.resistancecalculator.adapter.SharedPreferencesAdapter
import com.brandoncano.resistancecalculator.to.ResistorCtv

class ResistorCtvViewModel(private val savedStateHandle: SavedStateHandle): ViewModel() {

    private companion object {
        private const val TAG = "ResistorCtvViewModel"
        private const val KEY_RESISTOR_STATE_TO = "KEY_RESISTOR_STATE_TO"
    }

    private val sharedPreferencesAdapter = SharedPreferencesAdapter()
    val resistorStateTOStateFlow = savedStateHandle.getStateFlow(KEY_RESISTOR_STATE_TO, ResistorCtv())

    init {
        Log.d(TAG, "Init: $this")
        loadData()
    }

    fun loadData() {
        val resistor = sharedPreferencesAdapter.getResistorCtvPreference()
        savedStateHandle[KEY_RESISTOR_STATE_TO] = resistor
    }

    fun clear() {
        val currentNavBar = resistorStateTOStateFlow.value.navBarSelection
        val blankResistor = ResistorCtv(navBarSelection = currentNavBar)

        sharedPreferencesAdapter.setResistorCtvPreference(blankResistor)
        savedStateHandle[KEY_RESISTOR_STATE_TO] = blankResistor
    }

    fun updateBand(bandNumber: Int, color: String) {
        val currentResistor = resistorStateTOStateFlow.value
        val updatedResistor = when (bandNumber) {
            1 -> currentResistor.copy(band1 = color)
            2 -> currentResistor.copy(band2 = color)
            3 -> currentResistor.copy(band3 = color)
            4 -> currentResistor.copy(band4 = color)
            5 -> currentResistor.copy(band5 = color)
            6 -> currentResistor.copy(band6 = color)
            else -> currentResistor
        }

        sharedPreferencesAdapter.setResistorCtvPreference(updatedResistor)
        savedStateHandle[KEY_RESISTOR_STATE_TO] = updatedResistor
    }

    fun updateNavBarSelection(number: Int) {
        val navBar = number.coerceIn(0..3)
        val currentResistor = resistorStateTOStateFlow.value
        val updatedResistor = currentResistor.copy(navBarSelection = navBar)

        sharedPreferencesAdapter.setResistorCtvPreference(updatedResistor)
        savedStateHandle[KEY_RESISTOR_STATE_TO] = updatedResistor
    }
}
