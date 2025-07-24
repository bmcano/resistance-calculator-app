package com.brandoncano.resistancecalculator.ui.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Apps
import androidx.compose.material.icons.outlined.Colorize
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Functions
import androidx.compose.material.icons.outlined.Grade
import androidx.compose.material.icons.outlined.Memory
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.brandoncano.resistancecalculator.R
import com.brandoncano.resistancecalculator.data.ArrowCardButtonPO
import com.brandoncano.resistancecalculator.ui.composables.ArrowCardButtonContent
import com.brandoncano.resistancecalculator.ui.composables.m3.M3OutlinedCard
import com.brandoncano.resistancecalculator.ui.composables.outlinedCardColor

@Composable
fun InformationCardButtons(
    onViewColorCodeIecTapped: () -> Unit,
    onViewPreferredValuesIecTapped: () -> Unit,
    onViewSmdCodeIecTapped: () -> Unit,
    onViewCircuitEquationsTapped: () -> Unit,
) {
    Column {
        Text(
            text = stringResource(id = R.string.home_info_header),
            modifier = Modifier
                .padding(bottom = 12.dp)
                .align(Alignment.Start),
            style = MaterialTheme.typography.titleMedium,
        )
        M3OutlinedCard(
            modifier = Modifier.fillMaxWidth(),
        ) {
            ArrowCardButtonContent(
                color = outlinedCardColor(),
                ArrowCardButtonPO(
                    imageVector = Icons.Outlined.Colorize,
                    text = stringResource(id = R.string.home_standard_iec_button),
                    onClick = onViewColorCodeIecTapped,
                ),
                ArrowCardButtonPO(
                    imageVector = Icons.Outlined.Search,
                    text = stringResource(id = R.string.home_preferred_values_iec_button),
                    onClick = onViewPreferredValuesIecTapped,
                ),
                ArrowCardButtonPO(
                    imageVector = Icons.Outlined.Memory,
                    text = stringResource(id = R.string.home_smd_iec_button),
                    onClick = onViewSmdCodeIecTapped,
                ),
                ArrowCardButtonPO(
                    imageVector = Icons.Outlined.Functions,
                    text = stringResource(id = R.string.home_circuit_equations_button),
                    onClick = onViewCircuitEquationsTapped,
                ),
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
            text = stringResource(id = R.string.home_support_us_header),
            modifier = Modifier.align(Alignment.Start),
            style = MaterialTheme.typography.titleMedium,
        )
        Spacer(modifier = Modifier.height(12.dp))
        M3OutlinedCard(
            modifier = Modifier.fillMaxWidth(),
        ) {
            ArrowCardButtonContent(
                color = outlinedCardColor(),
                ArrowCardButtonPO(
                    imageVector = Icons.Outlined.Grade,
                    text = stringResource(id = R.string.home_rate_us),
                    onClick = onRateThisAppTapped,
                ),
                ArrowCardButtonPO(
                    imageVector = Icons.Outlined.Apps,
                    text = stringResource(id = R.string.home_view_our_apps),
                    onClick = onViewOurAppsTapped,
                ),
                ArrowCardButtonPO(
                    imageVector = Icons.Outlined.FavoriteBorder,
                    text = stringResource(R.string.home_donate),
                    onClick = onDonateTapped,
                ),
            )
        }
    }
}
