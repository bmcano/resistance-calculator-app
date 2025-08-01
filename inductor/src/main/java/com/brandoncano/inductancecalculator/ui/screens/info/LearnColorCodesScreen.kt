package com.brandoncano.inductancecalculator.ui.screens.info

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
import com.brandoncano.inductancecalculator.R
import com.brandoncano.inductancecalculator.constants.Colors
import com.brandoncano.inductancecalculator.constants.Lists
import com.brandoncano.inductancecalculator.data.InductorColorCodeItemPO
import com.brandoncano.inductancecalculator.to.InductorCtv
import com.brandoncano.inductancecalculator.ui.theme.InductorCalculatorTheme
import com.brandoncano.inductancecalculator.ui.theme.black
import com.brandoncano.inductancecalculator.ui.theme.blue
import com.brandoncano.inductancecalculator.ui.theme.red
import com.brandoncano.inductancecalculator.ui.theme.violet
import com.brandoncano.inductancecalculator.util.ColorFinder
import com.brandoncano.inductancecalculator.util.InductorImageBuilder
import com.brandoncano.library.m3.BottomScreenSpacer
import com.brandoncano.library.m3.M3CallOutCard
import com.brandoncano.library.m3.M3Divider
import com.brandoncano.library.m3.M3OutlinedCard
import com.brandoncano.library.m3.M3Scaffold
import com.brandoncano.library.m3.M3ScreenColumn
import com.brandoncano.library.m3.M3TopAppBar
import com.brandoncano.library.m3.ScreenPreviews
import com.brandoncano.library.m3.elevatedCardColor
import com.brandoncano.library.theme.black
import com.brandoncano.library.theme.gray
import com.brandoncano.library.theme.white

@OptIn(ExperimentalMaterial3Api::class) // For TopAppBar
@Composable
fun LearnColorCodesScreen(
    onNavigateBack: () -> Unit,
) {
    M3Scaffold(
        topBar = {
            M3TopAppBar(
                titleText = stringResource(R.string.info_color_title),
                navigationIcon = Icons.AutoMirrored.Filled.ArrowBack,
                onNavigateBack = onNavigateBack,
                scrollBehavior = it,
            )
        },
    ) { paddingValues ->
        LearnColorCodesScreenContent(paddingValues)
    }
}

@Composable
private fun LearnColorCodesScreenContent(paddingValues: PaddingValues) {
    M3ScreenColumn (
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
        InductorColorCodeTable()
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = stringResource(R.string.learn_color_four_band_headline),
            modifier = Modifier.padding(bottom = 12.dp),
            style = MaterialTheme.typography.titleMedium,
        )
        Text(
            text = stringResource(R.string.learn_color_four_band_body),
            modifier = Modifier.padding(bottom = 16.dp),
            style = MaterialTheme.typography.bodyMedium.gray(),
        )
        InfoInductorLayout(InductorCtv(Colors.YELLOW, Colors.VIOLET, Colors.RED, Colors.GOLD))
        Text(
            text = stringResource(R.string.info_color_code_calculation),
            modifier = Modifier.padding(vertical = 12.dp),
            style = MaterialTheme.typography.bodyLarge.gray(),
        )
        M3CallOutCard(stringResource(R.string.learn_color_four_band_code), color = elevatedCardColor())
        Text(
            text = stringResource(R.string.info_color_code_band_breakdown),
            modifier = Modifier.padding(vertical = 12.dp),
            style = MaterialTheme.typography.bodyLarge.gray(),
        )
        M3CallOutCard(stringResource(R.string.learn_color_four_band_description), color = elevatedCardColor())
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = stringResource(R.string.learn_color_five_band_headline),
            modifier = Modifier.padding(bottom = 12.dp),
            style = MaterialTheme.typography.titleMedium,
        )
        Text(
            text = stringResource(R.string.learn_color_five_band_body),
            style = MaterialTheme.typography.bodyMedium.gray(),
        )
        DisclaimerText()
        BottomScreenSpacer()
    }
}

@Composable
private fun InductorColorCodeTable() {
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
                HeaderCell(
                    Modifier.weight(1f),
                    stringResource(R.string.info_color)
                )
                HeaderCell(
                    Modifier.weight(
                        0.75f
                    ), stringResource(R.string.info_digit)
                )
                HeaderCell(
                    Modifier.weight(1f),
                    stringResource(R.string.info_multiplier)
                )
                HeaderCell(
                    Modifier.weight(1f),
                    stringResource(R.string.info_tolerance)
                )
            }
            M3Divider(insetPadding = 16.dp)
            Lists.INDUCTOR_COLOR_CODES.forEachIndexed { index, code ->
                ColorCodeRow(code)
                if (index != Lists.INDUCTOR_COLOR_CODES.lastIndex) M3Divider(insetPadding = 16.dp)
            }
        }
    }
}

@Composable
private fun ColorCodeRow(code: InductorColorCodeItemPO) {
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
private fun InfoInductorLayout(inductor: InductorCtv) {
    val imageColorPairs = InductorImageBuilder.execute(inductor)
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

@Composable
fun DisclaimerText() {
    M3Divider(modifier = Modifier.padding(vertical = 24.dp))
    Text(
        text = stringResource(R.string.info_disclaimer_footer),
        style = MaterialTheme.typography.bodySmall.gray(),
    )
}

@ScreenPreviews
@Composable
private fun LearnColorCodesScreenPreview() {
    InductorCalculatorTheme {
        LearnColorCodesScreen {}
    }
}
