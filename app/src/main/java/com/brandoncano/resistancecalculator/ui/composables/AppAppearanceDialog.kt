package com.brandoncano.resistancecalculator.ui.composables

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.brandoncano.resistancecalculator.R
import com.brandoncano.resistancecalculator.keys.AppAppearance
import com.brandoncano.resistancecalculator.ui.composables.m3.ComponentPreviews
import com.brandoncano.resistancecalculator.ui.composables.m3.M3RadioButtonGroup
import com.brandoncano.resistancecalculator.ui.composables.m3.M3TextButton

@Composable
fun AppAppearanceDialog(
    currentAppAppearance: AppAppearance,
    onThemeSelected: (AppAppearance) -> Unit,
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
            M3RadioButtonGroup(
                options = options,
                optionSelected = selectedOption,
                onOptionSelected = { option ->
                    val picked = appearanceOptions.first { it.second == option }.first
                    onThemeSelected(picked)
               },
                verticalPadding = 12.dp,
            )
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
        onThemeSelected = {},
        onDismissRequest = {},
    )
}
