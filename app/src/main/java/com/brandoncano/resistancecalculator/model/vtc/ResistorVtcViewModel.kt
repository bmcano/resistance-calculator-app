package com.brandoncano.resistancecalculator.model.vtc

import android.content.Context
import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.brandoncano.resistancecalculator.data.ESeriesCardContent
import com.brandoncano.resistancecalculator.to.ResistorVtc
import com.brandoncano.resistancecalculator.util.resistor.formatResistor
import com.brandoncano.resistancecalculator.util.resistor.isInputInvalid

class ResistorVtcViewModel(private val savedStateHandle: SavedStateHandle, context: Context) : ViewModel() {

    private companion object {
        private const val TAG = "ResistorVtcViewModel"
        private const val KEY_RESISTOR_STATE_TO = "KEY_RESISTOR_STATE_TO"
        private const val KEY_ERROR_STATE_BOOL = "KEY_ERROR_STATE_BOOL"
        private const val KEY_E_SERIES_CONTENT_STATE_TO = "KEY_E_SERIES_CONTENT_STATE_TO"
        private const val KEY_CLOSEST_STANDARD_VALUE_FLOAT = "KEY_CLOSEST_STANDARD_VALUE_FLOAT"
    }

    private val application = context.applicationContext
    private val repository = ResistorVtcRepository.getInstance(application)
    val resistorStateTOStateFlow = savedStateHandle.getStateFlow(KEY_RESISTOR_STATE_TO,
        ResistorVtc()
    )
    val isErrorStateFlow = savedStateHandle.getStateFlow(KEY_ERROR_STATE_BOOL, false)
    val eSeriesCardContentStateTOStateFlow = savedStateHandle.getStateFlow(KEY_E_SERIES_CONTENT_STATE_TO, ESeriesCardContent.NoContent)
    val closestStandardValueStateFlow = savedStateHandle.getStateFlow(KEY_CLOSEST_STANDARD_VALUE_FLOAT, 10.0)

    init {
        Log.d(TAG, "Init: $this")
        loadData()
    }

    fun loadData() {
        val resistor = repository.loadResistor()
        val navBar = resistor.navBarSelection

        savedStateHandle[KEY_RESISTOR_STATE_TO] = resistor
        Log.d(TAG, "loadData(): resistor = $resistor, navBar = $navBar")
        updateErrorState(resistor)
    }

    fun clear() {
        val currentNavBar = resistorStateTOStateFlow.value.navBarSelection
        repository.clearData(currentNavBar)

        val blankResistor = ResistorVtc(navBarSelection = currentNavBar)
        savedStateHandle[KEY_RESISTOR_STATE_TO] = blankResistor
        savedStateHandle[KEY_ERROR_STATE_BOOL] = false
        savedStateHandle[KEY_E_SERIES_CONTENT_STATE_TO] = ESeriesCardContent.NoContent
    }

    fun updateValues(resistance: String, units: String, band5: String, band6: String) {
        val currentResistor = resistorStateTOStateFlow.value
        val updatedResistor = currentResistor.copy(
            resistance = resistance,
            units = units,
            band5 = band5,
            band6 = band6
        )

        updateErrorState(updatedResistor)
        if (!isErrorStateFlow.value) {
            updatedResistor.formatResistor()
            repository.saveResistor(updatedResistor)
        }

        // Store back in handle
        savedStateHandle[KEY_RESISTOR_STATE_TO] = updatedResistor
    }

    fun updateNavBarSelection(number: Int) {
        val navBar = number.coerceIn(0..3)
        val currentResistor = resistorStateTOStateFlow.value
        val updatedResistor = currentResistor.copy(navBarSelection = navBar)

        updateErrorState(updatedResistor)
        if (!isErrorStateFlow.value) {
            updatedResistor.formatResistor()
            repository.saveResistor(updatedResistor)
        }

        // Store updated in handle
        savedStateHandle[KEY_RESISTOR_STATE_TO] = updatedResistor
    }

    fun updateCardContent(content: ESeriesCardContent) {
        savedStateHandle[KEY_E_SERIES_CONTENT_STATE_TO] = content
    }

    fun updateClosestStandardValue(value: Double) {
        savedStateHandle[KEY_CLOSEST_STANDARD_VALUE_FLOAT] = value
    }

    private fun updateErrorState(resistor: ResistorVtc) {
        val isInvalid = resistor.isInputInvalid()
        savedStateHandle[KEY_ERROR_STATE_BOOL] = isInvalid
    }
}
