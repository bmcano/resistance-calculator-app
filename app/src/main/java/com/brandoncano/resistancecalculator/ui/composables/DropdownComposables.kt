package com.brandoncano.resistancecalculator.ui.composables

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
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
import com.brandoncano.resistancecalculator.R
import com.brandoncano.resistancecalculator.data.DropdownItem
import com.brandoncano.resistancecalculator.ui.theme.ResistorCalculatorTheme
import com.brandoncano.resistancecalculator.ui.theme.RoundedSquare
import com.brandoncano.resistancecalculator.ui.theme.resistor_beige
import com.brandoncano.resistancecalculator.util.ColorFinder
import com.brandoncano.sharedcomponents.composables.AppComponentPreviews
import com.brandoncano.sharedcomponents.text.textStyleCaption
import com.brandoncano.sharedcomponents.text.textStyleSubhead

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
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp)
            .clickable { onClick() }
    ) {
        val color = ColorFinder.textToColor(item.name)
        RoundedSquare(color = color, size = 40.dp)
        Column(modifier = Modifier.align(Alignment.CenterVertically)) {
            Text(
                text = item.name,
                style = textStyleSubhead(),
                modifier = Modifier.padding(start = 8.dp, end = 8.dp, top = 2.dp)
            )
            Text(
                text = item.value,
                style = textStyleCaption(),
                modifier = Modifier.padding(start = 8.dp, end = 8.dp)
            )
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
    val item2 = DropdownItem(name = "Item 2", value = "Value 2")
    val item3 = DropdownItem(name = "Item 3", value = "Value 3")
    val item4 = DropdownItem(name = "Item 4", value = "Value 4")
    val item5 = DropdownItem(name = "Item 5", value = "Value 5")
    val item6 = DropdownItem(name = "Item 6", value = "Value 6")
    val list = listOf(item1, item2, item3, item4, item5, item6)
    ResistorCalculatorTheme {
        Column {
            ImageTextDropDownMenu(Modifier, R.string.number_band_hint1, "", list) { }
            ImageTextDropDownMenu(Modifier, R.string.number_band_hint1, "Red", list) { }
        }
    }
}
