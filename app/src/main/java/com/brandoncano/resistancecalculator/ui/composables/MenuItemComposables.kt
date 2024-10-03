package com.brandoncano.resistancecalculator.ui.composables

import android.graphics.Picture
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Colorize
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import com.brandoncano.resistancecalculator.R
import com.brandoncano.resistancecalculator.navigation.Screen
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

@Deprecated("Use other function definition for new compose architecture.")
@Composable
fun AboutAppMenuItem(navController: NavController, showMenu: MutableState<Boolean>) {
    DropdownMenuItem(
        text = { MenuText(stringRes = R.string.menu_about) },
        onClick = {
            showMenu.value = false
            navController.navigate(Screen.About.route)
        },
        leadingIcon = { MenuIcon(Icons.Outlined.Info) },
    )
}

@Composable
fun AboutAppMenuItem(onAboutTapped: () -> Unit) {
    DropdownMenuItem(
        text = { MenuText(stringRes = R.string.menu_about) },
        onClick = onAboutTapped,
        leadingIcon = { MenuIcon(Icons.Outlined.Info) },
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

@Composable
fun ValueToColorMenuItem(onValueToColorTapped: () -> Unit) {
    DropdownMenuItem(
        text = { MenuText(stringRes = R.string.menu_value_to_color) },
        onClick = onValueToColorTapped,
        leadingIcon = { MenuIcon(Icons.Outlined.Search) },
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
            ValueToColorMenuItem {}
        }
    }
}
