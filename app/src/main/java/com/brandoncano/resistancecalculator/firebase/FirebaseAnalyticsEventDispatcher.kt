package com.brandoncano.resistancecalculator.firebase

import com.brandoncano.resistancecalculator.BuildConfig
import com.brandoncano.resistancecalculator.ui.MainApplication
import com.google.firebase.analytics.FirebaseAnalytics

/**
 * Job: Log an analytic even to firebase, prevents it in DEBUG
 */
object FirebaseAnalyticsEventDispatcher {

    fun execute(event: FirebaseAnalyticsEvent) {
        if (BuildConfig.DEBUG) return // We don't want to log in DEBUG mode
        val application = MainApplication.instance
        val firebaseAnalytics = FirebaseAnalytics.getInstance(application)
        firebaseAnalytics.logEvent(event.eventName, null)
    }
}
