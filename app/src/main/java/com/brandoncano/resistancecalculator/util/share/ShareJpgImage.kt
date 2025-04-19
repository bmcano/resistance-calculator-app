package com.brandoncano.resistancecalculator.util.share

import android.content.ClipData
import android.content.Context
import android.content.Intent
import android.net.Uri

/**
 * Job: Take a uri as an image and share it
 */
object ShareJpgImage {

    fun execute(uri: Uri, context: Context) {
        val intent = Intent(Intent.ACTION_SEND).apply {
            putExtra(Intent.EXTRA_STREAM, uri)
            clipData = ClipData.newRawUri("Image Preview", uri)
            type = context.contentResolver.getType(uri) ?: "image/jpg"
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }
        context.startActivity(Intent.createChooser(intent, "Share via"))
    }
}
