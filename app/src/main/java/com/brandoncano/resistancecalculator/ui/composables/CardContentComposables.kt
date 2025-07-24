package com.brandoncano.resistancecalculator.ui.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.brandoncano.resistancecalculator.data.ArrowCardButtonPO
import com.brandoncano.resistancecalculator.ui.composables.m3.M3Divider
import com.brandoncano.resistancecalculator.ui.composables.m3.M3SingleLineListItem

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
fun ArrowCardButtonContent(color: Color, vararg arrowCardButtonPOs: ArrowCardButtonPO) {
    arrowCardButtonPOs.forEachIndexed { index, it ->
        AppCardRowView(color, it.text, it.imageVector, it.onClick)
        if (arrowCardButtonPOs.lastIndex != index) {
            M3Divider(insetPadding = 16.dp)
        }
    }
}

@Composable
private fun AppCardRowView(
    color: Color,
    text: String,
    imageVector: ImageVector,
    onClick: (() -> Unit),
) {
    M3SingleLineListItem(
        headlineText = text,
        modifier = Modifier
            .clickable { onClick.invoke() }
            .padding(vertical = 4.dp),
        leadingContent = {
            Icon(
                imageVector = imageVector,
                contentDescription = null,
            )
        },
        trailingImage = Icons.AutoMirrored.Filled.ArrowForwardIos,
        containerColor = color,
    )
}
