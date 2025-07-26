package com.brandoncano.resistancecalculator.ui.composables.m3

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun filledCardColor(): Color {
    return MaterialTheme.colorScheme.surfaceContainerHighest
}

@Composable
fun outlinedCardColor(): Color {
    return MaterialTheme.colorScheme.surface
}

@Composable
fun elevatedCardColor(): Color {
    return MaterialTheme.colorScheme.surfaceContainerLow
}

@Composable
fun M3Card(
    modifier: Modifier = Modifier,
    content: @Composable (ColumnScope.() -> Unit),
) {
    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.large,
        content = content,
    )
}

@Composable
fun M3ElevatedCard(
    modifier: Modifier = Modifier,
    defaultElevation: Dp = 1.dp,
    content: @Composable (ColumnScope.() -> Unit),
) {
    ElevatedCard(
        modifier = modifier,
        shape = MaterialTheme.shapes.large,
        content = content,
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = defaultElevation
        ),
    )
}

@Composable
fun M3OutlinedCard(
    modifier: Modifier = Modifier,
    content: @Composable (ColumnScope.() -> Unit),
) {
    OutlinedCard(
        modifier = modifier,
        shape = MaterialTheme.shapes.large,
        content = content,
    )
}
