package com.brandoncano.resistancecalculator.util.share

import android.content.Context
import android.content.Intent
import android.net.Uri

/**
 * Job: Take a uri as an image and share it
 */
object ShareJpgImage {

    fun execute(uri: Uri, context: Context) {
        val shareIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_STREAM, uri)
            type = "image/jpg"
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }
        context.startActivity(Intent.createChooser(shareIntent, "Share via"))
    }
}
