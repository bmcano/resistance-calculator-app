package com.brandoncano.resistancecalculator.util.share

import android.app.Activity
import android.content.Context
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy

/**
 * Job: Combines all aspects of the sharing logic together to share a composable as a JPG.
 */
object ShareResistor {

    fun execute(
        activity: Activity,
        context: Context,
        applicationId: String,
        content: @Composable () -> Unit,
    ) {
        val window = activity.window ?: return
        val decorView = window.decorView as ViewGroup
        val composeView = ComposeView(context).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            layoutParams = ViewGroup.LayoutParams(600, 200)
            setContent { content() }
        }
        decorView.addView(composeView)
        val bitmap = GenerateViewBitmap.execute(context, composeView)
        decorView.removeView(composeView)
        val uri = SaveBitmap.execute(bitmap, context, "$applicationId.provider") ?: return
        ShareJpgImage.execute(uri, context)
    }
}
