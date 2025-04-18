package com.brandoncano.resistancecalculator.util

import android.os.Build

@Deprecated("No longer need this for the sharing image method.")
object Sdk {

    fun isAtLeastAndroid7(): Boolean {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.N
    }
}
