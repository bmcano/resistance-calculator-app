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
import androidx.compose.ui.unit.dp
import com.brandoncano.resistancecalculator.R
import com.brandoncano.resistancecalculator.constants.Colors
import com.brandoncano.resistancecalculator.data.ESeriesCardContent
import com.brandoncano.resistancecalculator.data.ResistorImageColorPair
import com.brandoncano.resistancecalculator.to.ResistorVtc
import com.brandoncano.resistancecalculator.ui.screens.ctv.ResistanceText
import com.brandoncano.resistancecalculator.ui.screens.ctv.ResistorRow
import com.brandoncano.resistancecalculator.ui.theme.validGreen
import com.brandoncano.resistancecalculator.ui.theme.warningGold
import com.brandoncano.resistancecalculator.util.resistor.bandFiveForDisplay
import com.brandoncano.resistancecalculator.util.resistor.bandFourForDisplay
import com.brandoncano.resistancecalculator.util.resistor.bandOneForDisplay
import com.brandoncano.resistancecalculator.util.resistor.bandSixForDisplay
import com.brandoncano.resistancecalculator.util.resistor.bandThreeForDisplay
import com.brandoncano.resistancecalculator.util.resistor.bandTwoForDisplay
import com.brandoncano.resistancecalculator.util.resistor.deriveResistorColor
import com.brandoncano.sharedcomponents.composables.AppActionCard
import com.brandoncano.sharedcomponents.data.CardAction

@Composable
fun ResistorLayout(resistor: ResistorVtc, isError: Boolean) {
    val resistorColor = resistor.deriveResistorColor()
    val imageColorPairs = remember(resistor) {
        listOf(
            R.drawable.img_resistor_wire to Colors.RESISTOR_WIRE,
            R.drawable.img_resistor_end_left to resistorColor,
            R.drawable.img_resistor_band_96 to resistor.bandOneForDisplay(),
            R.drawable.img_resistor_curve_left to resistorColor,
            R.drawable.img_resistor_band_64 to resistor.bandTwoForDisplay(),
            R.drawable.img_resistor_band_64 to resistorColor,
            R.drawable.img_resistor_band_64 to resistor.bandThreeForDisplay(),
            R.drawable.img_resistor_band_64 to resistorColor,
            R.drawable.img_resistor_band_64 to resistor.bandFourForDisplay(),
            R.drawable.img_resistor_band_64_wide to resistorColor,
            R.drawable.img_resistor_band_64_wide to resistor.bandFiveForDisplay(),
            R.drawable.img_resistor_curve_right to resistorColor,
            R.drawable.img_resistor_band_96 to resistor.bandSixForDisplay(),
            R.drawable.img_resistor_end_right to resistorColor,
            R.drawable.img_resistor_wire to Colors.RESISTOR_WIRE
        ).map { (res, color) -> ResistorImageColorPair(res, color) }
    }
    val text = when {
        resistor.isEmpty() -> stringResource(id = R.string.vtc_default_value)
        isError -> stringResource(id = R.string.error_na)
        else -> resistor.getResistorValue()
    }
    Column(
        modifier = Modifier.padding(start = 32.dp, end = 32.dp),
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
