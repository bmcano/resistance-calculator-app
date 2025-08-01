package com.brandoncano.inductancecalculator.firebase

import android.util.Log
import com.google.firebase.remoteconfig.FirebaseRemoteConfig

/**
 * Job: Retrieve a string value from a remote config key or use default if connection fails.
 */
object FirebaseRemoteConfigStringRetriever {

    fun execute(firebaseRemoteConfigKey: FirebaseRemoteConfigKeys): String? {
        val firebaseRemoteConfig = try {
            FirebaseRemoteConfig.getInstance()
        } catch (ex: Exception) {
            Log.e(FIREBASE_TAG, Log.getStackTraceString(ex))
            return FirebaseRemoteConfigBackupValues.backupMap[firebaseRemoteConfigKey.pathName]
        }
        return firebaseRemoteConfig.getString(firebaseRemoteConfigKey.pathName)
    }
}
