package com.brandoncano.resistancecalculator.adapter

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.core.content.edit
import com.brandoncano.resistancecalculator.keys.AppAppearance
import com.brandoncano.resistancecalculator.keys.SharedPreferencesKey
import com.brandoncano.resistancecalculator.to.ResistorCtv
import com.brandoncano.resistancecalculator.ui.MainApplication
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException

/**
 * Notes:
 * + Device File Explorer: `/data/data/com.brandoncano.resistancecalculator/shared_prefs/ResistorSharedPrefs.xml`
 */
class SharedPreferencesAdapter {

    private companion object {
        const val NAME = "ResistorSharedPrefs"
    }

    fun getAppAppearancePreference(): String {
        val default = AppAppearance.SYSTEM_DEFAULT.toString()
        val pref = getString(SharedPreferencesKey.KEY_APP_APPEARANCE, default)
        return pref ?: default
    }

    fun setAppAppearancePreference(appAppearance: String) {
        setString(SharedPreferencesKey.KEY_APP_APPEARANCE, appAppearance)
    }

    fun getResistorCtvPreference(): ResistorCtv {
        val json = getString(SharedPreferencesKey.KEY_COLOR_TO_VALUE, null)
        if (json.isNullOrEmpty()) {
            Log.d(NAME, "No existing JSON, returning default ResistorCtv")
            return ResistorCtv()
        }

        return try {
            Gson().fromJson(json, ResistorCtv::class.java)
        } catch (e: JsonSyntaxException) {
            Log.e(NAME, "Failed to parse JSON, returning default. Error:", e)
            ResistorCtv()
        }
    }

    fun setResistorCtvPreference(resistor: ResistorCtv) {
        val json = Gson().toJson(resistor)
        setString(SharedPreferencesKey.KEY_COLOR_TO_VALUE, json)
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

    private fun removeSharedPreference(sharedPreferencesKey: SharedPreferencesKey) {
        getSharedPreferences().edit {
            remove(sharedPreferencesKey.key)
        }
    }

    private fun getSharedPreferences(): SharedPreferences {
        val application = MainApplication.instance
        return application.getSharedPreferences(NAME, Context.MODE_PRIVATE)
    }
}
