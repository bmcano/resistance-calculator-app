package com.brandoncano.resistancecalculator.ui.screens.donate

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.rounded.VolunteerActivism
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import com.brandoncano.resistancecalculator.R
import com.brandoncano.resistancecalculator.ui.theme.ResistorCalculatorTheme
import com.brandoncano.sharedcomponents.composables.AppScreenPreviews
import com.brandoncano.sharedcomponents.composables.AppTopAppBar
import com.brandoncano.sharedcomponents.text.onSurfaceVariant
import com.brandoncano.sharedcomponents.text.textStyleBody
import com.brandoncano.sharedcomponents.text.textStyleCallout
import com.brandoncano.sharedcomponents.text.textStyleHeadline
import com.brandoncano.sharedcomponents.text.textStyleSubhead

@Composable
fun DonateScreen(
    onNavigateBack: () -> Unit,
) {
    var selectedAmount by remember { mutableStateOf<Int?>(null) }

    Scaffold(
        topBar = {
            AppTopAppBar(
                titleText = "Support Us",
                navigationIcon = Icons.Filled.Close,
                onNavigateBack = onNavigateBack,
            )
        },
        bottomBar = {
            // TODO - update shared component to allowed disabling
            val sidePadding = dimensionResource(R.dimen.app_side_padding)
            Button(
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = sidePadding)
                    .padding(vertical = 24.dp)
                    .defaultMinSize(minHeight = 56.dp),
                enabled = selectedAmount != null,
                shape = MaterialTheme.shapes.medium,
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 2.dp
                ),
            ) {
                Text(
                    text = "Continue to payment",
                    style = textStyleCallout(),
                )
            }
        }
    ) { paddingValues ->
        DonateScreenContent(
            paddingValues = paddingValues,
            selectedAmount = selectedAmount,
            onAmountSelected = { amount -> selectedAmount = amount }
        )
    }
}

@Composable
private fun DonateScreenContent(
    paddingValues: PaddingValues,
    selectedAmount: Int?,
    onAmountSelected: (Int) -> Unit
) {
    // could add animation?
    val sidePadding = dimensionResource(R.dimen.app_side_padding)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(paddingValues)
            .padding(horizontal = sidePadding),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(24.dp))
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(128.dp)
                .background(
                    brush = Brush.radialGradient(
                        colors = listOf(Color(0xFFFFC1C1), Color(0xFFFFCDD2), Color(0xFFE57373))
                    ),
                    shape = CircleShape
                ),
        ) {
            Icon(
                imageVector = Icons.Rounded.VolunteerActivism,
                contentDescription = "Support us",
                tint = Color(0xFFFEFEFA),
                modifier = Modifier.size(80.dp)
            )
        }

        Spacer(modifier = Modifier.height(32.dp))
        Text( // placeholder text
            text = "Your donation helps us improve the app and create new features for everyone to enjoy!",
            style = textStyleBody().onSurfaceVariant(),
        )
        Spacer(modifier = Modifier.height(32.dp))
        Text( // placeholder text
            text = "How much would you like to donate?",
            style = textStyleHeadline().onSurfaceVariant(),
        )
        Spacer(modifier = Modifier.height(12.dp))
        DonationChipGroup(
            selectedAmount = selectedAmount,
            onAmountSelected = onAmountSelected
        )
    }
}

@Composable
fun DonationChipGroup(
    selectedAmount: Int?,
    onAmountSelected: (Int) -> Unit
) {
    val donationAmounts = listOf(10, 5, 3, 2, 1)

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            donationAmounts.take(2).forEach { amount ->
                DonationChip(selectedAmount, onAmountSelected, amount)
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            donationAmounts.drop(2).forEach { amount ->
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
                style = textStyleSubhead()
            )
        },
        modifier = Modifier.padding(horizontal = 8.dp),
        shape = MaterialTheme.shapes.extraLarge,
        colors = AssistChipDefaults.assistChipColors(
            containerColor = if (selectedAmount == amount) MaterialTheme.colorScheme.primary
            else MaterialTheme.colorScheme.surface
        )
    )
}

@AppScreenPreviews
@Composable
private fun DonateScreenPreview() {
    ResistorCalculatorTheme {
        DonateScreen(
            onNavigateBack = {},
        )
    }
}