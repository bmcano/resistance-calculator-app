package com.brandoncano.resistancecalculator.model.led

import android.content.Context
import android.util.Log
import androidx.core.content.edit
import com.brandoncano.resistancecalculator.to.led.LedCircuit
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException

/**
 * Notes:
 * + TODO: determine if we want to save results state
 * + We store the `LedCircuitInput` data class as JSON data
 * + Device File Explorer -> `/data/data/com.brandoncano.resistancecalculator/shared_prefs/led_resistance.xml`
 */
class LedCircuitRepository(context: Context) {

    companion object {
        private const val TAG = "LedRepository"
        private const val NAME_LED_SHARED_PREFS = "led_resistance"
        private const val KEY_LED_JSON = "led_json"

        @Volatile
        private var instance: LedCircuitRepository? = null
        fun getInstance(context: Context): LedCircuitRepository = instance
            ?: synchronized(this) {
                LedCircuitRepository(context).also {
                    instance = it
                }
            }
    }

    private val sharedPreferences = context.getSharedPreferences(NAME_LED_SHARED_PREFS, Context.MODE_PRIVATE)
    private val gson = Gson()

    fun loadLedCircuit(): LedCircuit {
        val json = sharedPreferences.getString(KEY_LED_JSON, null)
        if (json.isNullOrEmpty()) {
            Log.d(TAG, "No existing JSON, returning default LedCircuitInput")
            return LedCircuit()
        }

        return try {
            gson.fromJson(json, LedCircuit::class.java)
        } catch (e: JsonSyntaxException) {
            Log.e(TAG, "Failed to parse JSON, returning default. Error:", e)
            LedCircuit()
        }
    }

    fun clearData(navBarSelection: Int) {
        val blank = LedCircuit(navBarSelection = navBarSelection)
        val json = gson.toJson(blank)
        sharedPreferences.edit {
            remove(KEY_LED_JSON)
            putString(KEY_LED_JSON, json)
        }
    }

    fun saveLedCircuit(ledCircuit: LedCircuit) {
        sharedPreferences.edit {
            val json = gson.toJson(ledCircuit)
            putString(KEY_LED_JSON, json)
        }
    }
}
