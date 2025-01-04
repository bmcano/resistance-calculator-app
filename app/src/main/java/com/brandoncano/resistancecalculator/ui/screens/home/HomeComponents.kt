package com.brandoncano.resistancecalculator.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Apps
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Grade
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.brandoncano.resistancecalculator.R
import com.brandoncano.resistancecalculator.ui.theme.LocalIsDarkTheme
import com.brandoncano.resistancecalculator.ui.theme.ResistorCalculatorTheme
import com.brandoncano.sharedcomponents.composables.AppArrowCardButton
import com.brandoncano.sharedcomponents.composables.AppComponentPreviews
import com.brandoncano.sharedcomponents.data.ArrowCardButtonContents
import com.brandoncano.sharedcomponents.text.textStyleHeadline

@Composable
fun AppIcon() {
    val backgroundColor = if (LocalIsDarkTheme.current) {
        MaterialTheme.colorScheme.surfaceBright
    } else {
        MaterialTheme.colorScheme.primary
    }
    Card(
        modifier = Modifier
            .padding(top = 16.dp)
            .size(128.dp),
        shape = MaterialTheme.shapes.large,
        elevation = CardDefaults.cardElevation(16.dp),
        colors = CardDefaults.cardColors(containerColor = backgroundColor)
    ) {
        Box(contentAlignment = Alignment.Center) {
            Image(
                painter = painterResource(id = R.mipmap.ic_launcher_foreground),
                contentDescription = stringResource(id = R.string.content_description_app_icon),
                modifier = Modifier.size(128.dp)
            )
        }
    }
}

@Composable
fun OurAppsButtons(
    onRateThisAppTapped: () -> Unit,
    onViewOurAppsTapped: () -> Unit,
    onDonateTapped: () -> Unit,
) {
    Column {
        Text(
            text = stringResource(id = R.string.home_our_apps_header_text),
            modifier = Modifier.align(Alignment.Start),
            style = textStyleHeadline(),
        )
        Spacer(modifier = Modifier.height(12.dp))
        AppArrowCardButton(
            ArrowCardButtonContents(
                imageVector = Icons.Outlined.Grade,
                text = stringResource(id = R.string.home_button_rate_us),
                onClick = onRateThisAppTapped,
            ),
            ArrowCardButtonContents(
                imageVector = Icons.Outlined.Apps,
                text = stringResource(id = R.string.home_button_view_apps),
                onClick = onViewOurAppsTapped,
            ),
            ArrowCardButtonContents(
                imageVector = Icons.Outlined.FavoriteBorder,
                text = "Donate",
                onClick = onDonateTapped,
            ),
        )
    }
}

@AppComponentPreviews
@Composable
private fun OurAppsButtonsPreview() {
    ResistorCalculatorTheme {
        Surface {
            OurAppsButtons(
                onRateThisAppTapped = {},
                onViewOurAppsTapped = {},
                onDonateTapped = {},
            )
        }
    }
}
