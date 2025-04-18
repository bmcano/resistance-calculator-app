package com.brandoncano.resistancecalculator.ui.screens.ctv

import android.graphics.Picture
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.brandoncano.resistancecalculator.R
import com.brandoncano.resistancecalculator.constants.Colors
import com.brandoncano.resistancecalculator.to.ResistorCtv
import com.brandoncano.resistancecalculator.ui.theme.ResistorCalculatorTheme
import com.brandoncano.resistancecalculator.util.ColorFinder
import com.brandoncano.resistancecalculator.util.Sdk
import com.brandoncano.resistancecalculator.util.resistor.bandFiveForDisplay
import com.brandoncano.resistancecalculator.util.resistor.bandFourForDisplay
import com.brandoncano.resistancecalculator.util.resistor.bandOneForDisplay
import com.brandoncano.resistancecalculator.util.resistor.bandSixForDisplay
import com.brandoncano.resistancecalculator.util.resistor.bandThreeForDisplay
import com.brandoncano.resistancecalculator.util.resistor.bandTwoForDisplay
import com.brandoncano.resistancecalculator.util.resistor.deriveResistorColor
import com.brandoncano.resistancecalculator.util.resistor.formatResistance
import com.brandoncano.sharedcomponents.composables.AppCard
import com.brandoncano.sharedcomponents.composables.AppComponentPreviews
import com.brandoncano.sharedcomponents.composables.DrawContent
import com.brandoncano.sharedcomponents.text.onSurfaceVariant
import com.brandoncano.sharedcomponents.text.textStyleCallout
import com.brandoncano.sharedcomponents.text.textStyleHeadline
import com.brandoncano.sharedcomponents.text.textStyleSubhead
import com.brandoncano.sharedcomponents.text.textStyleTitle

@Composable
fun ResistorDisplay(picture: Picture, resistor: ResistorCtv) {
    if (Sdk.isAtLeastAndroid7()) {
        DrawContent(picture) {
            ResistorLayout(resistor)
        }
    } else {
        ResistorLayout(resistor)
    }
}

data class ResistorImagePair(@DrawableRes val drawableRes: Int, val color: String)

@Composable
fun ResistorLayout(resistor: ResistorCtv) {
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
        ResistanceText(
            if (resistor.isEmpty()) {
                stringResource(id = R.string.default_ctv_value)
            } else {
                resistor.formatResistance()
            }
        )
    }
}

@Composable
fun ResistorRow(vararg resistorImages: ResistorImagePair) {
    Row(horizontalArrangement = Arrangement.Absolute.Center) {
        resistorImages.forEach { resistorImage ->
            val color = ColorFinder.textToColor(resistorImage.color)
            Image(
                painter = painterResource(id = resistorImage.drawableRes),
                contentDescription = null,
                colorFilter = ColorFilter.tint(color),
            )
        }
    }
}

@Composable
fun ResistanceText(resistance: String) {
    AppCard(modifier = Modifier.padding(top = 12.dp)) {
        Text(
            text = resistance,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 6.dp, bottom = 6.dp, start = 12.dp, end = 12.dp),
            style = textStyleTitle(),
        )
    }
}

@AppComponentPreviews
@Composable
private fun ResistorLayoutsPreview() {
    ResistorCalculatorTheme {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            ResistorLayout(ResistorCtv())
            ResistorLayout(ResistorCtv(navBarSelection = 2))
            ResistorLayout(ResistorCtv("Red", "Orange", "", "Yellow", "", "", 0))
            ResistorLayout(ResistorCtv("Red", "Orange", "", "Yellow", "Green", "", 1))
            ResistorLayout(ResistorCtv("Red", "Orange", "Black", "Yellow", "Green", "", 2))
            ResistorLayout(ResistorCtv("Red", "Orange", "Black", "Yellow", "Green", "Blue", 3))
        }
    }
}

// TODO - This will be extracted into the shared lib
//  - Check spacings
data class CardAction(
    val buttonLabel: String,
    val onClick: () -> Unit
)

@Composable
fun AppActionCard(
    icon: ImageVector,
    iconTint: Color,
    cardTitle: String,
    cardBody: String,
    actions: List<CardAction> = emptyList(),
) {
    AppCard(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.Top
        ) {
            Image(
                modifier = Modifier.size(32.dp),
                imageVector = icon,
                contentDescription = null,
                colorFilter = ColorFilter.tint(iconTint)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = cardTitle,
                    modifier = Modifier.padding(bottom = 8.dp),
                    style = textStyleHeadline(),
                )
                Text(
                    text = cardBody,
                    style = textStyleSubhead().onSurfaceVariant(),
                )
            }
        }
        if (actions.isEmpty()) return@AppCard
        Row(horizontalArrangement = Arrangement.Start) {
            actions.forEach { action ->
                TextButton(
                    onClick = action.onClick,
                    modifier = Modifier.padding(start = 16.dp, end = 8.dp)
                ) {
                    Text(
                        text = action.buttonLabel,
                        style = textStyleCallout(),
                    )
                }
            }
        }
    }
}
