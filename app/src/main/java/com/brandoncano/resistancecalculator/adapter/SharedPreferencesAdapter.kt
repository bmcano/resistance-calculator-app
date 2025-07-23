package com.brandoncano.resistancecalculator.adapter

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.brandoncano.resistancecalculator.keys.AppAppearance
import com.brandoncano.resistancecalculator.keys.SharedPreferencesKey
import com.brandoncano.resistancecalculator.ui.MainApplication

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

    private fun getBoolean(sharedPreferencesKey: SharedPreferencesKey, default: Boolean): Boolean {
        val sharedPreferences = getSharedPreferences()
        return sharedPreferences.getBoolean(sharedPreferencesKey.key, default)
    }

    private fun setBoolean(sharedPreferencesKey: SharedPreferencesKey, value: Boolean) {
        getSharedPreferences().edit {
            putBoolean(sharedPreferencesKey.key, value)
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

    private fun removeSharedPreference(sharedPreferencesKey: SharedPreferencesKey, ) {
        getSharedPreferences().edit {
            remove(sharedPreferencesKey.key)
        }
    }

    private fun getSharedPreferences(): SharedPreferences {
        val application = MainApplication.instance
        return application.getSharedPreferences(NAME, Context.MODE_PRIVATE)
    }
}
