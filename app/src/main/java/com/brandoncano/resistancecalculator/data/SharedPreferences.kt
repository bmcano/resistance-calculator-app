package com.brandoncano.resistancecalculator.data

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import androidx.core.content.edit

/**
 * Job: Holds the names, keys, and methods for all shared_prefs data.
 *
 * Notes:
 *   Data is saved as xml files with mapping, where name_ -> file name; key_ -> key in map.
 *   Device File Explorer -> data -> data -> com.brandoncano.resistancecalculator -> shared_prefs
 */
enum class SharedPreferences(private val _name: String, private val _key: String) {

    NAVBAR_SELECTION_CTV("color_to_value", "navbar_selection"),
    SIGFIG_BAND_ONE_CTV("color_to_value", "sig_fig_band_1"),
    SIGFIG_BAND_TWO_CTV("color_to_value", "sig_fig_band_2"),
    SIGFIG_BAND_THREE_CTV("color_to_value", "sig_fig_band_3"),
    MULTIPLIER_BAND_CTV("color_to_value", "multiplier_band"),
    TOLERANCE_BAND_CTV("color_to_value", "tolerance_band"),
    PPM_BAND_CTV("color_to_value", "ppm_band"),

    NAVBAR_SELECTION_VTC("value_to_color", "navbar_selection"),
    USER_INPUT_VTC("value_to_color", "user_input"),
    UNITS_DROPDOWN_VTC("value_to_color", "units_dropdown"),
    TOLERANCE_DROPDOWN_VTC("value_to_color", "tolerance_dropdown"),
    PPM_DROPDOWN_VTC("value_to_color", "ppm_dropdown"),

    NAVBAR_SELECTION_SMD("smd", "navbar_selection"),
    CODE_INPUT_SMD("smd", "code_input"),
    UNITS_DROPDOWN_SMD("smd", "units_dropdown"),

    ; // methods to save, load, or clear the data as strings

    fun saveData(context: Context, input: String) {
        val sharedPreferences = context.getSharedPreferences(_name, AppCompatActivity.MODE_PRIVATE)
        sharedPreferences.edit {
            val gson = Gson()
            val json: String = gson.toJson(input)
            putString(_key, json)
        }
    }

    fun loadData(context: Context): String {
        val sharedPreferences = context.getSharedPreferences(_name, AppCompatActivity.MODE_PRIVATE)
        val gson = Gson()
        val json = sharedPreferences.getString(_key, null)
        val type: Type = object : TypeToken<String?>() {}.type
        return gson.fromJson<String?>(json, type) ?: return ""
    }

    fun clearData(context: Context) {
        val sharedPreferences = context.getSharedPreferences(_name, AppCompatActivity.MODE_PRIVATE)
        sharedPreferences.edit {
            clear()
        }
    }
}
