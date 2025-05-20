package com.brandoncano.resistancecalculator.ui.screens.info

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.brandoncano.resistancecalculator.R
import com.brandoncano.sharedcomponents.composables.AppCard
import com.brandoncano.sharedcomponents.composables.AppComponentPreviews
import com.brandoncano.sharedcomponents.composables.AppTable
import com.brandoncano.sharedcomponents.text.onSurfaceVariant
import com.brandoncano.sharedcomponents.text.textStyleCallout
import com.brandoncano.sharedcomponents.text.textStyleHeadline
import com.brandoncano.sharedcomponents.text.textStyleSubhead

@Composable
fun CodeInfoSection(
    headlineRes: Int,
    bodyRes: Int,
    formulaRes: Int,
    exampleLabelRes: Int,
    exampleRes: Int,
) {
    Text(
        text = stringResource(headlineRes),
        modifier = Modifier.padding(bottom = 12.dp),
        style = textStyleHeadline(),
    )
    Text(
        text = stringResource(bodyRes),
        modifier = Modifier.padding(bottom = 12.dp),
        style = textStyleSubhead().onSurfaceVariant(),
    )
    EquationCard(stringResource(formulaRes))
    Text(
        text = stringResource(exampleLabelRes),
        modifier = Modifier.padding(vertical = 12.dp),
        style = textStyleSubhead().onSurfaceVariant(),
    )
    EquationCard(stringResource(exampleRes))
}

@Composable
private fun EquationCard(equation: String) {
    AppCard(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Text(
            text = equation,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            style = textStyleCallout(),
            textAlign = TextAlign.Center,
        )
    }
}

@AppComponentPreviews
@Composable
fun MultiplierTable() {
    AppCard {
        AppTable(
            columnTitles = listOf(
                stringResource(id = R.string.info_smd_code_value_col1),
                stringResource(id = R.string.info_smd_code_value_col2),
            ),
            rows = listOf(
                listOf("Z", "0.001"),
                listOf("Y / R", "0.01"),
                listOf("X / S", "0.1"),
                listOf("A", "1"),
                listOf("B / H", "10"),
                listOf("C", "100"),
                listOf("D", "1,000"),
                listOf("E", "10,000"),
                listOf("R", "100,000"),
            ),
        )
    }
}


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
    val rows: List<List<String>> = codeValueList
        .chunked(2)
        .map { pair ->
            pair.flatMap { codeValue ->
                listOf(codeValue.code, codeValue.value)
            }
        }
    AppCard {
        AppTable(
            columnTitles = listOf(
                stringResource(id = R.string.info_smd_code_value_col1),
                stringResource(id = R.string.info_smd_code_value_col2),
                stringResource(id = R.string.info_smd_code_value_col1),
                stringResource(id = R.string.info_smd_code_value_col2),
            ),
            rows = rows,
        )
    }
}
