package com.brandoncano.resistancecalculator.util.share

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.view.View
import androidx.core.graphics.createBitmap

/**
 * Job: Generate a bitmap from a view object
 */
object GenerateViewBitmap {

    fun execute(context: Context, view: View): Bitmap {
        val displayMetrics = context.resources.displayMetrics
        val screenWidth = displayMetrics.widthPixels
        val screenHeight = displayMetrics.heightPixels
        val specWidth = View.MeasureSpec.makeMeasureSpec(screenWidth, screenHeight)
        view.measure(specWidth, specWidth)
        val bitmapWidth = view.measuredWidth
        val bitmapHeight = view.measuredHeight
        val bitmap = createBitmap(bitmapWidth, bitmapHeight)
        val canvas = Canvas(bitmap)
        canvas.drawColor(Color.WHITE)
        view.layout(view.left, view.top, view.right, view.bottom)
        view.draw(canvas)
        return bitmap
    }
}
