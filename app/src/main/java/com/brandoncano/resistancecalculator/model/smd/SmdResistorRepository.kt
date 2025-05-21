package com.brandoncano.resistancecalculator.model.smd

import android.content.Context
import android.util.Log
import androidx.core.content.edit
import com.brandoncano.resistancecalculator.to.SmdResistor
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException

/**
 * Notes:
 * + We store the `SmdResistor` data class as JSON data
 * + Device File Explorer -> `/data/data/com.brandoncano.resistancecalculator/shared_prefs/value_to_color.xml`
 */
class SmdResistorRepository(context: Context) {

    companion object {
        private const val TAG = "SmdResistorRepository"
        private const val NAME_SMD_SHARED_PREFS = "smd_resistor"
        private const val KEY_RESISTOR_SMD = "resistor_json_smd"

        @Volatile
        private var instance: SmdResistorRepository? = null
        fun getInstance(context: Context): SmdResistorRepository = instance
            ?: synchronized(this) {
                SmdResistorRepository(context).also {
                    instance = it
                }
            }
    }

    private val sharedPreferences = context.getSharedPreferences(NAME_SMD_SHARED_PREFS, Context.MODE_PRIVATE)
    private val gson = Gson()

    fun loadResistor(): SmdResistor {
        val json = sharedPreferences.getString(KEY_RESISTOR_SMD, null)
        if (json.isNullOrEmpty()) {
            Log.d(TAG, "No existing JSON, returning default SmdResistor")
            return SmdResistor()
        }

        return try {
            gson.fromJson(json, SmdResistor::class.java)
        } catch (e: JsonSyntaxException) {
            Log.e(TAG, "Failed to parse JSON, returning default. Error:", e)
            SmdResistor()
        }
    }

    fun clearData(navBarSelection: Int) {
        val blank = SmdResistor(navBarSelection = navBarSelection)
        val json = gson.toJson(blank)
        sharedPreferences.edit {
            remove(KEY_RESISTOR_SMD)
            putString(KEY_RESISTOR_SMD, json)
        }
    }

    fun saveResistor(resistor: SmdResistor) {
        sharedPreferences.edit {
            val json = gson.toJson(resistor)
            putString(KEY_RESISTOR_SMD, json)
        }
    }
}
