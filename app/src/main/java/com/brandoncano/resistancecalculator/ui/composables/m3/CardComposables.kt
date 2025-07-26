package com.brandoncano.resistancecalculator.ui.composables.m3

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Suppress("unused")
@Composable
fun filledCardColor(): Color {
    return MaterialTheme.colorScheme.surfaceContainerHighest
}

@Suppress("unused")
@Composable
fun outlinedCardColor(): Color {
    return MaterialTheme.colorScheme.surface
}

@Suppress("unused")
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

@ComponentPreviews
@Composable
private fun CardsPreview() {
    MaterialTheme {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp),
        ) {
            M3Card {
                Text(
                    text = "Filled Card",
                    modifier = Modifier.padding(24.dp),
                )
            }
            M3ElevatedCard {
                Text(
                    text = "Elevated Card",
                    modifier = Modifier.padding(24.dp),
                )
            }
            M3OutlinedCard {
                Text(
                    text = "Outlined Card",
                    modifier = Modifier.padding(24.dp),
                )
            }
        }
    }
}
