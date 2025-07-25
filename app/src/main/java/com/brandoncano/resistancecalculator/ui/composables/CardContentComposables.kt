package com.brandoncano.resistancecalculator.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.brandoncano.resistancecalculator.data.ArrowCardButtonPO
import com.brandoncano.resistancecalculator.ui.composables.m3.M3Divider
import com.brandoncano.resistancecalculator.ui.composables.m3.M3FilledButton
import com.brandoncano.resistancecalculator.ui.composables.m3.M3OutlinedButton
import com.brandoncano.resistancecalculator.ui.composables.m3.M3SingleLineListItem
import com.brandoncano.sharedcomponents.text.onSurfaceVariant

@Suppress("unused")
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
fun CalloutCard(
    text: String,
    style: TextStyle = MaterialTheme.typography.titleMedium,
    color: Color = filledCardColor(),
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(containerColor = color)
    ) {
        Text(
            text = text,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            style = style,
            textAlign = TextAlign.Center,
        )
    }
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

@Composable
fun M3CardContent(
    modifier: Modifier = Modifier,
    headline: String,
    subhead: String,
    bodyText: String,
    icon: ImageVector? = null,
    iconTint: Color? = null,
    primaryButtonText: String? = null,
    onPrimaryClick: (() -> Unit)? = null,
    onSecondaryClick: (() -> Unit)? = null,
    secondaryButtonText: String? = null,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = headline,
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.titleLarge,
            )
            Spacer(modifier = Modifier.width(8.dp))
            if (icon != null && iconTint != null) {
                Image(
                    modifier = Modifier.size(32.dp),
                    contentDescription = null,
                    imageVector = icon,
                    colorFilter = ColorFilter.tint(iconTint),
                )
            }
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = subhead,
            style = MaterialTheme.typography.titleMedium.onSurfaceVariant(),
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = bodyText,
            style = MaterialTheme.typography.bodyMedium,
        )
        Spacer(modifier = Modifier.height(16.dp))
        if (secondaryButtonText != null || primaryButtonText != null) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                if (secondaryButtonText != null && onSecondaryClick != null) {
                    M3OutlinedButton(
                        buttonLabel = secondaryButtonText,
                        onClick = onSecondaryClick,
                        minHeight = 40.dp,
                    )
                }
                if (
                    secondaryButtonText != null && onSecondaryClick != null &&
                    primaryButtonText != null && onPrimaryClick != null
                ) {
                    Spacer(modifier = Modifier.width(8.dp))
                }

                if (primaryButtonText != null && onPrimaryClick != null) {
                    M3FilledButton(
                        buttonLabel = primaryButtonText,
                        onClick = onPrimaryClick,
                        minHeight = 40.dp,
                    )
                }
            }
        }
    }
}