package com.brandoncano.resistancecalculator.ui.composables

import androidx.activity.compose.LocalActivity
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material.icons.outlined.Feedback
import androidx.compose.material.icons.outlined.Image
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Palette
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.brandoncano.resistancecalculator.R
import com.brandoncano.resistancecalculator.util.share.ShareResistor
import com.brandoncano.sharedcomponents.composables.MenuIcon
import com.brandoncano.sharedcomponents.composables.MenuText

@Composable
fun MenuIconButton(onClick: () -> Unit) {
    IconButton(onClick = onClick) {
        Icon(
            imageVector = Icons.Filled.MoreVert,
            contentDescription = stringResource(R.string.menu_icon_cd),
        )
    }
}

@Composable
fun AboutAppMenuItem(onClick: () -> Unit) {
    DropdownMenuItem(
        text = { MenuText(stringRes = R.string.menu_about) },
        onClick = onClick,
        leadingIcon = { MenuIcon(Icons.Outlined.Info) },
    )
}

@Composable
fun AppThemeMenuItem(onClick: () -> Unit) {
    DropdownMenuItem(
        text = { MenuText(stringRes = R.string.menu_app_theme) },
        onClick = onClick,
        leadingIcon = { MenuIcon(Icons.Outlined.Palette) },
    )
}

@Composable
fun ClearSelectionsMenuItem(onClick: (() -> Unit)) {
    DropdownMenuItem(
        text = { MenuText(stringRes = R.string.menu_clear_selections) },
        onClick = onClick,
        leadingIcon = { MenuIcon(Icons.Outlined.Cancel) },
    )
}

@Composable
fun FeedbackMenuItem(onClick: () -> Unit) {
    DropdownMenuItem(
        text = { MenuText(stringRes = R.string.menu_feedback) },
        onClick = onClick,
        leadingIcon = { MenuIcon(Icons.Outlined.Feedback) },
    )
}

@Composable
fun ShareImageMenuItem(onClick: () -> Unit) {
    DropdownMenuItem(
        text = { MenuText(stringRes = R.string.menu_share_image) },
        onClick = onClick,
        leadingIcon = { MenuIcon(Icons.Outlined.Image) }
    )
}

@Composable
fun ShareImageMenuItem(
    applicationId: String,
    showMenu: MutableState<Boolean>,
    content: @Composable () -> Unit,
) {
    // In case this returns null, we can simply not show the option
    val activity = LocalActivity.current ?: return
    val context = LocalContext.current
    DropdownMenuItem(
        text = { MenuText(stringRes = R.string.menu_share_image) },
        leadingIcon = { MenuIcon(Icons.Outlined.Image) },
        onClick = {
            showMenu.value = false
            ShareResistor.execute(
                activity = activity,
                context = context,
                applicationId = applicationId,
                content = content,
            )
        }
    )
}

@Composable
fun ShareTextMenuItem(onClick: () -> Unit) {
    DropdownMenuItem(
        text = { MenuText(stringRes = R.string.menu_share_text) },
        onClick = onClick,
        leadingIcon = { MenuIcon(Icons.Outlined.Share) },
    )
}
