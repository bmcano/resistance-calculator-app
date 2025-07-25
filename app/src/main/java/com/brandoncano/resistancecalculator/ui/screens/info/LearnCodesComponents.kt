package com.brandoncano.resistancecalculator.ui.screens.info

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.brandoncano.resistancecalculator.R
import com.brandoncano.resistancecalculator.constants.Symbols
import com.brandoncano.resistancecalculator.to.ResistorCtv
import com.brandoncano.resistancecalculator.ui.composables.CalloutCard
import com.brandoncano.resistancecalculator.ui.composables.elevatedCardColor
import com.brandoncano.resistancecalculator.ui.composables.m3.M3Divider
import com.brandoncano.resistancecalculator.ui.composables.m3.M3OutlinedCard
import com.brandoncano.resistancecalculator.ui.theme.black
import com.brandoncano.resistancecalculator.ui.theme.blue
import com.brandoncano.resistancecalculator.ui.theme.gray
import com.brandoncano.resistancecalculator.ui.theme.red
import com.brandoncano.resistancecalculator.ui.theme.violet
import com.brandoncano.resistancecalculator.ui.theme.white
import com.brandoncano.resistancecalculator.util.ColorFinder
import com.brandoncano.resistancecalculator.util.resistor.ResistorImageBuilder
import com.brandoncano.sharedcomponents.composables.AppComponentPreviews

@Composable
fun BandSection(
    headlineRes: Int,
    bodyRes: Int,
    codeRes: Int,
    descriptionRes: Int,
    resistor: ResistorCtv,
) {
    Text(
        text = stringResource(headlineRes),
        modifier = Modifier.padding(bottom = 12.dp),
        style = MaterialTheme.typography.titleMedium,
    )
    Text(
        text = stringResource(bodyRes),
        style = MaterialTheme.typography.bodyMedium.gray(),
        modifier = Modifier.padding(bottom = 16.dp),
    )
    InfoResistorLayout(resistor)
    Text(
        text = stringResource(R.string.info_color_code_calculation),
        modifier = Modifier.padding(vertical = 12.dp),
        style = MaterialTheme.typography.bodyLarge.gray(),
    )
    CalloutCard(stringResource(codeRes), color = elevatedCardColor())
    Text(
        text = stringResource(R.string.info_color_code_band_breakdown),
        modifier = Modifier.padding(vertical = 12.dp),
        style = MaterialTheme.typography.bodyLarge.gray(),
    )
    CalloutCard(stringResource(descriptionRes), color = elevatedCardColor())
}

@Composable
private fun InfoResistorLayout(resistor: ResistorCtv) {
    val imageColorPairs = ResistorImageBuilder.execute(resistor)
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp, vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row {
            imageColorPairs.forEach { (drawableRes, color) ->
                val tint = remember(color) { ColorFinder.textToColor(color) }
                Image(
                    painter = painterResource(id = drawableRes),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(tint),
                )
            }
        }
    }
}

data class ResistorColorCode(
    val colorResId: Int,
    val significantFigures: String,
    val multiplier: String,
    val tolerance: String,
    val tempCoefficient: String,
)

val resistorColorCodes = listOf(
    ResistorColorCode(R.string.color_black, "0", "${Symbols.X}1", "-", "250"),
    ResistorColorCode(R.string.color_brown, "1", "${Symbols.X}10", "${Symbols.PM}1%", "100"),
    ResistorColorCode(R.string.color_red, "2", "${Symbols.X}100", "${Symbols.PM}2%", "50"),
    ResistorColorCode(R.string.color_orange, "3", "${Symbols.X}1k", "-", "15"),
    ResistorColorCode(R.string.color_yellow, "4", "${Symbols.X}10k", "-", "25"),
    ResistorColorCode(R.string.color_green, "5", "${Symbols.X}100k", "${Symbols.PM}0.5%", "20"),
    ResistorColorCode(R.string.color_blue, "6", "${Symbols.X}1M", "${Symbols.PM}0.25%", "10"),
    ResistorColorCode(R.string.color_violet, "7", "${Symbols.X}10M", "${Symbols.PM}0.1%", "5"),
    ResistorColorCode(R.string.color_gray, "8", "${Symbols.X}100M", "${Symbols.PM}0.05%", "1"),
    ResistorColorCode(R.string.color_white, "9", "${Symbols.X}1G", "-", "-"),
    ResistorColorCode(R.string.color_gold, "-", "${Symbols.X}0.1", "${Symbols.PM}5%", "-"),
    ResistorColorCode(R.string.color_silver, "-", "${Symbols.X}0.01", "${Symbols.PM}10%", "-"),
    ResistorColorCode(R.string.color_none, "-", "-", "${Symbols.PM}20%", "-")
)

@AppComponentPreviews
@Composable
fun ResistorColorCodeTable() {
    M3OutlinedCard(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                HeaderCell(Modifier.weight(1f), stringResource(R.string.info_color))
                HeaderCell(Modifier.weight(0.75f), stringResource(R.string.info_digit))
                HeaderCell(Modifier.weight(1f), stringResource(R.string.info_multiplier))
                HeaderCell(Modifier.weight(1f), stringResource(R.string.info_tolerance))
                HeaderCell(Modifier.weight(1f), Symbols.PPM)
            }
            M3Divider(insetPadding = 16.dp)
            resistorColorCodes.forEachIndexed { index, code ->
                ColorCodeRow(code)
                if (index != resistorColorCodes.lastIndex) M3Divider(insetPadding = 16.dp)
            }
        }
    }
}

@Composable
private fun ColorCodeRow(code: ResistorColorCode) {
    val colorName = stringResource(id = code.colorResId)
    val color = ColorFinder.textToColor(colorName)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        TableColorCell(
            modifier = Modifier
                .weight(1f)
                .padding(start = 8.dp, end = 8.dp)
                .background(color = color, shape = MaterialTheme.shapes.small)
                .padding(vertical = 4.dp),
            text = colorName,
            backgroundColor = color,
        )
        val modifier = Modifier.padding(12.dp)
        TableCell(modifier.weight(0.75f), code.significantFigures)
        TableCell(modifier.weight(1f), code.multiplier)
        TableCell(modifier.weight(1f), code.tolerance)
        TableCell(modifier.weight(1f), code.tempCoefficient)
    }
}

@Composable
private fun HeaderCell(modifier: Modifier, text: String) {
    Text(
        text = text,
        modifier = modifier,
        style = MaterialTheme.typography.labelMedium,
        textAlign = TextAlign.Center,
    )
}

@Composable
private fun TableCell(modifier: Modifier, text: String) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodySmall.gray(),
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
private fun TableColorCell(modifier: Modifier, text: String, backgroundColor: Color) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        val darkColors = setOf(black, red, blue, violet)
        val style = when (backgroundColor) {
            in darkColors -> MaterialTheme.typography.bodySmall.white()
            else -> MaterialTheme.typography.bodySmall.black()
        }
        Text(
            text = text,
            style = style,
            textAlign = TextAlign.Center,
        )
    }
}

/**
 * Shared Info Components
 */
@Composable
fun DisclaimerText() {
    M3Divider(modifier = Modifier.padding(vertical = 24.dp))
    Text(
        text = stringResource(R.string.info_disclaimer_footer),
        style = MaterialTheme.typography.bodySmall.gray(),
    )
}
