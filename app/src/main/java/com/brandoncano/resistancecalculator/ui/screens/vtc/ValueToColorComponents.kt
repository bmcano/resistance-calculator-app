package com.brandoncano.resistancecalculator.ui.screens.vtc

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.Lightbulb
import androidx.compose.material.icons.rounded.WarningAmber
import androidx.compose.material3.MaterialTheme
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
import com.brandoncano.resistancecalculator.ui.screens.ctv.ResistanceText
import com.brandoncano.resistancecalculator.ui.screens.ctv.ResistorRow
import com.brandoncano.resistancecalculator.ui.theme.validGreen
import com.brandoncano.resistancecalculator.ui.theme.warningGold
import com.brandoncano.resistancecalculator.util.resistor.ResistorImageBuilder
import com.brandoncano.sharedcomponents.composables.AppActionCard
import com.brandoncano.sharedcomponents.data.CardAction

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
            AppActionCard(
                icon = Icons.Outlined.CheckCircle,
                iconTint = validGreen,
                cardTitle = stringResource(R.string.vtc_valid_card_title),
                cardBody = stringResource(R.string.vtc_valid_card_body, eSeriesCardContent.value),
                leftActionButton = CardAction(
                    buttonLabel = stringResource(R.string.vtc_info_card_action),
                    onClick = onLearnMoreTapped
                )
            )
        }
        is ESeriesCardContent.InvalidTolerance -> {
            AppActionCard(
                icon = Icons.Rounded.WarningAmber,
                iconTint = warningGold,
                cardTitle = stringResource(R.string.vtc_invalid_tolerance_label),
                cardBody = stringResource(R.string.vtc_invalid_tolerance_body, eSeriesCardContent.value),
                leftActionButton = CardAction(
                    buttonLabel = stringResource(R.string.vtc_info_card_action),
                    onClick = onLearnMoreTapped
                )
            )
        }
        is ESeriesCardContent.InvalidResistance -> {
            AppActionCard(
                icon = Icons.Rounded.WarningAmber,
                iconTint = warningGold,
                cardTitle = stringResource(R.string.vtc_invalid_card_title),
                cardBody = stringResource(R.string.vtc_invalid_card_body, eSeriesCardContent.value),
                leftActionButton = CardAction(
                    buttonLabel = stringResource(R.string.vtc_info_card_action),
                    onClick = onLearnMoreTapped
                ),
                rightActionButton = CardAction(
                    buttonLabel = stringResource(R.string.vtc_invalid_card_action),
                    onClick = onUseValueTapped
                )
            )
        }
        ESeriesCardContent.DefaultContent -> {
            AppActionCard(
                icon = Icons.Outlined.Lightbulb,
                iconTint = MaterialTheme.colorScheme.primary,
                cardTitle = stringResource(R.string.vtc_info_card_title),
                cardBody = stringResource(R.string.vtc_info_card_body),
                leftActionButton = CardAction(
                    buttonLabel = stringResource(R.string.vtc_info_card_action),
                    onClick = onLearnMoreTapped,
                )
            )
        }
    }
}
