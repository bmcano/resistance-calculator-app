package com.brandoncano.resistancecalculator.ui.screens.info

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.brandoncano.resistancecalculator.R
import com.brandoncano.resistancecalculator.constants.Symbols
import com.brandoncano.resistancecalculator.ui.theme.black
import com.brandoncano.resistancecalculator.ui.theme.blue
import com.brandoncano.resistancecalculator.ui.theme.red
import com.brandoncano.resistancecalculator.ui.theme.violet
import com.brandoncano.resistancecalculator.ui.theme.white
import com.brandoncano.resistancecalculator.util.ColorFinder
import com.brandoncano.sharedcomponents.composables.AppCard
import com.brandoncano.sharedcomponents.composables.AppComponentPreviews
import com.brandoncano.sharedcomponents.composables.AppDivider
import com.brandoncano.sharedcomponents.composables.AppStandardDivider
import com.brandoncano.sharedcomponents.text.onSurfaceVariant
import com.brandoncano.sharedcomponents.text.textStyleBody
import com.brandoncano.sharedcomponents.text.textStyleCaption
import com.brandoncano.sharedcomponents.text.textStyleHeadline
import com.brandoncano.sharedcomponents.text.textStyleSubhead

/**
 * Shared Info Components
 */
@Composable
fun DisclaimerText() {
    AppDivider(modifier = Modifier.padding(vertical = 24.dp))
    Text(
        text = stringResource(R.string.info_disclaimer_footer),
        style = textStyleCaption().onSurfaceVariant(),
    )
}

@Composable
fun CodeExampleCard(code: String, description: String) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(vertical = 12.dp, horizontal = 16.dp)) {
            Text(
                text = code,
                style = textStyleHeadline(),
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = description,
                style = textStyleBody().onSurfaceVariant(),
            )
        }
    }
}

/**
 * Color Code Info
 */
@AppComponentPreviews
@Composable
fun ResistorColorCodeTable() {
    data class ResistorColorCode(
        val color: String,
        val significantFigures: String,
        val multiplier: String,
        val tolerance: String,
        val tempCoefficient: String,
    )

    val resistorColorCodes = listOf(
        ResistorColorCode("Black", "0", "${Symbols.X}1", "-", "250"),
        ResistorColorCode("Brown", "1", "${Symbols.X}10", "${Symbols.PM}1%", "100"),
        ResistorColorCode("Red", "2", "${Symbols.X}100", "${Symbols.PM}2%", "50"),
        ResistorColorCode("Orange", "3", "${Symbols.X}1k", "-", "15"),
        ResistorColorCode("Yellow", "4", "${Symbols.X}10k", "-", "25"),
        ResistorColorCode("Green", "5", "${Symbols.X}100k", "${Symbols.PM}0.5%", "20"),
        ResistorColorCode("Blue", "6", "${Symbols.X}1M", "${Symbols.PM}0.25%", "10"),
        ResistorColorCode("Violet", "7", "${Symbols.X}10M", "${Symbols.PM}0.1%", "5"),
        ResistorColorCode("Gray", "8", "${Symbols.X}100M", "${Symbols.PM}0.05%", "1"),
        ResistorColorCode("White", "9", "${Symbols.X}1G", "-", "-"),
        ResistorColorCode("Gold", "-", "${Symbols.X}0.1", "${Symbols.PM}5%", "-"),
        ResistorColorCode("Silver", "-", "${Symbols.X}0.01", "${Symbols.PM}10%", "-"),
        ResistorColorCode("None", "-", "-", "${Symbols.PM}20%", "-")
    )

    Card(modifier = Modifier.fillMaxWidth()) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                val modifier =  Modifier.weight(1f)
                HeaderCell(modifier, "Color")
                HeaderCell(Modifier.weight(0.75f), "Digit")
                HeaderCell(modifier, "Multiplier")
                HeaderCell(modifier, "Tolerance")
                HeaderCell(modifier, "Temp. Coeff.")
            }
            AppStandardDivider()
            resistorColorCodes.forEachIndexed { index, resistorColorCode ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    val color = ColorFinder.textToColor(resistorColorCode.color)
                    TableColorCell(
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 8.dp, end = 8.dp)
                            .background(
                                color = color,
                                shape = MaterialTheme.shapes.small
                            )
                            .padding(vertical = 4.dp),
                        text = resistorColorCode.color,
                        backgroundColor = color,
                    )
                    val modifier = Modifier.padding(12.dp)
                    TableCell(modifier.weight(0.75f), resistorColorCode.significantFigures)
                    TableCell(modifier.weight(1f), resistorColorCode.multiplier)
                    TableCell(modifier.weight(1f), resistorColorCode.tolerance)
                    TableCell(modifier.weight(1f), resistorColorCode.tempCoefficient)
                }
                if (index != resistorColorCodes.size - 1) AppStandardDivider()
            }
        }
    }
}

