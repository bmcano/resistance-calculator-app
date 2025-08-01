package com.brandoncano.library.m3

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.brandoncano.library.theme.gray

/**
 * A labeled switch with optional icons for on/off states.
 *
 * @param labelText The text label displayed before the switch.
 * @param checked Current checked state.
 * @param onCheckedChange Callback invoked with the new state when toggled.
 * @param enabled Whether the switch is interactive.
 * @param checkedIcon Optional icon shown inside the thumb when checked.
 * @param uncheckedIcon Optional icon shown inside the thumb when unchecked.
 * @param horizontalInsetPadding Horizontal padding around the row (default 16.dp).
 * @param verticalPadding Vertical padding around the row (default 8.dp).
 */
@Composable
fun M3Switch(
    labelText: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    enabled: Boolean = true,
    checkedIcon: ImageVector? = null,
    uncheckedIcon: ImageVector? = null,
    horizontalInsetPadding: Dp = 16.dp,
    verticalPadding: Dp = 8.dp,
) {
    val (checkedState, onStateChange) = remember { mutableStateOf(checked) }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .toggleable(
                value = checkedState,
                enabled = enabled,
                onValueChange = {
                    onStateChange(!checkedState)
                    onCheckedChange(!checkedState)
                },
                role = Role.Switch,
            )
            .padding(
                horizontal = horizontalInsetPadding,
                vertical = verticalPadding,
            ),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = labelText,
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.bodyLarge.gray(),
        )
        Switch(
            checked = checkedState,
            onCheckedChange = null,
            modifier = Modifier.padding(start = 16.dp),
            thumbContent = {
                if (checkedState && checkedIcon != null) {
                    Icon(
                        imageVector = checkedIcon,
                        contentDescription = null,
                        modifier = Modifier.size(SwitchDefaults.IconSize),
                    )
                }
                if (!checkedState && uncheckedIcon != null) {
                    Icon(
                        imageVector = uncheckedIcon,
                        contentDescription = null,
                        modifier = Modifier.size(SwitchDefaults.IconSize),
                    )
                }
            },
            enabled = enabled,
            colors = SwitchDefaults.colors(), // Default parameter
            interactionSource = null, // Default parameter
        )
    }
}

@ComponentPreviews
@Composable
private fun SwitchPreview() {
    Column {
        M3Switch(
            labelText = "Checked switch",
            checked = true,
            onCheckedChange = {},
        )
        M3Switch(
            labelText = "Unchecked switch",
            checked = false,
            onCheckedChange = {},
        )
        M3Switch(
            labelText = "Checked switch with icon",
            checked = true,
            onCheckedChange = {},
            checkedIcon = Icons.Default.Check,
        )
        M3Switch(
            labelText = "Unchecked switch with icon",
            checked = false,
            onCheckedChange = {},
            uncheckedIcon = Icons.Default.Close,
        )
    }
}
