package com.brandoncano.resistancecalculator.model.vtc

import android.content.Context
import android.util.Log
import androidx.core.content.edit
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException

/**
 * Notes:
 * + Device File Explorer -> `/data/data/com.brandoncano.resistancecalculator/shared_prefs/value_to_color.xml`
 */
class ResistorVtcRepository(context: Context) {

    companion object {
        private const val TAG = "ResistorVtcRepository"
        private const val NAME_VTC_SHARED_PREFS = "value_to_color"
        private const val KEY_RESISTOR_VTC = "resistor_json_vtc"

        @Volatile
        private var instance: ResistorVtcRepository? = null
        fun getInstance(context: Context): ResistorVtcRepository = instance
            ?: synchronized(this) {
                ResistorVtcRepository(context).also {
                    instance = it
                }
            }
    }

    private val sharedPreferences = context.getSharedPreferences(NAME_VTC_SHARED_PREFS, Context.MODE_PRIVATE)
    private val gson = Gson()

    fun loadResistor(): ResistorVtc {
        val json = sharedPreferences.getString(KEY_RESISTOR_VTC, null)
        if (json.isNullOrEmpty()) {
            Log.d(TAG, "No existing JSON, returning default ResistorVtc")
            return ResistorVtc()
        }

        return try {
            gson.fromJson(json, ResistorVtc::class.java)
        } catch (e: JsonSyntaxException) {
            Log.e(TAG, "Failed to parse JSON, returning default. Error:", e)
            ResistorVtc()
        }
    }

    fun saveResistor(resistor: ResistorVtc) {
        sharedPreferences.edit {
            val json = gson.toJson(resistor)
            putString(KEY_RESISTOR_VTC, json)
        }
    }

    fun clearData(navBarSelection: Int) {
        val blank = ResistorVtc(navBarSelection = navBarSelection)
        val json = gson.toJson(blank)
        sharedPreferences.edit {
            remove(KEY_RESISTOR_VTC)
            putString(KEY_RESISTOR_VTC, json)
        }
    }
}
