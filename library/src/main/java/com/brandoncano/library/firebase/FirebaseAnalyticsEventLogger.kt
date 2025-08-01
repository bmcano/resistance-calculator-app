package com.brandoncano.library.firebase

import android.content.Context
import com.brandoncano.library.BuildConfig
import com.google.firebase.analytics.FirebaseAnalytics

/**
 * Job: Log an analytic event to firebase
 */
object FirebaseAnalyticsEventLogger {

    fun execute(context: Context, event: FirebaseAnalyticsEvent) {
        if (BuildConfig.DEBUG) return // We don't want to log in DEBUG mode
        val firebaseAnalytics = FirebaseAnalytics.getInstance(context)
        firebaseAnalytics.logEvent(event.analyticName, null)
    }
}
