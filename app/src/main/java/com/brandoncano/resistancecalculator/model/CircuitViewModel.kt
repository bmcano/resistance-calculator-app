package com.brandoncano.resistancecalculator.model

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.brandoncano.resistancecalculator.adapter.SharedPreferencesAdapter
import com.brandoncano.resistancecalculator.to.Circuit
import com.brandoncano.resistancecalculator.util.circuit.TotalResistanceParallel
import com.brandoncano.resistancecalculator.util.circuit.TotalResistanceSeries

class CircuitViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {

    private companion object {
        private const val TAG = "CircuitViewModel"
        private const val KEY_CIRCUIT_STATE_TO = "KEY_RESISTOR_STATE_TO"
    }

    private val sharedPreferencesAdapter = SharedPreferencesAdapter()
    val circuitStateTOStateFlow = savedStateHandle.getStateFlow(KEY_CIRCUIT_STATE_TO, Circuit())

    init {
        Log.d(TAG, "Init: $this")
        loadData()
    }

    fun loadData() {
        val circuit = sharedPreferencesAdapter.getCircuitPreference()

        savedStateHandle[KEY_CIRCUIT_STATE_TO] = circuit
        Log.d(TAG, "loadData(): circuit = $circuit")
    }

    fun clear() {
        val sameValues = circuitStateTOStateFlow.value.isSameValues
        val resistorCount = circuitStateTOStateFlow.value.resistorCount
        val units = circuitStateTOStateFlow.value.units
        val blankCircuit = Circuit(isSameValues = sameValues, resistorCount = resistorCount, units = units)

        sharedPreferencesAdapter.setCircuitPreference(blankCircuit)
        savedStateHandle[KEY_CIRCUIT_STATE_TO] = blankCircuit
    }

    fun updateValues(isSameValues: Boolean, resistorCount: Int, units: String, isSeriesCalculation: Boolean) {
        val currentCircuit = circuitStateTOStateFlow.value
        val resistorInputs = currentCircuit.resistorInputs.take(resistorCount)
        val totalResistance = if (isSeriesCalculation) {
            TotalResistanceSeries.execute(isSameValues, resistorCount, resistorInputs)
        } else {
            TotalResistanceParallel.execute(isSameValues, resistorCount, resistorInputs)
        }
        val updatedCircuit = currentCircuit.copy(isSameValues = isSameValues, resistorCount = resistorCount, units = units, totalResistance = totalResistance)

        sharedPreferencesAdapter.setCircuitPreference(updatedCircuit)
        savedStateHandle[KEY_CIRCUIT_STATE_TO] = updatedCircuit
    }

    fun updateResistorInput(resistance: String, index: Int, isSeriesCalculation: Boolean) {
        val currentCircuit = circuitStateTOStateFlow.value
        val updatedInputs = currentCircuit.resistorInputs.toMutableList().also {
            it[index] = resistance
        }
        val totalResistance = if (isSeriesCalculation) {
            TotalResistanceSeries.execute(currentCircuit.isSameValues, currentCircuit.resistorCount, updatedInputs)
        } else {
            TotalResistanceParallel.execute(currentCircuit.isSameValues, currentCircuit.resistorCount, updatedInputs)
        }
        val updatedCircuit = currentCircuit.copy(resistorInputs = updatedInputs, totalResistance = totalResistance)

        sharedPreferencesAdapter.setCircuitPreference(updatedCircuit)
        savedStateHandle[KEY_CIRCUIT_STATE_TO] = updatedCircuit
    }
}
