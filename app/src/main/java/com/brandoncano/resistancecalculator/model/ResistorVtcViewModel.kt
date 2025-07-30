package com.brandoncano.resistancecalculator.model

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.brandoncano.resistancecalculator.adapter.SharedPreferencesAdapter
import com.brandoncano.resistancecalculator.constants.Symbols
import com.brandoncano.resistancecalculator.data.ESeriesCardContent
import com.brandoncano.resistancecalculator.to.ResistorVtc
import com.brandoncano.resistancecalculator.util.eseries.CalculateClosestStandardValue
import com.brandoncano.resistancecalculator.util.eseries.DeriveESeries
import com.brandoncano.resistancecalculator.util.eseries.ParseResistanceValue
import com.brandoncano.resistancecalculator.util.eseries.tolerancePercentage
import com.brandoncano.resistancecalculator.util.resistor.formatResistor
import com.brandoncano.resistancecalculator.util.resistor.isInputInvalid

class ResistorVtcViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {

    private companion object {
        private const val TAG = "ResistorVtcViewModel"
        private const val KEY_SCREEN_STATE_TO = "KEY_SCREEN_STATE_TO"
        private const val KEY_ERROR_STATE_BOOL = "KEY_ERROR_STATE_BOOL"
        private const val KEY_E_SERIES_CONTENT_STATE_TO = "KEY_E_SERIES_CONTENT_STATE_TO"
        private const val KEY_CLOSEST_STANDARD_VALUE_FLOAT = "KEY_CLOSEST_STANDARD_VALUE_FLOAT"
    }

    private val sharedPreferencesAdapter = SharedPreferencesAdapter()
    val resistorStateTOStateFlow = savedStateHandle.getStateFlow(KEY_SCREEN_STATE_TO, ResistorVtc())
    val isErrorStateFlow = savedStateHandle.getStateFlow(KEY_ERROR_STATE_BOOL, false)
    val eSeriesCardContentStateTOStateFlow = savedStateHandle.getStateFlow(KEY_E_SERIES_CONTENT_STATE_TO, ESeriesCardContent.DefaultContent)
    val closestStandardValueStateFlow = savedStateHandle.getStateFlow(KEY_CLOSEST_STANDARD_VALUE_FLOAT, 10.0)

    init {
        Log.d(TAG, "Init: $this")
        setInitialScreenState()
    }

    private fun setInitialScreenState() {
        Log.d(TAG, "setInitialScreenState")
        savedStateHandle[KEY_SCREEN_STATE_TO] = deriveContentTO()
    }

    private fun deriveContentTO(): ResistorVtc {
        val resistor = sharedPreferencesAdapter.getResistorVtcPreference()
        deriveErrorState(resistor)
        return resistor
    }

    private fun deriveErrorState(resistor: ResistorVtc) {
        val isInvalid = resistor.isInputInvalid()
        savedStateHandle[KEY_ERROR_STATE_BOOL] = isInvalid
    }

    fun updateCardContentState(content: ESeriesCardContent) {
        savedStateHandle[KEY_E_SERIES_CONTENT_STATE_TO] = content
    }

    fun clear() {
        val currentNavBar = resistorStateTOStateFlow.value.navBarSelection
        val blankResistor = ResistorVtc(navBarSelection = currentNavBar)

        sharedPreferencesAdapter.setResistorVtcPreference(blankResistor)
        savedStateHandle[KEY_SCREEN_STATE_TO] = blankResistor
        savedStateHandle[KEY_ERROR_STATE_BOOL] = false
        savedStateHandle[KEY_E_SERIES_CONTENT_STATE_TO] = ESeriesCardContent.DefaultContent
    }

    fun updateValues(resistance: String, units: String, band5: String, band6: String) {
        val currentResistor = resistorStateTOStateFlow.value
        val updatedResistor = currentResistor.copy(
            resistance = resistance,
            units = units,
            band5 = band5,
            band6 = band6
        )

        deriveErrorState(updatedResistor)
        if (!isErrorStateFlow.value) {
            updatedResistor.formatResistor()
        }

        sharedPreferencesAdapter.setResistorVtcPreference(updatedResistor)
        savedStateHandle[KEY_SCREEN_STATE_TO] = updatedResistor
    }

    fun updateNavBarSelection(number: Int) {
        val navBar = number.coerceIn(0..3)
        val currentResistor = resistorStateTOStateFlow.value
        val updatedResistor = currentResistor.copy(navBarSelection = navBar)

        deriveErrorState(updatedResistor)
        if (!isErrorStateFlow.value) {
            updatedResistor.formatResistor()
        }

        sharedPreferencesAdapter.setResistorVtcPreference(updatedResistor)
        savedStateHandle[KEY_SCREEN_STATE_TO] = updatedResistor
    }

    fun validateResistance() {
        val resistor = resistorStateTOStateFlow.value
        val isError = isErrorStateFlow.value
        if (resistor.isEmpty() || isError) return

        val units = resistor.units
        val resistance = resistor.resistance
        val navBarSelection = resistor.navBarSelection
        val resistanceValue = ParseResistanceValue.execute(resistance, units) ?: return
        val tolerance = if (navBarSelection == 0) "${Symbols.PM}20%" else resistor.band5

        val tolerancePercentage = tolerance.tolerancePercentage() ?: run {
            val content = ESeriesCardContent.InvalidTolerance("${navBarSelection + 3}")
            updateCardContentState(content)
            return
        }

        val eSeriesList = DeriveESeries.execute(tolerancePercentage, navBarSelection + 3)
        if (eSeriesList.isNullOrEmpty()) {
            val content = ESeriesCardContent.InvalidTolerance("${navBarSelection + 3}")
            updateCardContentState(content)
            return
        }

        val (content, closestValue) = CalculateClosestStandardValue.execute(resistanceValue, units, eSeriesList)
        savedStateHandle[KEY_E_SERIES_CONTENT_STATE_TO] = content
        savedStateHandle[KEY_CLOSEST_STANDARD_VALUE_FLOAT] = closestValue
    }
}