@Composable
private fun HeaderCell(modifier: Modifier, text: String) {
    Text(
        text = text,
        modifier = modifier,
        style = textStyleSubhead(),
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
            style = textStyleCaption().onSurfaceVariant(),
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
            in darkColors -> textStyleCaption().white()
            else -> textStyleCaption().black()
        }
        Text(
            text = text,
            style = style,
            textAlign = TextAlign.Center,
        )
    }
}

/**
 * Preferred Values Info
 */
@Composable
fun ESeriesTable(seriesName: String, values: List<Int>) {
    AppCard(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier
                .padding(vertical = 16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = seriesName,
                modifier = Modifier.padding(bottom = 8.dp),
                style = textStyleHeadline(),
            )
            AppStandardDivider()
            val columns = 6
            val rows = (values.size + columns - 1) / columns
            (0 until rows).forEach { rowIndex ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                ) {
                    (0 until columns).forEach { columnIndex ->
                        val index = rowIndex * columns + columnIndex
                        if (index < values.size) {
                            Text(
                                text = values[index].toString(),
                                style = textStyleSubhead().onSurfaceVariant(),
                                modifier = Modifier
                                    .padding(start = 8.dp, end = 8.dp, top = 8.dp)
                                    .weight(1f),
                                textAlign = TextAlign.Center,
                            )
                        }
                    }
                }
            }
        }
    }
}

/**
 * SMD Codes Info
 */
private data class CodeValue(val code: String, val value: String)
private val codeValueList = listOf(
    CodeValue("01", "100"), CodeValue("02", "102"), CodeValue("03", "105"), CodeValue("04", "107"),
    CodeValue("05", "110"), CodeValue("06", "113"), CodeValue("07", "115"), CodeValue("08", "118"),
    CodeValue("09", "121"), CodeValue("10", "124"), CodeValue("11", "127"), CodeValue("12", "130"),
    CodeValue("13", "133"), CodeValue("14", "137"), CodeValue("15", "140"), CodeValue("16", "143"),
    CodeValue("17", "147"), CodeValue("18", "150"), CodeValue("19", "154"), CodeValue("20", "158"),
    CodeValue("21", "162"), CodeValue("22", "165"), CodeValue("23", "169"), CodeValue("24", "174"),
    CodeValue("25", "178"), CodeValue("26", "182"), CodeValue("27", "187"), CodeValue("28", "191"),
    CodeValue("29", "196"), CodeValue("30", "200"), CodeValue("31", "205"), CodeValue("32", "210"),
    CodeValue("33", "215"), CodeValue("34", "221"), CodeValue("35", "226"), CodeValue("36", "232"),
    CodeValue("37", "237"), CodeValue("38", "243"), CodeValue("39", "249"), CodeValue("40", "255"),
    CodeValue("41", "261"), CodeValue("42", "267"), CodeValue("43", "274"), CodeValue("44", "280"),
    CodeValue("45", "287"), CodeValue("46", "294"), CodeValue("47", "301"), CodeValue("48", "309"),
    CodeValue("49", "316"), CodeValue("50", "324"), CodeValue("51", "332"), CodeValue("52", "340"),
    CodeValue("53", "348"), CodeValue("54", "357"), CodeValue("55", "365"), CodeValue("56", "374"),
    CodeValue("57", "383"), CodeValue("58", "392"), CodeValue("59", "402"), CodeValue("60", "412"),
    CodeValue("61", "422"), CodeValue("62", "432"), CodeValue("63", "442"), CodeValue("64", "453"),
    CodeValue("65", "464"), CodeValue("66", "475"), CodeValue("67", "487"), CodeValue("68", "499"),
    CodeValue("69", "511"), CodeValue("70", "523"), CodeValue("71", "536"), CodeValue("72", "549"),
    CodeValue("73", "562"), CodeValue("74", "576"), CodeValue("75", "590"), CodeValue("76", "604"),
    CodeValue("77", "619"), CodeValue("78", "634"), CodeValue("79", "649"), CodeValue("80", "665"),
    CodeValue("81", "681"), CodeValue("82", "698"), CodeValue("83", "715"), CodeValue("84", "732"),
    CodeValue("85", "750"), CodeValue("86", "768"), CodeValue("87", "787"), CodeValue("88", "806"),
    CodeValue("89", "825"), CodeValue("90", "845"), CodeValue("91", "866"), CodeValue("92", "887"),
    CodeValue("93", "909"), CodeValue("94", "931"), CodeValue("95", "953"), CodeValue("96", "976")
)

