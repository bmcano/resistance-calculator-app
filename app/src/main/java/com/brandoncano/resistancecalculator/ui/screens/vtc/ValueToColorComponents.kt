package com.brandoncano.resistancecalculator.ui.screens.vtc

import android.graphics.Picture
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.rounded.WarningAmber
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.brandoncano.resistancecalculator.R
import com.brandoncano.resistancecalculator.constants.Colors
import com.brandoncano.resistancecalculator.data.ESeriesCardContent
import com.brandoncano.resistancecalculator.to.ResistorVtc
import com.brandoncano.resistancecalculator.ui.screens.ctv.AppActionCard
import com.brandoncano.resistancecalculator.ui.screens.ctv.CardAction
import com.brandoncano.resistancecalculator.ui.screens.ctv.ResistanceText
import com.brandoncano.resistancecalculator.ui.screens.ctv.ResistorImagePair
import com.brandoncano.resistancecalculator.ui.screens.ctv.ResistorRow
import com.brandoncano.resistancecalculator.ui.theme.validGreen
import com.brandoncano.resistancecalculator.ui.theme.warningGold
import com.brandoncano.resistancecalculator.util.Sdk
import com.brandoncano.resistancecalculator.util.resistor.bandFiveForDisplay
import com.brandoncano.resistancecalculator.util.resistor.bandFourForDisplay
import com.brandoncano.resistancecalculator.util.resistor.bandOneForDisplay
import com.brandoncano.resistancecalculator.util.resistor.bandSixForDisplay
import com.brandoncano.resistancecalculator.util.resistor.bandThreeForDisplay
import com.brandoncano.resistancecalculator.util.resistor.bandTwoForDisplay
import com.brandoncano.resistancecalculator.util.resistor.deriveResistorColor
import com.brandoncano.sharedcomponents.composables.DrawContent

@Composable
fun ResistorDisplay(picture: Picture, resistor: ResistorVtc, isError: Boolean) {
    if (Sdk.isAtLeastAndroid7()) {
        DrawContent(picture) {
            ResistorLayout(resistor, isError)
        }
    } else {
        ResistorLayout(resistor, isError)
    }
}

@Composable
fun ResistorLayout(resistor: ResistorVtc, isError: Boolean) {
    Column(
        modifier = Modifier.padding(start = 32.dp, end = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        val resistorColor = resistor.deriveResistorColor()
        ResistorRow(
            ResistorImagePair(R.drawable.img_resistor_wire, Colors.RESISTOR_WIRE),
            ResistorImagePair(R.drawable.img_resistor_end_left, resistorColor),
            ResistorImagePair(R.drawable.img_resistor_band_96, resistor.bandOneForDisplay()),
            ResistorImagePair(R.drawable.img_resistor_curve_left, resistorColor),
            ResistorImagePair(R.drawable.img_resistor_band_64, resistor.bandTwoForDisplay()),
            ResistorImagePair(R.drawable.img_resistor_band_64, resistorColor),
            ResistorImagePair(R.drawable.img_resistor_band_64, resistor.bandThreeForDisplay()),
            ResistorImagePair(R.drawable.img_resistor_band_64, resistorColor),
            ResistorImagePair(R.drawable.img_resistor_band_64, resistor.bandFourForDisplay()),
            ResistorImagePair(R.drawable.img_resistor_band_64_wide, resistorColor),
            ResistorImagePair(R.drawable.img_resistor_band_64_wide, resistor.bandFiveForDisplay()),
            ResistorImagePair(R.drawable.img_resistor_curve_right, resistorColor),
            ResistorImagePair(R.drawable.img_resistor_band_96, resistor.bandSixForDisplay()),
            ResistorImagePair(R.drawable.img_resistor_end_right, resistorColor),
            ResistorImagePair(R.drawable.img_resistor_wire, Colors.RESISTOR_WIRE),
        )
        val text = when {
            resistor.isEmpty() -> stringResource(id = R.string.vtc_default_value)
            isError -> stringResource(id = R.string.error_na)
            else -> resistor.getResistorValue()
        }
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
                cardTitle = stringResource(R.string.vtc_valid_card_label),
                cardBody = stringResource(R.string.vtc_valid_card_body, eSeriesCardContent.value),
                actions = listOf(
                    CardAction(
                        buttonLabel = stringResource(R.string.vtc_valid_card_action),
                        onClick = onLearnMoreTapped
                    )
                )
            )
        }
        is ESeriesCardContent.InvalidTolerance -> {
            AppActionCard(
                icon = Icons.Rounded.WarningAmber,
                iconTint = warningGold,
                cardTitle = stringResource(R.string.vtc_invalid_tolerance_label),
                cardBody = stringResource(R.string.vtc_invalid_tolerance_body, eSeriesCardContent.value),
                actions = listOf(
                    CardAction(
                        buttonLabel = stringResource(R.string.vtc_valid_card_action),
                        onClick = onLearnMoreTapped
                    )
                )
            )
        }
        is ESeriesCardContent.InvalidResistance -> {
            AppActionCard(
                icon = Icons.Rounded.WarningAmber,
                iconTint = warningGold,
                cardTitle = stringResource(R.string.vtc_invalid_card_label),
                cardBody = stringResource(R.string.vtc_invalid_card_body, eSeriesCardContent.value),
                actions = listOf(
                    CardAction(
                        buttonLabel = stringResource(R.string.vtc_valid_card_action),
                        onClick = onLearnMoreTapped
                    ),
                    CardAction(
                        buttonLabel = stringResource(R.string.vtc_invalid_card_action),
                        onClick = onUseValueTapped
                    )
                )
            )
        }
        ESeriesCardContent.NoContent -> { /* left intentionally empty to show no card */ }
    }
}
