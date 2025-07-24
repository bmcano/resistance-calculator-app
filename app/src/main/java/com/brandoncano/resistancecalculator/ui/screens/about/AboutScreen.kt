package com.brandoncano.resistancecalculator.ui.screens.about

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.Policy
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.brandoncano.resistancecalculator.R
import com.brandoncano.resistancecalculator.data.ArrowCardButtonPO
import com.brandoncano.resistancecalculator.ui.composables.ArrowCardButtonContent
import com.brandoncano.resistancecalculator.ui.composables.BottomScreenSpacer
import com.brandoncano.resistancecalculator.ui.composables.elevatedCardColor
import com.brandoncano.resistancecalculator.ui.composables.m3.M3ElevatedCard
import com.brandoncano.resistancecalculator.ui.composables.m3.M3TopAppBar
import com.brandoncano.resistancecalculator.ui.screens.home.InformationCardButtons
import com.brandoncano.resistancecalculator.ui.screens.home.OurAppsButtons
import com.brandoncano.resistancecalculator.ui.theme.ResistorCalculatorTheme
import com.brandoncano.sharedcomponents.composables.AppScreenPreviews


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
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            M3TopAppBar(
                titleText = stringResource(R.string.about_title),
                navigationIcon = Icons.AutoMirrored.Filled.ArrowBack,
                onNavigateBack = onNavigateBack,
                scrollBehavior = scrollBehavior,
            )
        },
        contentWindowInsets = WindowInsets.safeDrawing,
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
    val sidePadding = dimensionResource(R.dimen.app_side_padding)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(paddingValues)
            .padding(horizontal = sidePadding),
        horizontalAlignment = Alignment.Start,
    ) {
        Spacer(modifier = Modifier.height(24.dp))
        AppInformationCard(
            version = stringResource(R.string.version),
            lastUpdated = stringResource(R.string.last_updated),
        )
        Spacer(modifier = Modifier.height(12.dp))
        M3ElevatedCard(
            modifier = Modifier.fillMaxWidth(),
        ) {
            ArrowCardButtonContent(
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
            onViewCircuitEquationsTapped = {},
            onRateThisAppTapped = {},
            onViewOurAppsTapped = {},
            onDonateTapped = {},
        )
    }
}
