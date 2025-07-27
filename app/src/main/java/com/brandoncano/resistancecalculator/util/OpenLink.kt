package com.brandoncano.resistancecalculator.util

import android.content.Context
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.net.toUri

/**
 * Job: Open a web link based on a given URL
 */
object OpenLink {

    fun execute(context: Context, url: String) {
        try {
            val builder = CustomTabsIntent.Builder()
            val customTabsIntent = builder.build()
            customTabsIntent.launchUrl(context, url.toUri())
        } catch (e: Exception) {
            e.printStackTrace()
            ErrorDialog.build(context, "A problem occurred while trying to open the link.")
        }
    }
}
