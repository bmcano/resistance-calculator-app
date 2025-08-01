package com.brandoncano.inductancecalculator.firebase

import android.content.Context
import android.util.Log
import com.brandoncano.inductancecalculator.R
import org.xmlpull.v1.XmlPullParser

/**
 * Job: Setups remote config backup/default values from remote_config_defaults.xml
 */
object FirebaseRemoteConfigBackupValues {

    private const val KEY = "key"
    val backupMap = mutableMapOf<String, String>()

    fun execute(context: Context) {
        if (backupMap.isNotEmpty()) {
            Log.i(FIREBASE_TAG, "Remote config backup mapping has already completed.")
            return
        }

        try {
            val parser = context.resources.getXml(R.xml.remote_config_defaults)
            while (parser.eventType != XmlPullParser.END_DOCUMENT) {
                if (parser.eventType == XmlPullParser.START_TAG && parser.name == KEY) {
                    parser.next()
                    val key = parser.text
                    repeat(3) { parser.next() }
                    val value = parser.text
                    backupMap[key] = value
                }
                parser.next()
            }
        } catch (ex: Exception) {
            Log.e(FIREBASE_TAG, Log.getStackTraceString(ex))
            return
        }
        Log.i(FIREBASE_TAG, "Remote config backup mapping has completed.")
    }
}
