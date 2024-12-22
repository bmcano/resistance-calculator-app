package com.brandoncano.resistancecalculator.model.circuit

import androidx.lifecycle.ViewModel
import com.brandoncano.resistancecalculator.util.circuit.TotalResistanceSeries
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CircuitViewModel : ViewModel() {

    private val _circuit = MutableStateFlow(Circuit())
    val circuit: StateFlow<Circuit> get() = _circuit

    fun updateValues(isSame: Boolean, count: Int, units: String) {
        _circuit.value = _circuit.value.copy(sameValues = isSame, resistorCount = count, units = units)
        calculateTotalResistance()
    }

    fun clear() {
        _circuit.value = Circuit()
    }

    private fun calculateTotalResistance() {
        val resistorInputs = _circuit.value.resistorInputs.take(_circuit.value.resistorCount)
        val sameValues = _circuit.value.sameValues

        val totalResistance = if (sameValues) {
            TotalResistanceSeries.execute(
                numberOfResistors = _circuit.value.resistorCount,
                resistorValue = resistorInputs.firstOrNull() ?: "0"
            )
        } else {
            TotalResistanceSeries.execute(resistorInputs)
        }

        _circuit.value = _circuit.value.copy(totalResistance = totalResistance)
    }
}
