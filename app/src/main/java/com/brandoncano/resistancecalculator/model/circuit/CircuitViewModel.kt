package com.brandoncano.resistancecalculator.model.circuit

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.brandoncano.resistancecalculator.to.Circuit
import com.brandoncano.resistancecalculator.util.circuit.TotalResistanceParallel
import com.brandoncano.resistancecalculator.util.circuit.TotalResistanceSeries

class CircuitViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {

    private companion object {
        private const val KEY_CIRCUIT_STATE_TO = "KEY_RESISTOR_STATE_TO"
    }

    val circuitStateTOStateFlow = savedStateHandle.getStateFlow(KEY_CIRCUIT_STATE_TO, Circuit())

    fun clear() {
        val sameValues = circuitStateTOStateFlow.value.isSameValues
        val resistorCount = circuitStateTOStateFlow.value.resistorCount
        val units = circuitStateTOStateFlow.value.units

        val blankCircuit = Circuit(isSameValues = sameValues, resistorCount = resistorCount, units = units)
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

        savedStateHandle[KEY_CIRCUIT_STATE_TO] = updatedCircuit
    }
}
