package com.brandoncano.inductancecalculator.util

import android.content.Context
import com.brandoncano.inductancecalculator.BuildConfig
import com.brandoncano.library.util.SendFeedback

object SendFeedbackWrapper {

    fun execute(context: Context) {
        SendFeedback.execute(context, "Inductor Color Code", BuildConfig.VERSION_NAME)
    }
}
