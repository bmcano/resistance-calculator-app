package com.brandoncano.resistancecalculator.model.ctv

import android.content.Context
import android.util.Log
import androidx.core.content.edit
import com.brandoncano.resistancecalculator.to.ResistorCtv
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException

/**
 * Notes:
 * + We store the `ResistorCtv` data class as JSON data
 * + Device File Explorer: `/data/data/com.brandoncano.resistancecalculator/shared_prefs/color_to_value.xml`
 */
class ResistorCtvRepository(context: Context) {

    companion object {
        private const val TAG = "ResistorCtvRepository"
        private const val NAME_CTV_SHARED_PREFS = "color_to_value"
        private const val KEY_RESISTOR_CTV = "resistor_json_ctv"

        @Volatile
        private var instance: ResistorCtvRepository? = null
        fun getInstance(context: Context): ResistorCtvRepository = instance
            ?: synchronized(this) {
                ResistorCtvRepository(context).also {
                    instance = it
                }
            }
    }

    private val sharedPreferences = context.getSharedPreferences(NAME_CTV_SHARED_PREFS, Context.MODE_PRIVATE)
    private val gson = Gson()

    fun loadResistor(): ResistorCtv {
        val json = sharedPreferences.getString(KEY_RESISTOR_CTV, null)
        if (json.isNullOrEmpty()) {
            Log.d(TAG, "No existing JSON, returning default ResistorCtv")
            return ResistorCtv()
        }

        return try {
            gson.fromJson(json, ResistorCtv::class.java)
        } catch (e: JsonSyntaxException) {
            Log.e(TAG, "Failed to parse JSON, returning default. Error:", e)
            ResistorCtv()
        }
    }

    fun clearData(navBarSelection: Int) {
        val blank = ResistorCtv(navBarSelection = navBarSelection)
        val json = gson.toJson(blank)
        sharedPreferences.edit {
            remove(KEY_RESISTOR_CTV)
            putString(KEY_RESISTOR_CTV, json)
        }
    }

    fun saveResistor(resistor: ResistorCtv) {
        sharedPreferences.edit {
            val json = gson.toJson(resistor)
            putString(KEY_RESISTOR_CTV, json)
        }
    }
}
