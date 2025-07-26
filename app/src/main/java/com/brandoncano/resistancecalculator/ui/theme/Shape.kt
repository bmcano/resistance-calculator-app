package com.brandoncano.resistancecalculator.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.brandoncano.resistancecalculator.ui.composables.m3.ComponentPreviews

@Composable
fun RoundedSquare(color: Color, size: Dp) {
    Box(
        modifier = Modifier
            .size(size)
            .clip(MaterialTheme.shapes.extraSmall)
            .background(color)
    )
}

@ComponentPreviews
@Composable
private fun RoundedSquarePreview() {
    ResistorCalculatorTheme {
        RoundedSquare(red, 40.dp)
    }
}
