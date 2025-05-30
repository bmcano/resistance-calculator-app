package com.brandoncano.resistancecalculator.ui.composables

import androidx.activity.compose.LocalActivity
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Image
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.platform.LocalContext
import com.brandoncano.resistancecalculator.R
import com.brandoncano.resistancecalculator.util.share.ShareResistor
import com.brandoncano.sharedcomponents.composables.MenuIcon
import com.brandoncano.sharedcomponents.composables.MenuText

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
