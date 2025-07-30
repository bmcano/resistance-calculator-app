package com.brandoncano.resistancecalculator.model

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import com.brandoncano.resistancecalculator.adapter.SharedPreferencesAdapter
import com.brandoncano.resistancecalculator.to.Circuit
import com.brandoncano.resistancecalculator.util.circuit.TotalResistanceParallel
import com.brandoncano.resistancecalculator.util.circuit.TotalResistanceSeries

class CircuitViewModel(private val savedStateHandle: SavedStateHandle, private val isSeriesCircuit: Boolean) : ViewModel() {

    companion object {
        private const val TAG = "CircuitViewModel"
        private const val KEY_SCREEN_STATE_TO = "KEY_SCREEN_STATE_TO"

        fun getFactory(isSeriesCircuit: Boolean): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
                return CircuitViewModel(
                    savedStateHandle = extras.createSavedStateHandle(),
                    isSeriesCircuit = isSeriesCircuit,
                ) as T
            }
        }
    }

    private val sharedPreferencesAdapter = SharedPreferencesAdapter()
    val circuitStateTOStateFlow = savedStateHandle.getStateFlow(KEY_SCREEN_STATE_TO, Circuit())

    init {
        Log.d(TAG, "Init: $this")
        setInitialScreenState()
    }

    private fun setInitialScreenState() {
        Log.d(TAG, "setInitialScreenState")
        savedStateHandle[KEY_SCREEN_STATE_TO] = deriveContentTO()
    }

    private fun deriveContentTO(): Circuit {
        val circuit = sharedPreferencesAdapter.getCircuitPreference()
        val totalResistance = calculateTotalResistance(circuit)
        val circuitContent = circuit.copy(totalResistance = totalResistance)
        sharedPreferencesAdapter.setCircuitPreference(circuitContent)
        return circuitContent
    }

    fun clear() {
        val sameValues = circuitStateTOStateFlow.value.isSameValues
        val resistorCount = circuitStateTOStateFlow.value.resistorCount
        val units = circuitStateTOStateFlow.value.units
        val blankCircuit = Circuit(isSameValues = sameValues, resistorCount = resistorCount, units = units)
        sharedPreferencesAdapter.setCircuitPreference(blankCircuit)
        savedStateHandle[KEY_SCREEN_STATE_TO] = blankCircuit
    }

    fun updateValues(isSameValues: Boolean, resistorCount: Int, units: String) {
        val currentCircuit = circuitStateTOStateFlow.value.copy(isSameValues = isSameValues, resistorCount = resistorCount, units = units)
        val totalResistance = calculateTotalResistance(currentCircuit)
        val updatedCircuit = currentCircuit.copy(totalResistance = totalResistance)
        sharedPreferencesAdapter.setCircuitPreference(updatedCircuit.copy(totalResistance = totalResistance))
        savedStateHandle[KEY_SCREEN_STATE_TO] = updatedCircuit.copy(totalResistance = totalResistance)
    }

    fun updateResistorInput(resistance: String, index: Int) {
        val currentCircuit = circuitStateTOStateFlow.value
        val updatedInputs = currentCircuit.resistorInputs.toMutableList().also {
            it[index] = resistance
        }
        val totalResistance = calculateTotalResistance(currentCircuit.copy(resistorInputs = updatedInputs))
        val updatedCircuit = currentCircuit.copy(resistorInputs = updatedInputs, totalResistance = totalResistance)
        sharedPreferencesAdapter.setCircuitPreference(updatedCircuit)
        savedStateHandle[KEY_SCREEN_STATE_TO] = updatedCircuit
    }

    private fun calculateTotalResistance(circuit: Circuit): String {
        val isSameValues = circuit.isSameValues
        val resistorCount = circuit.resistorCount
        val resistorInputs = circuit.getInputs()
        return if (isSeriesCircuit) {
            TotalResistanceSeries.execute(isSameValues, resistorCount, resistorInputs)
        } else {
            TotalResistanceParallel.execute(isSameValues, resistorCount, resistorInputs)
        }
    }
}
