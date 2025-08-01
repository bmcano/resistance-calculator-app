package com.brandoncano.resistancecalculator.util

import android.content.Context
import com.brandoncano.library.util.SendFeedback
import com.brandoncano.resistancecalculator.BuildConfig

object SendFeedbackWrapper {

    fun execute(context: Context) {
        SendFeedback.execute(context, "Resistor Color Code", BuildConfig.VERSION_NAME)
    }
}
