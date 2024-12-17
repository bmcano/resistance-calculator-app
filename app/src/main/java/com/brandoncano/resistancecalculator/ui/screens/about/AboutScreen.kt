package com.brandoncano.resistancecalculator.ui.screens.about

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.outlined.Colorize
import androidx.compose.material.icons.outlined.Memory
import androidx.compose.material.icons.outlined.Policy
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.brandoncano.resistancecalculator.R
import com.brandoncano.resistancecalculator.ui.screens.home.OurAppsButtons
import com.brandoncano.resistancecalculator.ui.theme.ResistorCalculatorTheme
import com.brandoncano.sharedcomponents.composables.AppArrowCardButton
import com.brandoncano.sharedcomponents.composables.AppCard
import com.brandoncano.sharedcomponents.composables.AppScreenPreviews
import com.brandoncano.sharedcomponents.composables.AppTopAppBar
import com.brandoncano.sharedcomponents.data.ArrowCardButtonContents
import com.brandoncano.sharedcomponents.screen.AppInfoCard
import com.brandoncano.sharedcomponents.screen.AuthorCard
import com.brandoncano.sharedcomponents.text.onSurfaceVariant
import com.brandoncano.sharedcomponents.text.textStyleBody
import com.brandoncano.sharedcomponents.text.textStyleHeadline

@Composable
fun AboutScreen(
    onNavigateBack: () -> Unit,
    onViewPrivacyPolicyTapped: () -> Unit,
    onViewColorCodeIecTapped: () -> Unit,
    onViewPreferredValuesIecTapped: () -> Unit,
    onViewSmdCodeIecTapped: () -> Unit,
    onRateThisAppTapped: () -> Unit,
    onViewOurAppsTapped: () -> Unit,
) {
    Scaffold(
        topBar = {
            AppTopAppBar(
                titleText = stringResource(R.string.about_title),
                navigationIcon = Icons.Filled.Close,
                onNavigateBack = onNavigateBack,
            )
        }
    ) { paddingValues ->
        AboutScreenContent(
            paddingValues = paddingValues,
            onViewPrivacyPolicyTapped = onViewPrivacyPolicyTapped,
            onViewColorCodeIecTapped = onViewColorCodeIecTapped,
            onViewPreferredValuesIecTapped = onViewPreferredValuesIecTapped,
            onViewSmdCodeIecTapped = onViewSmdCodeIecTapped,
            onRateThisAppTapped = onRateThisAppTapped,
            onViewOurAppsTapped = onViewOurAppsTapped,
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
    onRateThisAppTapped: () -> Unit,
    onViewOurAppsTapped: () -> Unit,
) {
    val sidePadding = dimensionResource(R.dimen.app_side_padding)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(horizontal = sidePadding)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.Start,
    ) {
        Spacer(modifier = Modifier.height(12.dp))
        AuthorCard()
        Spacer(modifier = Modifier.height(16.dp))
        AppInfoCard(R.string.version, R.string.last_updated)
        Spacer(modifier = Modifier.height(16.dp))
        AppArrowCardButton(
            ArrowCardButtonContents(
                imageVector = Icons.Outlined.Policy,
                text = stringResource(id = R.string.about_view_privacy_policy),
                onClick = onViewPrivacyPolicyTapped,
            )
        )
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = stringResource(id = R.string.about_description),
            modifier = Modifier.padding(bottom = 12.dp),
            style = textStyleHeadline(),
        )
        AppCard {
            Text(
                text = stringResource(id = R.string.about_description_01),
                modifier = Modifier.padding(16.dp),
                style = textStyleBody().onSurfaceVariant(),
            )
            Text(
                text = stringResource(id = R.string.about_description_02),
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
                style = textStyleBody().onSurfaceVariant(),
            )
        }
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = stringResource(id = R.string.about_iec_header_text),
            modifier = Modifier
                .padding(bottom = 12.dp)
                .align(Alignment.Start),
            style = textStyleHeadline(),
        )
        AppArrowCardButton(
            ArrowCardButtonContents(
                imageVector = Icons.Outlined.Colorize,
                text = stringResource(id = R.string.about_standard_iec_button),
                onClick = onViewColorCodeIecTapped,
            ),
            ArrowCardButtonContents(
                imageVector = Icons.Outlined.Search,
                text = stringResource(id = R.string.about_preferred_values_iec_button),
                onClick = onViewPreferredValuesIecTapped,
            ),
            ArrowCardButtonContents(
                imageVector = Icons.Outlined.Memory,
                text = stringResource(id = R.string.about_smd_iec_button),
                onClick = onViewSmdCodeIecTapped,
            ),
        )
        Spacer(modifier = Modifier.height(32.dp))
        OurAppsButtons(
            onRateThisAppTapped = onRateThisAppTapped,
            onViewOurAppsTapped = onViewOurAppsTapped,
        )
        Spacer(modifier = Modifier.height(48.dp))
    }
}

@AppScreenPreviews
@Composable
private fun AboutPreview() {
    ResistorCalculatorTheme {
        AboutScreen(
            onNavigateBack = {},
            onViewPrivacyPolicyTapped = {},
            onViewColorCodeIecTapped = {},
            onViewPreferredValuesIecTapped = {},
            onViewSmdCodeIecTapped = {},
            onRateThisAppTapped = {},
            onViewOurAppsTapped = {},
        )
    }
}
