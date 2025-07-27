package com.brandoncano.resistancecalculator.util

import android.app.AlertDialog
import android.content.Context
import com.brandoncano.resistancecalculator.R

/**
 * Job: Build an alert dialog for when an error occurs
 */
object ErrorDialog {

    fun build(context: Context, message: String) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(context.getString(R.string.error_dialog_title))
            .setMessage(message)
            .setPositiveButton(context.getString(R.string.error_dialog_close), null)
            .show()
    }
}
