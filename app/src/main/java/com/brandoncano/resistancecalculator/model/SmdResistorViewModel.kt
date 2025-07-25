package com.brandoncano.resistancecalculator.model

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.brandoncano.resistancecalculator.adapter.SharedPreferencesAdapter
import com.brandoncano.resistancecalculator.to.SmdResistor
import com.brandoncano.resistancecalculator.util.resistor.formatResistance
import com.brandoncano.resistancecalculator.util.resistor.isSmdInputInvalid

class SmdResistorViewModel(private val savedStateHandle: SavedStateHandle): ViewModel() {

    private companion object {
        private const val TAG = "SmdResistorViewModel"
        private const val KEY_RESISTOR_STATE_TO = "KEY_RESISTOR_STATE_TO"
        private const val KEY_ERROR_STATE_BOOL = "KEY_ERROR_STATE_BOOL"
    }

    private val sharedPreferencesAdapter = SharedPreferencesAdapter()
    val resistorStateTOStateFlow = savedStateHandle.getStateFlow(KEY_RESISTOR_STATE_TO, SmdResistor())
    val isErrorStateFlow = savedStateHandle.getStateFlow(KEY_ERROR_STATE_BOOL, false)

    init {
        Log.d(TAG, "Init: $this")
        loadData()
    }

    fun loadData() {
        val resistor = sharedPreferencesAdapter.getSmdResistorPreference()
        val navBar = resistor.navBarSelection

        savedStateHandle[KEY_RESISTOR_STATE_TO] = resistor
        Log.d(TAG, "loadData(): resistor = $resistor, navBar = $navBar")
        updateErrorState(resistor)
    }

    fun clear() {
        val currentNavBar = resistorStateTOStateFlow.value.navBarSelection
        val blankResistor = SmdResistor(navBarSelection = currentNavBar)

        sharedPreferencesAdapter.setSmdResistorPreference(blankResistor)
        savedStateHandle[KEY_RESISTOR_STATE_TO] = blankResistor
        savedStateHandle[KEY_ERROR_STATE_BOOL] = false
    }

    fun updateValues(code: String, units: String) {
        val currentResistor = resistorStateTOStateFlow.value
        val updatedResistor = currentResistor.copy(
            code = code,
            units = units,
        )

        updateErrorState(updatedResistor)
        if (!isErrorStateFlow.value) {
            updatedResistor.formatResistance()
            sharedPreferencesAdapter.setSmdResistorPreference(updatedResistor)
        }

        savedStateHandle[KEY_RESISTOR_STATE_TO] = updatedResistor
    }

    fun updateNavBarSelection(number: Int) {
        val navBar = number.coerceIn(0..2)
        val currentResistor = resistorStateTOStateFlow.value
        val updatedResistor = currentResistor.copy(navBarSelection = navBar)

        updateErrorState(updatedResistor)
        if (!isErrorStateFlow.value) {
            updatedResistor.formatResistance()
            sharedPreferencesAdapter.setSmdResistorPreference(updatedResistor)
        }

        savedStateHandle[KEY_RESISTOR_STATE_TO] = updatedResistor
    }

    private fun updateErrorState(resistor: SmdResistor) {
        val isInvalid = resistor.isSmdInputInvalid()
        savedStateHandle[KEY_ERROR_STATE_BOOL] = isInvalid
    }
}