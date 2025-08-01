package com.brandoncano.inductancecalculator.ui.composables

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.brandoncano.inductancecalculator.R

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
fun ShareTextMenuItem(onClick: () -> Unit) {
    DropdownMenuItem(
        text = { MenuText(stringRes = R.string.menu_share_text) },
        onClick = onClick,
        leadingIcon = { MenuIcon(Icons.Outlined.Share) },
    )
}

@Composable
private fun MenuText(@StringRes stringRes: Int) {
    Text(
        text = stringResource(id = stringRes),
        style = MaterialTheme.typography.bodyLarge,
    )
}

@Composable
private fun MenuIcon(imageVector: ImageVector) {
    Image(
        modifier = Modifier.size(24.dp),
        imageVector = imageVector,
        contentDescription = null,
        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurfaceVariant),
    )
}
