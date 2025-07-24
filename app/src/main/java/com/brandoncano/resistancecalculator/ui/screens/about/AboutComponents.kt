package com.brandoncano.resistancecalculator.ui.screens.about

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.History
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.brandoncano.resistancecalculator.R
import com.brandoncano.resistancecalculator.ui.composables.elevatedCardColor
import com.brandoncano.resistancecalculator.ui.composables.m3.M3Divider
import com.brandoncano.resistancecalculator.ui.composables.m3.M3ElevatedCard
import com.brandoncano.resistancecalculator.ui.composables.m3.M3ListItem
import com.brandoncano.resistancecalculator.ui.composables.m3.M3OutlinedCard
import com.brandoncano.sharedcomponents.text.onSurfaceVariant

@Composable
fun AppInformationCard(version: String, lastUpdated: String) {
    M3ElevatedCard {
        M3ListItem(
            headlineText = stringResource(R.string.about_created_by),
            supportingText = stringResource(R.string.about_author),
            leadingContent = {
                Image(
                    imageVector = Icons.Outlined.Person,
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurfaceVariant),
                )
            },
            containerColor = elevatedCardColor(),
        )
        M3Divider(insetPadding = 16.dp)
        M3ListItem(
            headlineText = stringResource(R.string.about_app_version),
            supportingText = version,
            leadingContent = {
                Image(
                    imageVector = Icons.Outlined.Info,
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurfaceVariant),
                )
            },
            containerColor = elevatedCardColor(),
        )
        M3Divider(insetPadding = 16.dp)
        M3ListItem(
            headlineText = stringResource(R.string.about_last_updated_on),
            supportingText = lastUpdated,
            leadingContent = {
                Image(
                    imageVector = Icons.Outlined.History,
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurfaceVariant),
                )
            },
            containerColor = elevatedCardColor(),
        )
    }
}

@Composable
fun AboutOverviewCard() {
    Column {
        Text(
            text = stringResource(id = R.string.about_overview_title),
            modifier = Modifier.align(Alignment.Start),
            style = MaterialTheme.typography.titleMedium,
        )
        Spacer(modifier = Modifier.height(12.dp))
        M3OutlinedCard(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(
                text = stringResource(id = R.string.about_overview_body_01),
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 12.dp),
                style = MaterialTheme.typography.bodyMedium.onSurfaceVariant(),
            )
            Text(
                text = stringResource(id = R.string.about_overview_body_02),
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
                style = MaterialTheme.typography.bodyMedium.onSurfaceVariant(),
            )
        }
    }
}
