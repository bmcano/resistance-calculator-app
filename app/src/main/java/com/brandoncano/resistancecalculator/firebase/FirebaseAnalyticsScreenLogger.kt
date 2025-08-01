package com.brandoncano.resistancecalculator.firebase

import android.os.Bundle
import com.brandoncano.resistancecalculator.BuildConfig
import com.brandoncano.resistancecalculator.ui.ResistorApplication
import com.google.firebase.analytics.FirebaseAnalytics

/**
 * Job: Log an analytic screen name to firebase
 */
object FirebaseAnalyticsScreenLogger {

    fun execute(event: FirebaseAnalyticsEvent) {
        if (BuildConfig.DEBUG) return // We don't want to log in DEBUG mode
        val bundle = Bundle()
        bundle.putString(FirebaseAnalytics.Param.SCREEN_NAME, event.analyticName)
        val firebaseAnalytics = FirebaseAnalytics.getInstance(ResistorApplication.instance)
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW, bundle)
    }
}
