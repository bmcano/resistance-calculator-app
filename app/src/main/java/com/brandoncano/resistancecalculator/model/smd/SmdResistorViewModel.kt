package com.brandoncano.resistancecalculator.model.smd

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.brandoncano.resistancecalculator.util.resistor.formatResistance
import com.brandoncano.resistancecalculator.util.resistor.isSmdInputInvalid
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SmdResistorViewModel(context: Context): ViewModel() {

    private val repository = SmdResistorRepository.getInstance(context)

    private val _resistor = MutableStateFlow(SmdResistor())
    val resistor: StateFlow<SmdResistor> get() = _resistor

    private val _isError = MutableStateFlow(false)
    val isError: StateFlow<Boolean> get() = _isError

    init {
        viewModelScope.launch {
            val loadedResistor = repository.loadResistor()
            _resistor.value = loadedResistor
            updateErrorState()
        }
    }

    fun clear() {
        _resistor.value = SmdResistor(navBarSelection = getNavBarSelection())
        _isError.value = false
        repository.clear()
    }

    fun updateValues(code: String, units: String) {
        _resistor.value = _resistor.value.copy(code = code, units = units)
        updateErrorState()
        if (!_isError.value) {
            _resistor.value.formatResistance()
            saveResistorValues()
        }
    }

    fun getNavBarSelection(): Int {
        return _resistor.value.navBarSelection
    }

    fun saveNavBarSelection(number: Int) {
        val navBarSelection = number.coerceIn(0..2)
        _resistor.value = _resistor.value.copy(navBarSelection = navBarSelection)
        updateErrorState()
        if (!_isError.value) {
            _resistor.value.formatResistance()
            saveResistorValues()
        }
        repository.saveNavBarSelection(navBarSelection)
    }

    private fun saveResistorValues() {
        repository.saveResistor(_resistor.value)
    }

    private fun updateErrorState() {
        _isError.value = _resistor.value.isSmdInputInvalid()
    }
}
