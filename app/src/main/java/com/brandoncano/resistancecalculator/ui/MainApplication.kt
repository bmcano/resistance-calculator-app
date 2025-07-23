package com.brandoncano.resistancecalculator.ui

import android.app.Application
import android.util.Log

class MainApplication : Application() {

    companion object {
        private const val TAG = "MainApplication"
        var instance = MainApplication()
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        Log.i(TAG, "onCreate called")
    }
}
