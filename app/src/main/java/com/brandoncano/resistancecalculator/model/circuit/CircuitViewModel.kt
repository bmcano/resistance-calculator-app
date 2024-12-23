package com.brandoncano.resistancecalculator.model.circuit

import androidx.lifecycle.ViewModel
import com.brandoncano.resistancecalculator.util.circuit.TotalResistanceParallel
import com.brandoncano.resistancecalculator.util.circuit.TotalResistanceSeries
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CircuitViewModel : ViewModel() {

    private val _circuit = MutableStateFlow(Circuit())
    val circuit: StateFlow<Circuit> get() = _circuit

    fun updateValues(isSame: Boolean, count: Int, units: String, isSeriesCalculation: Boolean) {
        _circuit.value = _circuit.value.copy(sameValues = isSame, resistorCount = count, units = units)
        calculateTotalResistance(isSeriesCalculation)
    }

    fun clear() {
        _circuit.value = Circuit()
    }

    private fun calculateTotalResistance(isSeriesCalculation: Boolean) {
        val resistorInputs = _circuit.value.resistorInputs.take(_circuit.value.resistorCount)
        val sameValues = _circuit.value.sameValues
        val resistorCount = _circuit.value.resistorCount

        val totalResistance = if (isSeriesCalculation) {
            TotalResistanceSeries.execute(sameValues, resistorCount, resistorInputs)
        } else {
            TotalResistanceParallel.execute(sameValues, resistorCount, resistorInputs)
        }

        _circuit.value = _circuit.value.copy(totalResistance = totalResistance)
    }
}
