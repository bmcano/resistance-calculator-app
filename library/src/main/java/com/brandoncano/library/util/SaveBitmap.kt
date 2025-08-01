package com.brandoncano.library.util

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import androidx.core.content.FileProvider
import com.brandoncano.library.R
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException

/**
 * Job: Takes a bitmap and saves it to internal storage so a Uri can be created, returns null if fails
 */
object SaveBitmap {

    fun execute(bitmap: Bitmap, context: Context, applicationId: String): Uri? {
        val errorMessage = context.getString(R.string.error_body_share_image)
        val imagesFolder = File(context.cacheDir, "images")
        try {
            imagesFolder.mkdirs()
            val file = File(imagesFolder, "shared_image.jpg")
            val stream = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
            stream.flush()
            stream.close()
            return FileProvider.getUriForFile(
                context.applicationContext,
                applicationId,
                file
            )
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
            ErrorDialog.build(context, errorMessage)
            return null
        } catch (e: IOException) {
            e.printStackTrace()
            ErrorDialog.build(context, errorMessage)
            return null
        }
    }
}
