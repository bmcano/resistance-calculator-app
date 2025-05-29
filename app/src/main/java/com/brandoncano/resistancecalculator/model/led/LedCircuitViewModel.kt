package com.brandoncano.resistancecalculator.model.led

import android.content.Context
import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.brandoncano.resistancecalculator.data.LedType
import com.brandoncano.resistancecalculator.to.led.LedCircuit

class LedCircuitViewModel(private val savedStateHandle: SavedStateHandle, context: Context): ViewModel() {

    private companion object {
        private const val TAG = "LedCircuitViewModel"
        private const val KEY_LED_CIRCUIT_STATE_TO = "KEY_LED_CIRCUIT_STATE_TO"
        //private const val KEY_LED_FORWARD_VOLTAGE = "KEY_LED_FORWARD_VOLTAGE"
        private const val KEY_ERROR_STATE_BOOL = "KEY_ERROR_STATE_BOOL"
    }

    private val application = context.applicationContext
    private val repository = LedCircuitRepository.getInstance(application)
    val ledCircuitStateTOStateFlow = savedStateHandle.getStateFlow(KEY_LED_CIRCUIT_STATE_TO, LedCircuit())
    //val ledForwardVoltageStateFlow = savedStateHandle.getStateFlow(KEY_LED_FORWARD_VOLTAGE, "")
    val isErrorStateFlow = savedStateHandle.getStateFlow(KEY_ERROR_STATE_BOOL, false) // might need multiple variants of these

    init {
        Log.d(TAG, "Init: $this")
        loadData()
    }

    fun loadData() {
        val ledCircuit = repository.loadLedCircuit()
        val navBar = ledCircuit.navBarSelection

        savedStateHandle[KEY_LED_CIRCUIT_STATE_TO] = ledCircuit
        Log.d(TAG, "loadData(): ledCircuit = $ledCircuit, navBar = $navBar")
//        updateErrorState(ledCircuit)
    }

    fun clear() {
        val currentNavBar = ledCircuitStateTOStateFlow.value.navBarSelection
        repository.clearData(currentNavBar)

        val blankResistor = LedCircuit(navBarSelection = currentNavBar)
        savedStateHandle[KEY_LED_CIRCUIT_STATE_TO] = blankResistor
        savedStateHandle[KEY_ERROR_STATE_BOOL] = false
    }

    fun updateValues(sourceVoltage: String, ledForwardVoltage: String, ledCurrent: String) {
        val currentLedCircuit = ledCircuitStateTOStateFlow.value
        val updatedLedCircuit = currentLedCircuit.copy(
            sourceVoltage = sourceVoltage,
            ledForwardVoltage = ledForwardVoltage,
            ledCurrent = ledCurrent,
        )

        repository.saveLedCircuit(updatedLedCircuit)
//        updateErrorState(updatedLedCircuit)
//        if (!isErrorStateFlow.value) {
//            updatedLedCircuit.formatResistor()
//
//        }

        savedStateHandle[KEY_LED_CIRCUIT_STATE_TO] = updatedLedCircuit
    }

    fun updateLedType(display: String) {
        val led = LedType.fromDisplay(display)
        // TODO - if "Custom" then we don't want to update the voltage and current values.
        val currentLedCircuit = ledCircuitStateTOStateFlow.value
        val updatedLedCircuit = currentLedCircuit.copy(
            led = led,
            ledForwardVoltage = led.voltage.toString(),
            ledCurrent = led.current.toString(),
        )

        repository.saveLedCircuit(updatedLedCircuit)
        savedStateHandle[KEY_LED_CIRCUIT_STATE_TO] = updatedLedCircuit
        //savedStateHandle[KEY_LED_FORWARD_VOLTAGE] = led.voltage.toString()
    }

    fun updateNavBarSelection(number: Int) {
        val navBar = number.coerceIn(0..3)
        val currentLedCircuit = ledCircuitStateTOStateFlow.value
        val updatedLedCircuit = currentLedCircuit.copy(navBarSelection = navBar)

//        updateErrorState(updatedLedCircuit)
        if (!isErrorStateFlow.value) {
//            updatedLedCircuit.formatResistor()
            repository.saveLedCircuit(updatedLedCircuit)
        }

        savedStateHandle[KEY_LED_CIRCUIT_STATE_TO] = updatedLedCircuit
    }
}
