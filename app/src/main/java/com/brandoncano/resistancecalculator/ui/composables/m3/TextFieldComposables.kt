package com.brandoncano.resistancecalculator.ui.composables.m3

import androidx.compose.foundation.Image
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Error
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.toSize

@Composable
fun M3TextField(
    label: String,
    modifier: Modifier = Modifier,
    value: String = "",
    enabled: Boolean = true,
    isError: Boolean = false,
    errorMessage: String = "",
    keyboardOptions: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
    onValueChange: (String) -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }
    var textFieldSize by remember { mutableStateOf(Size.Zero) }
    val displayText = value

    OutlinedTextField(
        value = displayText,
        onValueChange = onValueChange,
        modifier = modifier
            .fillMaxWidth()
            .onGloballyPositioned { coordinates -> textFieldSize = coordinates.size.toSize() },
        enabled = enabled,
        label = { Text(text = label, maxLines = 1, overflow = TextOverflow.Ellipsis) },
        trailingIcon = {
            if (isError) {
                Image(
                    imageVector = Icons.Outlined.Error,
                    contentDescription = "Error",
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.error)
                )
            }
        },
        supportingText = if (isError && errorMessage.isNotEmpty()) {
            { Text(text = errorMessage) }
        } else {
            null
        },
        isError = isError,
        keyboardOptions = keyboardOptions,
        singleLine = true,
        interactionSource = interactionSource,
    )
}
