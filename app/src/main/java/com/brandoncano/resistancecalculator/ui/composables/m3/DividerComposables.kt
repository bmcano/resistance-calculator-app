package com.brandoncano.resistancecalculator.ui.composables.m3

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * A horizontal divider with optional inset padding on both sides.
 *
 * @param insetPadding Horizontal [Dp] inset to apply on the start and end.
 * @param modifier Optional [Modifier] for styling.
 */
@Composable
fun M3Divider(
    modifier: Modifier = Modifier,
    insetPadding: Dp = 0.dp,
) {
    HorizontalDivider(modifier = modifier.padding(horizontal = insetPadding))
}
