package com.brandoncano.resistancecalculator.ui

import android.app.Application
import android.util.Log
import android.widget.Toast
import com.brandoncano.resistancecalculator.R
import com.brandoncano.resistancecalculator.BuildConfig
import com.brandoncano.resistancecalculator.firebase.FIREBASE_TAG
import com.brandoncano.resistancecalculator.firebase.FirebaseRemoteConfigBackupValues
import com.google.firebase.Firebase
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
        Log.i(TAG, "onCreate called")
        setupFirebaseRemoteConfig()
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
