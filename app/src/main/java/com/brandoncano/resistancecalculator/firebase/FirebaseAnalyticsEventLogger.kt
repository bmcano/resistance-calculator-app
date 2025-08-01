package com.brandoncano.resistancecalculator.firebase

import com.brandoncano.resistancecalculator.BuildConfig
import com.brandoncano.resistancecalculator.ui.ResistorApplication
import com.google.firebase.analytics.FirebaseAnalytics

/**
 * Job: Log an analytic event to firebase
 */
object FirebaseAnalyticsEventLogger {

    fun execute(event: FirebaseAnalyticsEvent) {
        if (BuildConfig.DEBUG) return // We don't want to log in DEBUG mode
        val application = ResistorApplication.instance
        val firebaseAnalytics = FirebaseAnalytics.getInstance(application)
        firebaseAnalytics.logEvent(event.analyticName, null)
    }
}
