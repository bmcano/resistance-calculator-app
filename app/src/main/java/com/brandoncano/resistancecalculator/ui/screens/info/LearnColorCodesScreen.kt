package com.brandoncano.resistancecalculator.ui.screens.info

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
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
import com.brandoncano.resistancecalculator.constants.Lists
import com.brandoncano.resistancecalculator.constants.Symbols
import com.brandoncano.resistancecalculator.data.ResistorColorCodeItemPO
import com.brandoncano.resistancecalculator.to.ResistorCtv
import com.brandoncano.resistancecalculator.ui.composables.BottomScreenSpacer
import com.brandoncano.resistancecalculator.ui.composables.m3.M3CallOutCard
import com.brandoncano.resistancecalculator.ui.composables.m3.M3Divider
import com.brandoncano.resistancecalculator.ui.composables.m3.M3OutlinedCard
import com.brandoncano.resistancecalculator.ui.composables.m3.M3Scaffold
import com.brandoncano.resistancecalculator.ui.composables.m3.M3ScreenColumn
import com.brandoncano.resistancecalculator.ui.composables.m3.M3TopAppBar
import com.brandoncano.resistancecalculator.ui.composables.m3.elevatedCardColor
import com.brandoncano.resistancecalculator.ui.theme.ResistorCalculatorTheme
import com.brandoncano.resistancecalculator.ui.theme.black
import com.brandoncano.resistancecalculator.ui.theme.blue
import com.brandoncano.resistancecalculator.ui.theme.gray
import com.brandoncano.resistancecalculator.ui.theme.red
import com.brandoncano.resistancecalculator.ui.theme.violet
import com.brandoncano.resistancecalculator.ui.theme.white
import com.brandoncano.resistancecalculator.util.ColorFinder
import com.brandoncano.resistancecalculator.util.resistor.ResistorImageBuilder
import com.brandoncano.sharedcomponents.composables.AppLongScreenPreview
import com.brandoncano.sharedcomponents.composables.AppScreenPreviews
import com.brandoncano.resistancecalculator.constants.Colors as C

/**
 * Note: Information originated from - https://eepower.com/resistor-guide/resistor-standards-and-codes/resistor-color-code/#
 */

@OptIn(ExperimentalMaterial3Api::class) // For TopAppBar
@Composable
fun LearnColorCodesScreen(
    onNavigateBack: () -> Unit,
) {
    M3Scaffold(
        topBar = {
            M3TopAppBar(
                titleText = stringResource(R.string.info_color_title),
                navigationIcon =  Icons.AutoMirrored.Filled.ArrowBack,
                onNavigateBack = onNavigateBack,
                scrollBehavior = it,
            )
        },
    )  { paddingValues ->
        LearnColorCodesScreenContent(paddingValues)
    }
}

@Composable
private fun LearnColorCodesScreenContent(paddingValues: PaddingValues) {
    M3ScreenColumn(
        paddingValues = paddingValues,
    ) {
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = stringResource(R.string.info_color_body1),
            modifier = Modifier.padding(bottom = 16.dp),
            style = MaterialTheme.typography.bodyMedium.gray(),
        )
        Text(
            text = stringResource(R.string.info_color_body2),
            modifier = Modifier.padding(bottom = 24.dp),
            style = MaterialTheme.typography.bodyMedium.gray(),
        )
        ResistorColorCodeTable()
        Spacer(modifier = Modifier.height(32.dp))
        ResistorBandSectionContent(
            headlineRes = R.string.info_color_three_band_headline,
            bodyRes = R.string.info_color_three_band_body,
            codeRes = R.string.info_color_three_band_code,
            descriptionRes = R.string.info_color_three_band_description,
            resistor = ResistorCtv(C.RED, C.VIOLET, "", C.ORANGE, "", "", 0),
        )
        Spacer(modifier = Modifier.height(32.dp))
        ResistorBandSectionContent(
            headlineRes = R.string.info_color_four_band_headline,
            bodyRes = R.string.info_color_four_band_body,
            codeRes = R.string.info_color_four_band_code,
            descriptionRes = R.string.info_color_four_band_description,
            resistor = ResistorCtv(C.YELLOW, C.VIOLET, "", C.RED, C.GOLD, "", 1),
        )
        Spacer(modifier = Modifier.height(32.dp))
        ResistorBandSectionContent(
            headlineRes = R.string.info_color_five_band_headline,
            bodyRes = R.string.info_color_five_band_body,
            codeRes = R.string.info_color_five_band_code,
            descriptionRes = R.string.info_color_five_band_description,
            resistor = ResistorCtv(C.BROWN, C.GREEN, C.BLACK, C.ORANGE, C.BROWN, "", 2),
        )
        Spacer(modifier = Modifier.height(32.dp))
        ResistorBandSectionContent(
            headlineRes = R.string.info_color_six_band_headline,
            bodyRes = R.string.info_color_six_band_body,
            codeRes = R.string.info_color_six_band_code,
            descriptionRes = R.string.info_color_six_band_description,
            resistor = ResistorCtv(C.GREEN, C.BLUE, C.BLACK, C.BROWN, C.RED, C.BROWN, 3),
        )
        DisclaimerText()
        BottomScreenSpacer()
    }
}

@Composable
fun ResistorColorCodeTable() {
    M3OutlinedCard(
        modifier = Modifier.fillMaxWidth(),
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
            Lists.RESISTOR_COLOR_CODES.forEachIndexed { index, code ->
                ColorCodeRow(code)
                if (index != Lists.RESISTOR_COLOR_CODES.lastIndex) M3Divider(insetPadding = 16.dp)
            }
        }
    }
}

@Composable
private fun ColorCodeRow(code: ResistorColorCodeItemPO) {
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

@Composable
private fun ResistorBandSectionContent(
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
    M3CallOutCard(stringResource(codeRes), color = elevatedCardColor())
    Text(
        text = stringResource(R.string.info_color_code_band_breakdown),
        modifier = Modifier.padding(vertical = 12.dp),
        style = MaterialTheme.typography.bodyLarge.gray(),
    )
    M3CallOutCard(stringResource(descriptionRes), color = elevatedCardColor())
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

/**
 * Note: Used for all info screens
 */
@Composable
fun DisclaimerText() {
    M3Divider(modifier = Modifier.padding(vertical = 24.dp))
    Text(
        text = stringResource(R.string.info_disclaimer_footer),
        style = MaterialTheme.typography.bodySmall.gray(),
    )
}

@AppScreenPreviews
@AppLongScreenPreview
@Composable
private fun LearnColorCodesScreenPreview() {
    ResistorCalculatorTheme { LearnColorCodesScreen {} }
}
