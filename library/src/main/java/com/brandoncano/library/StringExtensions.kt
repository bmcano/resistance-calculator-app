package com.brandoncano.library

import android.util.Log
import com.google.gson.Gson

/**
 * Job: Project wide string extensions
 */

inline fun <reified T : Any> String?.fromJsonStringToTypeOrNull(): T? {
    if (this == null) return null
    if (T::class == String::class) return this as T
    return try {
        Gson().fromJson(this, T::class.java)
    } catch (ex: Exception) {
        Log.e("fromJsonStringToTypeOrNull", Log.getStackTraceString(ex))
        null
    }
}
