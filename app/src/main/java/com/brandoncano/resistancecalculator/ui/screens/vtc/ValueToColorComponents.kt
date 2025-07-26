package com.brandoncano.resistancecalculator.ui.screens.vtc

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.rounded.WarningAmber
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.brandoncano.resistancecalculator.R
import com.brandoncano.resistancecalculator.data.ESeriesCardContent
import com.brandoncano.resistancecalculator.to.ResistorVtc
import com.brandoncano.resistancecalculator.ui.composables.m3.M3CardContent
import com.brandoncano.resistancecalculator.ui.composables.m3.M3ElevatedCard
import com.brandoncano.resistancecalculator.ui.composables.m3.M3OutlinedCard
import com.brandoncano.resistancecalculator.ui.screens.ctv.ResistanceText
import com.brandoncano.resistancecalculator.ui.screens.ctv.ResistorRow
import com.brandoncano.resistancecalculator.ui.theme.validGreen
import com.brandoncano.resistancecalculator.ui.theme.warningGold
import com.brandoncano.resistancecalculator.util.resistor.ResistorImageBuilder

@Composable
fun ResistorLayout(resistor: ResistorVtc, isError: Boolean, verticalPadding: Dp = 0.dp) {
    val imageColorPairs = remember(resistor) {
        ResistorImageBuilder.execute(resistor)
    }
    val text = when {
        resistor.isEmpty() -> stringResource(id = R.string.vtc_default_value)
        isError -> stringResource(id = R.string.error_na)
        else -> resistor.getResistorValue()
    }
    Column(
        modifier = Modifier.padding(horizontal = 32.dp, vertical = verticalPadding),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        ResistorRow(imageColorPairs)
        ResistanceText(text)
    }
}

@Composable
fun ESeriesCard(
    eSeriesCardContent: ESeriesCardContent,
    onLearnMoreTapped: () -> Unit,
    onUseValueTapped: () -> Unit,
) {
    when (eSeriesCardContent) {
        is ESeriesCardContent.ValidResistance -> {
            M3OutlinedCard {
                M3CardContent(
                    headline = stringResource(R.string.vtc_valid_card_title),
                    subhead = stringResource(R.string.vtc_info_card_subhead),
                    bodyText = stringResource(R.string.vtc_valid_card_body, eSeriesCardContent.value),
                    icon = Icons.Outlined.CheckCircle,
                    iconTint = validGreen,
                    primaryButtonText = stringResource(R.string.vtc_info_card_action),
                    onPrimaryClick = onLearnMoreTapped,
                )
            }
        }
        is ESeriesCardContent.InvalidTolerance -> {
            M3ElevatedCard(defaultElevation = 1.dp) {
                M3CardContent(
                    headline = stringResource(R.string.vtc_invalid_tolerance_label),
                    subhead = stringResource(R.string.vtc_info_card_subhead),
                    bodyText = stringResource(R.string.vtc_invalid_tolerance_body, eSeriesCardContent.value),
                    icon = Icons.Rounded.WarningAmber,
                    iconTint = warningGold,
                    primaryButtonText = stringResource(R.string.vtc_info_card_action),
                    onPrimaryClick = onLearnMoreTapped,
                )
            }
        }
        is ESeriesCardContent.InvalidResistance -> {
            M3ElevatedCard(defaultElevation = 1.dp) {
                M3CardContent(
                    headline = stringResource(R.string.vtc_invalid_card_title),
                    subhead = stringResource(R.string.vtc_info_card_subhead),
                    icon = Icons.Rounded.WarningAmber,
                    iconTint = warningGold,
                    bodyText = stringResource(R.string.vtc_invalid_card_body, eSeriesCardContent.value),
                    primaryButtonText = stringResource(R.string.vtc_invalid_card_action),
                    onPrimaryClick = onUseValueTapped,
                    secondaryButtonText = stringResource(R.string.vtc_info_card_action),
                    onSecondaryClick = onLearnMoreTapped,
                )
            }
        }
        ESeriesCardContent.DefaultContent -> {
            M3OutlinedCard {
                M3CardContent(
                    headline = stringResource(R.string.vtc_info_card_title),
                    subhead = stringResource(R.string.vtc_info_card_subhead),
                    bodyText = stringResource(R.string.vtc_info_card_body),
                    primaryButtonText = stringResource(R.string.vtc_info_card_action),
                    onPrimaryClick = onLearnMoreTapped,
                    // secondaryButtonText = "View PDF",
                    // onSecondaryClick = {},
                )
            }
        }
    }
}
