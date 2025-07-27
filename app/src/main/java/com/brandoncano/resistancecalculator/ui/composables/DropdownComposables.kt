package com.brandoncano.resistancecalculator.ui.composables

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import com.brandoncano.resistancecalculator.data.DropdownItemPO
import com.brandoncano.resistancecalculator.ui.composables.m3.ComponentPreviews
import com.brandoncano.resistancecalculator.ui.theme.ResistorCalculatorTheme
import com.brandoncano.resistancecalculator.ui.theme.RoundedSquare
import com.brandoncano.resistancecalculator.ui.theme.gray
import com.brandoncano.resistancecalculator.util.ColorFinder

@OptIn(ExperimentalMaterial3Api::class) // For ExposedDropdownMenuBox
@Composable
fun ImageTextDropDownMenu(
    modifier: Modifier = Modifier,
    label: String,
    selectedOption: String = "",
    items: List<DropdownItemPO>,
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

@Composable
private fun DropdownItemView(item: DropdownItemPO, onClick: () -> Unit) {
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
                    style = MaterialTheme.typography.bodySmall.gray(),
                    modifier = Modifier.padding(start = 8.dp, end = 8.dp)
                )
            }
        }
    }
}

@ComponentPreviews
@Composable
private fun ImageDropdownPreview() {
    ResistorCalculatorTheme {
        val item1 = DropdownItemPO(name = "Item 1", value = "Value 1")
        val list = listOf(item1)
        Column {
            ImageTextDropDownMenu(Modifier, "Default Text", "", list) { }
            ImageTextDropDownMenu(Modifier, "", "Red", list) { }
            DropdownItemView(item1) { }
        }
    }
}
