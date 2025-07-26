package com.brandoncano.resistancecalculator.ui.composables.m3

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Looks
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import com.brandoncano.resistancecalculator.data.NavigationBarItemPO
import com.brandoncano.sharedcomponents.composables.AppComponentPreviews

@Composable
fun M3NavigationBar(
    selection: Int = 0,
    onClick: (Int) -> Unit,
    options: List<NavigationBarItemPO>,
) {
    var selectedItem by remember { mutableIntStateOf(selection) }
    NavigationBar {
        options.forEachIndexed { index, it ->
            NavigationBarItem(
                selected = selectedItem == index,
                onClick = {
                    selectedItem = index
                    onClick(selectedItem)
                },
                icon = {
                    val color = if (selectedItem == index) {
                        MaterialTheme.colorScheme.onSurface
                    } else {
                        MaterialTheme.colorScheme.onSurfaceVariant
                    }
                    Image(
                        imageVector = it.imageVector,
                        contentDescription = null,
                        modifier = Modifier.size(32.dp),
                        colorFilter = ColorFilter.tint(color),
                    )
                },
                label = { Text(text = it.label) },
            )
        }
    }
}

@AppComponentPreviews
@Composable
private fun NavigationBarPreview() {
    M3NavigationBar(
        selection = 0,
        onClick = {},
        options = listOf(
            NavigationBarItemPO("Option 1", Icons.Outlined.Looks),
            NavigationBarItemPO("Option 2", Icons.Outlined.Looks),
            NavigationBarItemPO("Option 3", Icons.Outlined.Looks),
        )
    )
}
