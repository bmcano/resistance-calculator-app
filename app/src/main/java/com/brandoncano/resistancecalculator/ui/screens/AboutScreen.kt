package com.brandoncano.resistancecalculator.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.History
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Policy
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.brandoncano.library.data.ArrowCardButtonPO
import com.brandoncano.library.m3.BottomScreenSpacer
import com.brandoncano.library.m3.LongScreenPreview
import com.brandoncano.library.m3.M3ArrowButtonCardContent
import com.brandoncano.library.m3.M3Divider
import com.brandoncano.library.m3.M3ElevatedCard
import com.brandoncano.library.m3.M3ListItem
import com.brandoncano.library.m3.M3Scaffold
import com.brandoncano.library.m3.M3ScreenColumn
import com.brandoncano.library.m3.M3TopAppBar
import com.brandoncano.library.m3.ScreenPreviews
import com.brandoncano.library.m3.elevatedCardColor
import com.brandoncano.library.theme.gray
import com.brandoncano.resistancecalculator.R
import com.brandoncano.resistancecalculator.ui.theme.ResistorCalculatorTheme

@OptIn(ExperimentalMaterial3Api::class) // For TopAppBar
@Composable
fun AboutScreen(
    onNavigateBack: () -> Unit,
    onViewPrivacyPolicyTapped: () -> Unit,
    onViewColorCodeIecTapped: () -> Unit,
    onViewPreferredValuesIecTapped: () -> Unit,
    onViewSmdCodeIecTapped: () -> Unit,
    onViewCircuitEquationsTapped: () -> Unit,
    onRateThisAppTapped: () -> Unit,
    onViewOurAppsTapped: () -> Unit,
    onDonateTapped: () -> Unit,
) {
    M3Scaffold(
        topBar = {
            M3TopAppBar(
                titleText = stringResource(R.string.about_title),
                navigationIcon = Icons.AutoMirrored.Filled.ArrowBack,
                onNavigateBack = onNavigateBack,
                scrollBehavior = it,
            )
        },
    ) { paddingValues ->
        AboutScreenContent(
            paddingValues = paddingValues,
            onViewPrivacyPolicyTapped = onViewPrivacyPolicyTapped,
            onViewColorCodeIecTapped = onViewColorCodeIecTapped,
            onViewPreferredValuesIecTapped = onViewPreferredValuesIecTapped,
            onViewSmdCodeIecTapped = onViewSmdCodeIecTapped,
            onViewCircuitEquationsTapped = onViewCircuitEquationsTapped,
            onRateThisAppTapped = onRateThisAppTapped,
            onViewOurAppsTapped = onViewOurAppsTapped,
            onDonateTapped = onDonateTapped,
        )
    }
}

@Composable
private fun AboutScreenContent(
    paddingValues: PaddingValues,
    onViewPrivacyPolicyTapped: () -> Unit,
    onViewColorCodeIecTapped: () -> Unit,
    onViewPreferredValuesIecTapped: () -> Unit,
    onViewSmdCodeIecTapped: () -> Unit,
    onViewCircuitEquationsTapped: () -> Unit,
    onRateThisAppTapped: () -> Unit,
    onViewOurAppsTapped: () -> Unit,
    onDonateTapped: () -> Unit,
) {
    M3ScreenColumn (
        paddingValues = paddingValues,
    ) {
        Spacer(modifier = Modifier.height(24.dp))
        AppInformationCard(
            version = stringResource(R.string.version),
            lastUpdated = stringResource(R.string.last_updated),
        )
        Spacer(modifier = Modifier.height(12.dp))
        M3ElevatedCard(
            modifier = Modifier.fillMaxWidth(),
            defaultElevation = 0.dp,
        ) {
            M3ArrowButtonCardContent(
                color = elevatedCardColor(),
                ArrowCardButtonPO(
                    text = stringResource(id = R.string.about_view_privacy_policy),
                    imageVector = Icons.Outlined.Policy,
                    onClick = onViewPrivacyPolicyTapped,
                )
            )
        }
        Spacer(modifier = Modifier.height(32.dp))
        AboutOverviewCard()
        Spacer(modifier = Modifier.height(32.dp))
        InformationCardButtons(
            onViewColorCodeIecTapped = onViewColorCodeIecTapped,
            onViewPreferredValuesIecTapped = onViewPreferredValuesIecTapped,
            onViewSmdCodeIecTapped = onViewSmdCodeIecTapped,
            onViewCircuitEquationsTapped = onViewCircuitEquationsTapped,
        )
        Spacer(modifier = Modifier.height(32.dp))
        OurAppsButtons(
            onRateThisAppTapped = onRateThisAppTapped,
            onViewOurAppsTapped = onViewOurAppsTapped,
            onDonateTapped = onDonateTapped,
        )
        BottomScreenSpacer()
    }
}

@Composable
private fun AppInformationCard(version: String, lastUpdated: String) {
    M3ElevatedCard(
        defaultElevation = 0.dp,
    ) {
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
private fun AboutOverviewCard() {
    Column {
        Text(
            text = stringResource(id = R.string.about_overview_title),
            modifier = Modifier.align(Alignment.Start),
            style = MaterialTheme.typography.titleMedium,
        )
        Spacer(modifier = Modifier.height(12.dp))
        M3ElevatedCard(
            modifier = Modifier.fillMaxWidth(),
            defaultElevation = 0.dp,
        ) {
            Text(
                text = stringResource(id = R.string.about_overview_body_01),
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 12.dp),
                style = MaterialTheme.typography.bodyMedium.gray(),
            )
            Text(
                text = stringResource(id = R.string.about_overview_body_02),
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
                style = MaterialTheme.typography.bodyMedium.gray(),
            )
        }
    }
}

@ScreenPreviews
@LongScreenPreview
@Composable
private fun AboutScreenPreview() {
    ResistorCalculatorTheme {
        AboutScreen(
            onNavigateBack = {},
            onViewPrivacyPolicyTapped = {},
            onViewColorCodeIecTapped = {},
            onViewPreferredValuesIecTapped = {},
            onViewSmdCodeIecTapped = {},
            onViewCircuitEquationsTapped = {},
            onRateThisAppTapped = {},
            onViewOurAppsTapped = {},
            onDonateTapped = {},
        )
    }
}
