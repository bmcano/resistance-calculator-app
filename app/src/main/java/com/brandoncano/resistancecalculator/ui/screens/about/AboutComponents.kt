package com.brandoncano.resistancecalculator.ui.screens.about

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Colorize
import androidx.compose.material.icons.outlined.Functions
import androidx.compose.material.icons.outlined.Memory
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.brandoncano.resistancecalculator.R
import com.brandoncano.sharedcomponents.composables.AppArrowCardButton
import com.brandoncano.sharedcomponents.composables.AppCard
import com.brandoncano.sharedcomponents.data.ArrowCardButtonContents
import com.brandoncano.sharedcomponents.text.onSurfaceVariant
import com.brandoncano.sharedcomponents.text.textStyleHeadline
import com.brandoncano.sharedcomponents.text.textStyleSubhead

@Composable
fun AboutOverviewCard() {
    AppCard(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Text(
            text = stringResource(id = R.string.about_overview_title),
            modifier = Modifier.padding(16.dp),
            style = textStyleHeadline(),
        )
        Text(
            text = stringResource(id = R.string.about_overview_body_01),
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
            style = textStyleSubhead().onSurfaceVariant(),
        )
        Text(
            text = stringResource(id = R.string.about_overview_body_02),
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
            style = textStyleSubhead().onSurfaceVariant(),
        )
    }
}

@Composable
fun InformationCardButtons(
    onViewColorCodeIecTapped: () -> Unit,
    onViewPreferredValuesIecTapped: () -> Unit,
    onViewSmdCodeIecTapped: () -> Unit,
    onViewCircuitEquationsTapped: () -> Unit,
) {
    Column {
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
            ArrowCardButtonContents(
                imageVector = Icons.Outlined.Functions,
                text = stringResource(id = R.string.about_circuit_equations_button),
                onClick = onViewCircuitEquationsTapped,
            ),
        )
    }
}