@AppComponentPreviews
@Composable
fun CodeValueTable() {
    val rows = codeValueList.chunked(3)
    AppCard(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(vertical = 12.dp)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 4.dp),
            ) {
                (1..3).forEach { _ ->
                    Text(
                        text = stringResource(R.string.info_smd_code_value_col1),
                        modifier = Modifier.weight(1f),
                        style = textStyleHeadline(),
                        textAlign = TextAlign.Center,
                    )
                    Text(
                        text = stringResource(R.string.info_smd_code_value_col2),
                        modifier = Modifier.weight(1f),
                        style = textStyleHeadline(),
                        textAlign = TextAlign.Center,
                    )
                }
            }
            AppStandardDivider()
            rows.forEach { rowItems ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly,
                ) {
                    rowItems.forEach { codeValue ->
                        Text(
                            text = codeValue.code,
                            style = textStyleSubhead(),
                            modifier = Modifier.weight(1f),
                            textAlign = TextAlign.Center,
                        )
                        Text(
                            text = codeValue.value,
                            style = textStyleSubhead().onSurfaceVariant(),
                            modifier = Modifier.weight(1f),
                            textAlign = TextAlign.Center,
                        )
                    }
                }
            }
        }
    }
}

@AppComponentPreviews
@Composable
fun MultiplierTable() {
    val multipliers = listOf(
        "Z" to "0.001", "Y / R" to "0.01", "X / S" to "0.1", "A" to "1", "B / H" to "10",
        "C" to "100", "D" to "1,000", "E" to "10,000", "F" to "100,000"
    )
    AppCard(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(vertical = 12.dp, horizontal = 16.dp)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 4.dp, start = 4.dp),
            ) {
                Text(
                    text = stringResource(R.string.info_smd_code_value_col1),
                    modifier = Modifier.weight(1f),
                    style = textStyleHeadline(),
                    textAlign = TextAlign.Start,
                )
                Text(
                    text = stringResource(R.string.info_smd_code_value_col2),
                    modifier = Modifier.weight(1f),
                    style = textStyleHeadline(),
                    textAlign = TextAlign.Start,
                )
            }
            AppDivider(Modifier.fillMaxWidth())
            multipliers.forEach { (letter, value) ->
                Row(
                    modifier = Modifier.padding(vertical = 4.dp, horizontal = 4.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = letter,
                        modifier = Modifier.weight(1f),
                        style = textStyleSubhead(),
                    )
                    Text(
                        text = "${Symbols.X}$value",
                        modifier = Modifier.weight(1f),
                        style = textStyleSubhead().onSurfaceVariant(),
                    )
                }
            }
        }
    }
}
