package com.brandoncano.resistancecalculator.ui.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.brandoncano.resistancecalculator.R
import com.brandoncano.resistancecalculator.keys.AppAppearance
import com.brandoncano.library.m3.ComponentPreviews
import com.brandoncano.library.m3.M3Divider
import com.brandoncano.library.m3.M3RadioButtonGroup
import com.brandoncano.library.m3.M3Switch
import com.brandoncano.library.m3.M3TextButton

@Composable
fun AppAppearanceDialog(
    currentAppAppearance: AppAppearance,
    onAppAppearanceSelected: (AppAppearance) -> Unit,
    dynamicColor: Boolean,
    onDynamicColorSelected: (Boolean) -> Unit,
    onDismissRequest: () -> Unit,
) {
    val appearanceOptions = listOf(
        AppAppearance.SYSTEM_DEFAULT to stringResource(R.string.dialog_system_default),
        AppAppearance.LIGHT to stringResource(R.string.dialog_light),
        AppAppearance.DARK to stringResource(R.string.dialog_dark),
    )
    val options = appearanceOptions.map { it.second }
    val selectedOption = appearanceOptions
        .first { it.first == currentAppAppearance }
        .second

    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = {
            Text(
                text = stringResource(R.string.dialog_app_appearance),
                style = MaterialTheme.typography.titleLarge,
            )
        },
        text = {
            Column {
                M3RadioButtonGroup(
                    options = options,
                    optionSelected = selectedOption,
                    onOptionSelected = { option ->
                        val picked = appearanceOptions.first { it.second == option }.first
                        onAppAppearanceSelected(picked)
                    },
                    verticalPadding = 12.dp,
                )
                M3Divider(modifier = Modifier.padding(vertical = 16.dp))
                M3Switch(
                    labelText = stringResource(R.string.dialog_dynamic_color),
                    checked = dynamicColor,
                    onCheckedChange = { onDynamicColorSelected(it) }
                )
            }
        },
        confirmButton = {
            M3TextButton(
                buttonLabel = stringResource(R.string.dialog_confirm_button),
                onClick = onDismissRequest
            )
        }
    )
}

@ComponentPreviews
@Composable
private fun AppThemeDialogPreview() {
    AppAppearanceDialog(
        currentAppAppearance = AppAppearance.SYSTEM_DEFAULT,
        onAppAppearanceSelected = {},
        dynamicColor = false,
        onDynamicColorSelected = {},
        onDismissRequest = {},
    )
}
