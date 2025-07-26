package com.brandoncano.resistancecalculator.ui.composables.m3

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.brandoncano.resistancecalculator.constants.Symbols

/**
 * A reusable bullet list component that displays a vertical list of bullet points with uniform spacing
 * and consistent text styling. Each item is rendered as a row with a bullet character and the provided text.
 *
 * @param bulletStrings A list of strings to display as bullet points.
 * @param textStyle The [TextStyle] used for both the bullet character and the text content.
 * @param bulletVerticalSpace The vertical spacing between bullet items. Default is `8.dp`.
 * @param bulletEndPadding The horizontal padding between the bullet character and the text. Default is `16.dp`.
 * @param bulletStartPadding The start padding for the whole list from the left screen edge. Default is `16.dp`.
 */
@Composable
fun M3BulletList(
    bulletStrings: List<String>,
    textStyle: TextStyle,
    bulletVerticalSpace: Dp = 8.dp,
    bulletEndPadding: Dp = 16.dp,
    bulletStartPadding: Dp = 16.dp,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = bulletStartPadding),
    ) {
        bulletStrings.forEachIndexed { index, bulletString ->
            Column {
                BulletRow(
                    text = bulletString,
                    textStyle = textStyle,
                    bulletEndPadding = bulletEndPadding,
                )
                if (index != bulletStrings.lastIndex) {
                    Spacer(modifier = Modifier.height(bulletVerticalSpace))
                }
            }
        }
    }
}

@Composable
private fun BulletRow(
    text: String,
    textStyle: TextStyle,
    bulletEndPadding: Dp,
) {
    Row {
        Text(
            text = Symbols.BULLET,
            modifier = Modifier.padding(end = bulletEndPadding),
            style = textStyle,
        )
        Text(
            text = text,
            style = textStyle,
        )
    }
}

@ComponentPreviews
@Composable
private fun AppBulletListPreview() {
    Column {
        Text(
            text = "Placeholder text:",
            modifier = Modifier.padding(bottom = 16.dp),
            style = MaterialTheme.typography.bodyLarge,
        )
        M3BulletList(
            bulletStrings = listOf("First item", "Second item", "Third item"),
            textStyle = MaterialTheme.typography.bodyLarge,
        )
    }
}
