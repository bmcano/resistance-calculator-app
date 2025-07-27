package com.brandoncano.resistancecalculator.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.rounded.VolunteerActivism
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.brandoncano.resistancecalculator.R
import com.brandoncano.resistancecalculator.ui.composables.m3.BottomScreenSpacer
import com.brandoncano.resistancecalculator.ui.composables.m3.M3FilledButton
import com.brandoncano.resistancecalculator.ui.composables.m3.M3Scaffold
import com.brandoncano.resistancecalculator.ui.composables.m3.M3ScreenColumn
import com.brandoncano.resistancecalculator.ui.composables.m3.M3TopAppBar
import com.brandoncano.resistancecalculator.ui.composables.m3.ScreenPreviews
import com.brandoncano.resistancecalculator.ui.theme.gray

@OptIn(ExperimentalMaterial3Api::class) // For TopAppBar
@Composable
fun DonateScreen(
    onNavigateBack: () -> Unit,
    onContinueToPaymentTapped: (Int) -> Unit,
    snackbarHostState: SnackbarHostState,
) {
    var selectedAmount by remember { mutableStateOf<Int?>(null) }
    M3Scaffold(
        topBar = {
            M3TopAppBar(
                titleText = stringResource(R.string.donate_title),
                navigationIcon = Icons.Filled.Close,
                onNavigateBack = onNavigateBack,
                scrollBehavior = it,
            )
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
    ) { paddingValues ->
        DonateScreenContent(
            paddingValues = paddingValues,
            selectedAmount = selectedAmount,
            onAmountSelected = { selectedAmount = it },
            onContinueToPaymentTapped = { selectedAmount?.let(onContinueToPaymentTapped) }
        )
    }
}

@Composable
private fun DonateScreenContent(
    paddingValues: PaddingValues,
    selectedAmount: Int?,
    onAmountSelected: (Int) -> Unit,
    onContinueToPaymentTapped: () -> Unit,
) {
    M3ScreenColumn(
        paddingValues = paddingValues,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(32.dp))
        DonationHeroImage()
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = stringResource(R.string.donate_description_body),
            modifier = Modifier.align(Alignment.Start),
            style = MaterialTheme.typography.bodyLarge.gray(),
        )
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = stringResource(R.string.donate_how_much_header),
            modifier = Modifier.align(Alignment.Start),
            style = MaterialTheme.typography.titleMedium,
        )
        Spacer(modifier = Modifier.height(12.dp))
        DonationChipGroup(
            selectedAmount = selectedAmount,
            onAmountSelected = onAmountSelected,
        )
        M3FilledButton(
            buttonLabel = stringResource(R.string.donate_payment_cta),
            onClick = onContinueToPaymentTapped,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 32.dp),
            enabled = selectedAmount != null,
            useSquareShape = true
        )
        Text(
            text = stringResource(R.string.donate_caption),
            modifier = Modifier.align(Alignment.Start),
            style = MaterialTheme.typography.bodySmall.gray(),
        )
        BottomScreenSpacer()
    }
}

@Composable
fun DonationHeroImage() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(128.dp)
            .background(
                brush = Brush.radialGradient(
                    colors = listOf(Color(0xFFFFC1C1), Color(0xFFFFCDD2), Color(0xFFE57373))
                ),
                shape = CircleShape,
            ),
    ) {
        Icon(
            imageVector = Icons.Rounded.VolunteerActivism,
            contentDescription = stringResource(R.string.donate_title),
            tint = Color(0xFFFEFEFA),
            modifier = Modifier.size(80.dp),
        )
    }
}

@Composable
fun DonationChipGroup(
    selectedAmount: Int?,
    onAmountSelected: (Int) -> Unit,
) {
    val donationAmounts = listOf(1, 2, 3, 5, 10)
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            donationAmounts.forEach { amount ->
                DonationChip(selectedAmount, onAmountSelected, amount)
            }
        }
    }
}

@Composable
private fun DonationChip(
    selectedAmount: Int?,
    onAmountSelected: (Int) -> Unit,
    amount: Int,
) {
    AssistChip(
        onClick = { onAmountSelected(amount) },
        label = {
            Text(
                text = "$amount USD",
                modifier = Modifier.padding(horizontal = 4.dp),
            )
        },
        modifier = Modifier.padding(horizontal = 0.dp),
        shape = MaterialTheme.shapes.extraLarge,
        colors = AssistChipDefaults.assistChipColors(
            labelColor = if (selectedAmount == amount) {
                MaterialTheme.colorScheme.surface
            } else {
                MaterialTheme.colorScheme.onSurface
            },
            containerColor = if (selectedAmount == amount) {
                MaterialTheme.colorScheme.primary
            } else {
                MaterialTheme.colorScheme.surface
            },
        )
    )
}

@ScreenPreviews
@Composable
private fun DonateScreenPreview() {
    DonateScreen(
        onNavigateBack = {},
        onContinueToPaymentTapped = {},
        snackbarHostState = SnackbarHostState(),
    )
}
