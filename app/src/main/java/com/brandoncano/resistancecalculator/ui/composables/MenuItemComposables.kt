package com.brandoncano.resistancecalculator.ui.composables

import android.graphics.Picture
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Colorize
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Palette
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.brandoncano.resistancecalculator.R
import com.brandoncano.resistancecalculator.ui.theme.ResistorCalculatorTheme
import com.brandoncano.sharedcomponents.composables.AppComponentPreviews
import com.brandoncano.sharedcomponents.composables.ClearSelectionsMenuItem
import com.brandoncano.sharedcomponents.composables.FeedbackMenuItem
import com.brandoncano.sharedcomponents.composables.MenuIcon
import com.brandoncano.sharedcomponents.composables.MenuText
import com.brandoncano.sharedcomponents.composables.ShareImageMenuItem
import com.brandoncano.sharedcomponents.composables.ShareTextMenuItem

/**
 * Note: Menu items are in alphabetical order
 */

@Composable
fun AboutAppMenuItem(onAboutTapped: () -> Unit) {
    DropdownMenuItem(
        text = { MenuText(stringRes = R.string.menu_about) },
        onClick = onAboutTapped,
        leadingIcon = { MenuIcon(Icons.Outlined.Info) },
    )
}

@Composable
fun AppThemeMenuItem(
    openMenu: MutableState<Boolean>,
    onThemeSelected: () -> Unit,
) {
    DropdownMenuItem(
        text = { MenuText(stringRes = R.string.menu_app_theme) },
        onClick = {
            openMenu.value = false
            onThemeSelected()
        },
        leadingIcon = { MenuIcon(Icons.Outlined.Palette) },
    )
}

@Composable
fun ColorToValueMenuItem(onColorToValueTapped: () -> Unit) {
    DropdownMenuItem(
        text = { MenuText(stringRes = R.string.menu_color_to_value) },
        onClick = onColorToValueTapped,
        leadingIcon = { MenuIcon(Icons.Outlined.Colorize) },
    )
}

@AppComponentPreviews
@Composable
private fun MenuItemsPreview() {
    val showMenu = remember { mutableStateOf(false) }
    ResistorCalculatorTheme {
        Column {
            AboutAppMenuItem {}
            ClearSelectionsMenuItem {}
            ColorToValueMenuItem {}
            FeedbackMenuItem("app", showMenu)
            ShareImageMenuItem("applicationId", showMenu, Picture())
            ShareTextMenuItem("text", showMenu)
        }
    }
}
