package com.brandoncano.inductancecalculator.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.brandoncano.library.m3.ComponentPreviews

@Composable
fun RoundedSquare(color: Color, size: Dp) {
    Box(
        modifier = Modifier
            .size(size)
            .clip(RoundedCornerShape(4.dp))
            .background(color)
    )
}

@ComponentPreviews
@Composable
private fun RoundedSquarePreview() {
    InductorCalculatorTheme {
        RoundedSquare(red, 40.dp)
    }
}
