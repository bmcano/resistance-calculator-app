package com.brandoncano.resistancecalculator.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.brandoncano.resistancecalculator.data.ThemePreferences
import com.brandoncano.sharedcomponents.data.ThemeMode
import kotlinx.coroutines.flow.MutableStateFlow

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val _themeMode = MutableLiveData<ThemeMode>()
    val themeMode: LiveData<ThemeMode> = _themeMode

    private val _showThemeDialog = MutableStateFlow(false)
    val showThemeDialog: MutableStateFlow<Boolean> = _showThemeDialog

    init {
        val themePreference = ThemePreferences.THEME_PREFERENCE.loadIntData(application)
        _themeMode.value = ThemeMode.fromValue(themePreference)
    }

    fun setThemeMode(themeMode: ThemeMode) {
        _themeMode.value = themeMode
        ThemePreferences.THEME_PREFERENCE.saveIntData(getApplication(), themeMode.value)
    }

    fun openThemeDialog() {
        _showThemeDialog.value = true
    }

    fun closeThemeDialog() {
        _showThemeDialog.value = false
    }
}
