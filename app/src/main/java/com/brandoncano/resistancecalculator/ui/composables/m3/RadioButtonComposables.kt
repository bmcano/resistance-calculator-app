package com.brandoncano.resistancecalculator.ui.composables.m3

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.brandoncano.sharedcomponents.composables.AppComponentPreviews
import kotlin.collections.forEach

/**
 * Renders a vertical group of radio buttons for selecting one option.
 *
 * @param options A list of labels for each radio button; at least two items required.
 * @param horizontalInsetPadding Space from the start of the screen to the content (default 16.dp).
 * @param verticalPadding Space between rows (default 8.dp).
 */
@Composable
fun M3RadioButtonGroup(
    options: List<String>,
    optionSelected: String,
    onOptionSelected: (String) -> Unit,
    horizontalInsetPadding: Dp = 16.dp,
    verticalPadding: Dp = 8.dp,
) {
    if (options.size < 2) return
    val (selectedOption, onSelectedOption) = remember { mutableStateOf(optionSelected) }
    Column(
        modifier = Modifier.selectableGroup(),
    ) {
        options.forEach { option ->
            val selected = (option == selectedOption)
            Row(
               modifier = Modifier
                   .fillMaxWidth()
                   .selectable(
                       selected = selected,
                       onClick = {
                           onSelectedOption(option)
                           onOptionSelected(option)
                       },
                       role = Role.RadioButton,
                   )
                   .padding(
                       horizontal = horizontalInsetPadding,
                       vertical = verticalPadding,
                   ),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                RadioButton(
                    selected = (option == selectedOption),
                    onClick = null, // null recommended for screen readers
                )
                Text(
                    text = option,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(start = 16.dp),
                )
            }
        }
    }
}

@AppComponentPreviews
@Composable
private fun RadioButtonPreview() {
    M3RadioButtonGroup(
        options = listOf("Light", "Dark", "System default"),
        optionSelected = "Dark",
        onOptionSelected = {},
    )
}
