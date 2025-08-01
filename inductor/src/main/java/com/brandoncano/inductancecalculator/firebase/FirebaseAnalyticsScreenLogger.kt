package com.brandoncano.inductancecalculator.firebase

import android.os.Bundle
import com.brandoncano.inductancecalculator.ui.InductorApplication
import com.brandoncano.inductancecalculator.BuildConfig
import com.google.firebase.analytics.FirebaseAnalytics

/**
 * Job: Log an analytic screen name to firebase
 */
object FirebaseAnalyticsScreenLogger {

    fun execute(event: FirebaseAnalyticsEvent) {
        if (BuildConfig.DEBUG) return // We don't want to log in DEBUG mode
        val bundle = Bundle()
        bundle.putString(FirebaseAnalytics.Param.SCREEN_NAME, event.analyticName)
        val firebaseAnalytics = FirebaseAnalytics.getInstance(InductorApplication.instance)
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW, bundle)
    }
}
