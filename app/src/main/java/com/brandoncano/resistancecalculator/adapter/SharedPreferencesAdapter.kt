package com.brandoncano.resistancecalculator.adapter

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.brandoncano.library.fromJsonStringToTypeOrNull
import com.brandoncano.resistancecalculator.keys.AppAppearance
import com.brandoncano.resistancecalculator.keys.SharedPreferencesKey
import com.brandoncano.resistancecalculator.to.Circuit
import com.brandoncano.resistancecalculator.to.ResistorCtv
import com.brandoncano.resistancecalculator.to.ResistorVtc
import com.brandoncano.resistancecalculator.to.SmdResistor
import com.brandoncano.resistancecalculator.ui.ResistorApplication
import com.google.gson.Gson

/**
 * Notes:
 * + Device File Explorer: `/data/data/com.brandoncano.resistancecalculator/shared_prefs/ResistorSharedPrefs.xml`
 */
class SharedPreferencesAdapter {

    private companion object {
        const val NAME = "ResistorSharedPrefs"
    }

    fun getResetPreferences(): Boolean {
        return getBoolean(SharedPreferencesKey.KEY_RESET_PREFERENCES, true)
    }

    fun setResetPreferences() {
        setBoolean(SharedPreferencesKey.KEY_RESET_PREFERENCES, false)
    }

    fun getAppAppearancePreference(): String {
        val default = AppAppearance.SYSTEM_DEFAULT.toString()
        val pref = getString(SharedPreferencesKey.KEY_APP_APPEARANCE, default)
        return pref ?: default
    }

    fun setAppAppearancePreference(appAppearance: String) {
        setString(SharedPreferencesKey.KEY_APP_APPEARANCE, appAppearance)
    }

    fun getDynamicColorPreference(): Boolean {
        return getBoolean(SharedPreferencesKey.KEY_DYNAMIC_COLOR, false)
    }

    fun setDynamicColorPreference(dynamicColor: Boolean) {
        setBoolean(SharedPreferencesKey.KEY_DYNAMIC_COLOR, dynamicColor)
    }

    fun getResistorCtvPreference(): ResistorCtv {
        val resistor = getString(SharedPreferencesKey.KEY_COLOR_TO_VALUE, null)
        return resistor.fromJsonStringToTypeOrNull() ?: ResistorCtv()
    }

    fun setResistorCtvPreference(resistor: ResistorCtv) {
        val resistorString = Gson().toJson(resistor, ResistorCtv::class.java)
        setString(SharedPreferencesKey.KEY_COLOR_TO_VALUE, resistorString)
    }

    fun getResistorVtcPreference(): ResistorVtc {
        val resistor = getString(SharedPreferencesKey.KEY_VALUE_TO_COLOR, null)
        return resistor.fromJsonStringToTypeOrNull() ?: ResistorVtc()
    }

    fun setResistorVtcPreference(resistor: ResistorVtc) {
        val resistorString = Gson().toJson(resistor, ResistorVtc::class.java)
        setString(SharedPreferencesKey.KEY_VALUE_TO_COLOR, resistorString)
    }

    fun getSmdResistorPreference(): SmdResistor {
        val smd = getString(SharedPreferencesKey.KEY_SMD_RESISTOR, null)
        return smd.fromJsonStringToTypeOrNull() ?: SmdResistor()
    }

    fun setSmdResistorPreference(resistor: SmdResistor) {
        val resistorString = Gson().toJson(resistor, SmdResistor::class.java)
        setString(SharedPreferencesKey.KEY_SMD_RESISTOR, resistorString)
    }

    fun getCircuitPreference(): Circuit {
        val circuit = getString(SharedPreferencesKey.KEY_CIRCUIT, null)
        return circuit.fromJsonStringToTypeOrNull() ?: Circuit()
    }

    fun setCircuitPreference(circuit: Circuit) {
        val circuitString = Gson().toJson(circuit, Circuit::class.java)
        setString(SharedPreferencesKey.KEY_CIRCUIT, circuitString)
    }

    fun removeSharedPreference(sharedPreferencesKey: SharedPreferencesKey) {
        getSharedPreferences().edit {
            remove(sharedPreferencesKey.key)
        }
    }

    private fun getString(sharedPreferencesKey: SharedPreferencesKey, default: String?): String? {
        val sharedPreferences = getSharedPreferences()
        return sharedPreferences.getString(sharedPreferencesKey.key, default)
    }

    private fun setString(sharedPreferencesKey: SharedPreferencesKey, value: String?) {
        getSharedPreferences().edit {
            putString(sharedPreferencesKey.key, value)
        }
    }

    private fun getBoolean(sharedPreferencesKey: SharedPreferencesKey, default: Boolean): Boolean {
        val sharedPreferences = getSharedPreferences()
        return sharedPreferences.getBoolean(sharedPreferencesKey.key, default)
    }

    private fun setBoolean(sharedPreferencesKey: SharedPreferencesKey, value: Boolean) {
        getSharedPreferences().edit {
            putBoolean(sharedPreferencesKey.key, value)
        }
    }

    private fun getSharedPreferences(): SharedPreferences {
        val application = ResistorApplication.instance
        return application.getSharedPreferences(NAME, Context.MODE_PRIVATE)
    }
}
