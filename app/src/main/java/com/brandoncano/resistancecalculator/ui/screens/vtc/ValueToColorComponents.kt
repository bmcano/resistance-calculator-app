package com.brandoncano.resistancecalculator.ui.screens.vtc

import android.graphics.Picture
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.rounded.WarningAmber
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.brandoncano.resistancecalculator.R
import com.brandoncano.resistancecalculator.constants.Colors
import com.brandoncano.resistancecalculator.data.ESeriesCardContent
import com.brandoncano.resistancecalculator.model.vtc.ResistorVtc
import com.brandoncano.resistancecalculator.ui.screens.ctv.ResistanceText
import com.brandoncano.resistancecalculator.ui.screens.ctv.ResistorImagePair
import com.brandoncano.resistancecalculator.ui.screens.ctv.ResistorRow
import com.brandoncano.resistancecalculator.ui.theme.ResistorCalculatorTheme
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
import com.brandoncano.sharedcomponents.composables.AppCard
import com.brandoncano.sharedcomponents.composables.AppComponentPreviews
import com.brandoncano.sharedcomponents.composables.DrawContent
import com.brandoncano.sharedcomponents.text.onSurfaceVariant
import com.brandoncano.sharedcomponents.text.textStyleBody
import com.brandoncano.sharedcomponents.text.textStyleCallout

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
        modifier = Modifier.padding(top = 16.dp, start = 32.dp, end = 32.dp),
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
            resistor.isEmpty() -> stringResource(id = R.string.default_vtc_value)
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
            ESeriesCardContent(
                isValueValid = true,
                cardLabel = stringResource(R.string.vtc_valid_card_label),
                cardBody = stringResource(R.string.vtc_valid_card_body, eSeriesCardContent.value),
                onLearnMoreTapped = onLearnMoreTapped,
            )
        }
        is ESeriesCardContent.InvalidTolerance -> {
            ESeriesCardContent(
                isValueValid = false,
                cardLabel = stringResource(R.string.vtc_invalid_tolerance_label),
                cardBody = stringResource(R.string.vtc_invalid_tolerance_body, eSeriesCardContent.value),
                onLearnMoreTapped = onLearnMoreTapped,
            )
        }
        is ESeriesCardContent.InvalidResistance -> {
            ESeriesCardContent(
                isValueValid = false,
                cardLabel = stringResource(R.string.vtc_invalid_card_label),
                cardBody = stringResource(R.string.vtc_invalid_card_body, eSeriesCardContent.value),
                onLearnMoreTapped = onLearnMoreTapped,
                textButtonLabel = stringResource(R.string.vtc_invalid_card_action),
                onClick = onUseValueTapped,
            )
        }
        ESeriesCardContent.NoContent -> { /* left intentionally empty to show no card */ }
    }
}

@Composable
private fun ESeriesCardContent(
    isValueValid: Boolean,
    cardLabel: String,
    cardBody: String,
    onLearnMoreTapped: () -> Unit,
    textButtonLabel: String = "",
    onClick: () -> Unit = {},
) {
    val icon = if (isValueValid) Icons.Outlined.CheckCircle else Icons.Rounded.WarningAmber
    val color = if (isValueValid) validGreen else warningGold
    AppCard(
        modifier = Modifier
            .padding(top = 12.dp)
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.Top
        ) {
            Image(
                modifier = Modifier.size(32.dp),
                imageVector = icon,
                contentDescription = null,
                colorFilter = ColorFilter.tint(color)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = cardLabel,
                    modifier = Modifier.padding(bottom = 8.dp),
                    style = textStyleCallout(),
                )
                Text(
                    text = cardBody,
                    style = textStyleBody().onSurfaceVariant(),
                )
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End,
        ) {
            if (textButtonLabel.isNotEmpty()) {
                TextButton(
                    modifier = Modifier
                        .padding(end = 8.dp),
                    onClick = onClick,
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    Text(
                        text = textButtonLabel,
                        style = textStyleCallout(),
                    )
                }
            }
            TextButton(
                modifier = Modifier
                    .padding(end = 8.dp),
                onClick = onLearnMoreTapped,
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Text(
                    text = stringResource(R.string.vtc_valid_card_action),
                    style = textStyleCallout(),
                )
            }
        }
    }
}

@AppComponentPreviews
@Composable
private fun ESeriesCardPreview() {
    ResistorCalculatorTheme {
        Column {
            ESeriesCard(ESeriesCardContent.ValidResistance("E-12"), {}) {}
            ESeriesCard(ESeriesCardContent.InvalidResistance("22 Ω"), {}) {}
            ESeriesCard(ESeriesCardContent.InvalidTolerance("4"), {}) {}
        }
    }
}
