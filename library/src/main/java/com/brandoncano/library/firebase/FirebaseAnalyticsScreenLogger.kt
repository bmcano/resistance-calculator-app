package com.brandoncano.library.firebase

import android.content.Context
import android.os.Bundle
import com.brandoncano.library.BuildConfig
import com.google.firebase.analytics.FirebaseAnalytics

/**
 * Job: Log an analytic screen name to firebase
 */
object FirebaseAnalyticsScreenLogger {

    fun execute(context: Context, event: FirebaseAnalyticsEvent) {
        if (BuildConfig.DEBUG) return // We don't want to log in DEBUG mode
        val bundle = Bundle()
        bundle.putString(FirebaseAnalytics.Param.SCREEN_NAME, event.analyticName)
        val firebaseAnalytics = FirebaseAnalytics.getInstance(context)
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW, bundle)
    }
}
