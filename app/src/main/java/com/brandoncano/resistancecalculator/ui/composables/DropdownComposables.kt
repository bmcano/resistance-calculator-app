package com.brandoncano.resistancecalculator.ui.composables

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.brandoncano.resistancecalculator.data.DropdownItem
import com.brandoncano.resistancecalculator.ui.theme.ResistorCalculatorTheme
import com.brandoncano.resistancecalculator.ui.theme.RoundedSquare
import com.brandoncano.resistancecalculator.ui.theme.resistor_beige
import com.brandoncano.resistancecalculator.util.ColorFinder
import com.brandoncano.sharedcomponents.composables.AppComponentPreviews
import com.brandoncano.sharedcomponents.text.onSurfaceVariant

@OptIn(ExperimentalMaterial3Api::class) // For ExposedDropdownMenuBox
@Composable
fun ImageTextDropDownMenu(
    modifier: Modifier = Modifier,
    label: String,
    selectedOption: String = "",
    items: List<DropdownItem>,
    isValueToColor: Boolean = false,
    onOptionSelected: (String) -> Unit,
) {
    val focusManager = LocalFocusManager.current
    var expanded by remember { mutableStateOf(false) }
    val leadingColor = remember(selectedOption) {
        ColorFinder.textToColor(selectedOption)
    }
    val displayText = selectedOption

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier = modifier.fillMaxWidth(),
    ) {
        OutlinedTextField(
            value = displayText,
            onValueChange = {},
            readOnly = true,
            label = { Text(text = label) },
            leadingIcon = if (displayText.isNotEmpty()) {
                { RoundedSquare(color = leadingColor, size = 24.dp) }
            } else {
                null
            },
            trailingIcon = {
                Icon(
                    imageVector = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                    contentDescription = null,
                    modifier = Modifier.clickable { expanded = !expanded },
                )
            },
            modifier = Modifier
                .menuAnchor(MenuAnchorType.PrimaryNotEditable, true)
                .fillMaxWidth(),
            colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors(),
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ) {
            items.forEach {
                DropdownItemView(it) {
                    val newVal = if (isValueToColor) it.value else it.name
                    onOptionSelected(newVal)
                    expanded = false
                    focusManager.clearFocus()
                }
            }
        }
    }
}

@Deprecated("User other format")
@OptIn(ExperimentalMaterial3Api::class) // For ExposedDropdownMenuBox
@Composable
fun ImageTextDropDownMenu(
    modifier: Modifier = Modifier,
    @StringRes label: Int,
    selectedOption: String = "",
    items: List<DropdownItem>,
    reset: Boolean = false,
    isValueToColor: Boolean = false,
    onOptionSelected: (String) -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf(selectedOption) }
    var selectedLeadingIconColor by remember {
        val color = ColorFinder.textToColor(selectedOption)
        mutableStateOf(color)
    }
    val icon = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown
    val focusManager = LocalFocusManager.current

    LaunchedEffect(reset) {
        if (reset) {
            selectedText = ""
            selectedLeadingIconColor = resistor_beige
        }
    }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier = modifier.fillMaxWidth(),
    ) {
        OutlinedTextField(
            value = selectedText,
            onValueChange = {},
            readOnly = true,
            label = { Text(stringResource(label)) },
            leadingIcon = if (selectedLeadingIconColor != resistor_beige) {
                { RoundedSquare(color = selectedLeadingIconColor, size = 24.dp) }
            } else null,
            trailingIcon = {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    modifier = Modifier.clickable { expanded = !expanded }
                )
            },
            modifier = Modifier
                .menuAnchor(MenuAnchorType.PrimaryNotEditable, true)
                .fillMaxWidth(),
            colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors(),
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ) {
            items.forEach {
                DropdownItemView(it) {
                    selectedText = if (isValueToColor) it.value else it.name
                    selectedLeadingIconColor = ColorFinder.textToColor(it.name)
                    expanded = false
                    onOptionSelected(if (isValueToColor) it.value else it.name)
                    focusManager.clearFocus()
                }
            }
        }
    }
}

@Composable
private fun DropdownItemView(item: DropdownItem, onClick: () -> Unit) {
    val color = ColorFinder.textToColor(item.name)
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            RoundedSquare(color = color, size = 40.dp)
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(
                    text = item.name,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(start = 8.dp, end = 8.dp)
                )
                Text(
                    text = item.value,
                    style = MaterialTheme.typography.bodySmall.onSurfaceVariant(),
                    modifier = Modifier.padding(start = 8.dp, end = 8.dp)
                )
            }
        }
    }
}

@AppComponentPreviews
@Composable
private fun CustomDropdownRowPreview() {
    ResistorCalculatorTheme {
        val item1 = DropdownItem(name = "Item 1", value = "Value 1")
        Column {
            DropdownItemView(item1) { }
            DropdownItemView(item1) { }
        }
    }
}

@AppComponentPreviews
@Composable
private fun CustomDropdownPreview() {
    val item1 = DropdownItem(name = "Item 1", value = "Value 1")
    val list = listOf(item1)
    ResistorCalculatorTheme {
        Column {
            ImageTextDropDownMenu(Modifier, "Default Text", "", list) { }
            ImageTextDropDownMenu(Modifier, "", "Red", list) { }
        }
    }
}
