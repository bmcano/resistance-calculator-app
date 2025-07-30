package com.brandoncano.resistancecalculator.ui

import android.app.Application
import android.util.Log
import android.widget.Toast
import com.brandoncano.resistancecalculator.BuildConfig
import com.brandoncano.resistancecalculator.R
import com.brandoncano.resistancecalculator.adapter.SharedPreferencesAdapter
import com.brandoncano.resistancecalculator.firebase.FIREBASE_TAG
import com.brandoncano.resistancecalculator.firebase.FirebaseRemoteConfigBackupValues
import com.brandoncano.resistancecalculator.keys.SharedPreferencesKey
import com.google.firebase.Firebase
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.remoteconfig.remoteConfig
import com.google.firebase.remoteconfig.remoteConfigSettings

class MainApplication : Application() {

    companion object {
        private const val TAG = "MainApplication"
        var instance = MainApplication()
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        Log.d(TAG, "onCreate called")
        setupFirstAppLaunchPreference()
        setupFirebaseAnalytics()
        setupFirebaseRemoteConfig()
    }

    private fun setupFirstAppLaunchPreference() {
        val sharedPreferencesAdapter = SharedPreferencesAdapter()
        val resetPreferences = sharedPreferencesAdapter.getResetPreferences()
        if (!resetPreferences) return
        with (sharedPreferencesAdapter) {
            removeSharedPreference(SharedPreferencesKey.KEY_COLOR_TO_VALUE)
            removeSharedPreference(SharedPreferencesKey.KEY_VALUE_TO_COLOR)
            removeSharedPreference(SharedPreferencesKey.KEY_SMD_RESISTOR)
            removeSharedPreference(SharedPreferencesKey.KEY_CIRCUIT)
            setResetPreferences()
        }
    }

    private fun setupFirebaseAnalytics() {
        if (BuildConfig.DEBUG) return // We don't want to log in DEBUG mode
        // TODO - Add shared prefs on settings (or about) allowing users to opt out
        val firebaseAnalytics = FirebaseAnalytics.getInstance(this)
        firebaseAnalytics.setAnalyticsCollectionEnabled(true)
    }

    private fun setupFirebaseRemoteConfig() {
        FirebaseRemoteConfigBackupValues.execute(this)

        val remoteConfig = Firebase.remoteConfig
        val configSettings = remoteConfigSettings {
            minimumFetchIntervalInSeconds = if (BuildConfig.DEBUG) 0L else 3600L
        }

        remoteConfig.setConfigSettingsAsync(configSettings)
        remoteConfig.setDefaultsAsync(R.xml.remote_config_defaults)
        remoteConfig
            .fetchAndActivate()
            .addOnCompleteListener {
                val isSuccessful = it.isSuccessful
                if (isSuccessful) {
                    Log.i(FIREBASE_TAG, "Remote Config retrieval succeeded.")
                } else {
                    Log.i(FIREBASE_TAG, "Remote Config retrieval failed.")
                    if (BuildConfig.DEBUG) {
                        Toast.makeText(this, "Remote Config retrieval failed.", Toast.LENGTH_LONG).show()
                    }
                }
            }

        Log.i(TAG, "Firebase remote config setup completed.")
    }
}
