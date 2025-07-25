package com.brandoncano.resistancecalculator.ui.theme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.brandoncano.sharedcomponents.composables.AppComponentPreviews

/**
 * Note: Text colors
 */

@Composable
fun TextStyle.white() = this.merge(
    color = white
)

@Composable
fun TextStyle.black() = this.merge(
    color = black
)

@Composable
fun TextStyle.gray() = this.merge(
    color = MaterialTheme.colorScheme.onSurfaceVariant
)

@AppComponentPreviews
@Composable
private fun TextStyleColorsPreview() {
    ResistorCalculatorTheme {
        Column(
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "White",
                style = MaterialTheme.typography.titleMedium.white(),
            )
            Text(
                text = "Black",
                style = MaterialTheme.typography.titleMedium.black(),
            )
            Text(
                text = "Black",
                style = MaterialTheme.typography.titleMedium.gray(),
            )
        }
    }
}
