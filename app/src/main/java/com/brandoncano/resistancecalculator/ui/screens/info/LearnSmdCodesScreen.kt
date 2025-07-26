package com.brandoncano.resistancecalculator.ui.screens.info

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.brandoncano.resistancecalculator.R
import com.brandoncano.resistancecalculator.constants.Lists
import com.brandoncano.resistancecalculator.ui.composables.BottomScreenSpacer
import com.brandoncano.resistancecalculator.ui.composables.M3Table
import com.brandoncano.resistancecalculator.ui.composables.m3.M3CallOutCard
import com.brandoncano.resistancecalculator.ui.composables.m3.M3OutlinedCard
import com.brandoncano.resistancecalculator.ui.composables.m3.M3Scaffold
import com.brandoncano.resistancecalculator.ui.composables.m3.M3ScreenColumn
import com.brandoncano.resistancecalculator.ui.composables.m3.M3TopAppBar
import com.brandoncano.resistancecalculator.ui.composables.m3.elevatedCardColor
import com.brandoncano.resistancecalculator.ui.theme.ResistorCalculatorTheme
import com.brandoncano.resistancecalculator.ui.theme.gray
import com.brandoncano.sharedcomponents.composables.AppLongScreenPreview
import com.brandoncano.sharedcomponents.composables.AppScreenPreviews

/**
 * Note: Information originated from - https://eepower.com/resistor-guide/resistor-standards-and-codes/resistor-smd-code/
 */

@OptIn(ExperimentalMaterial3Api::class) // For TopAppBar
@Composable
fun LearnSmdCodesScreen(
    onNavigateBack: () -> Unit,
) {
    M3Scaffold(
        topBar = {
            M3TopAppBar(
                titleText = stringResource(R.string.info_smd_title),
                navigationIcon =  Icons.AutoMirrored.Filled.ArrowBack,
                onNavigateBack = onNavigateBack,
                scrollBehavior = it,
            )
        },
    )  { paddingValues ->
        LearnSmdCodesScreenContent(paddingValues)
    }
}

@Composable
private fun LearnSmdCodesScreenContent(paddingValues: PaddingValues) {
    M3ScreenColumn(
        paddingValues = paddingValues,
    ) {
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = stringResource(R.string.info_smd_body1),
            modifier = Modifier.padding(bottom = 16.dp),
            style = MaterialTheme.typography.bodyMedium.gray(),
        )
        Text(
            text = stringResource(R.string.info_smd_body2),
            modifier = Modifier.padding(bottom = 32.dp),
            style = MaterialTheme.typography.bodyMedium.gray(),
        )
        CodeSystemSectionContent(
            headlineRes = R.string.info_smd_three_code_headline,
            bodyRes = R.string.info_smd_three_code_body,
            formulaRes = R.string.info_smd_three_digit_formula,
            exampleLabelRes = R.string.info_smd_three_digit_example_label,
            exampleRes = R.string.info_smd_three_digit_example,
        )
        Spacer(modifier = Modifier.height(32.dp))
        CodeSystemSectionContent(
            headlineRes = R.string.info_smd_four_code_headline,
            bodyRes = R.string.info_smd_four_code_body,
            formulaRes = R.string.info_smd_four_digit_formula,
            exampleLabelRes = R.string.info_smd_four_digit_example_label,
            exampleRes = R.string.info_smd_four_digit_example,
        )
        Text(
            text = stringResource(R.string.info_smd_four_digit_body2),
            modifier = Modifier.padding(top = 16.dp),
            style = MaterialTheme.typography.bodyMedium.gray(),
        )
        Spacer(modifier = Modifier.height(32.dp))
        CodeSystemSectionContent(
            headlineRes = R.string.info_smd_eia_headline,
            bodyRes = R.string.info_smd_eia_body,
            formulaRes = R.string.info_smd_eia_formula,
            exampleLabelRes = R.string.info_smd_eia_example_label,
            exampleRes = R.string.info_smd_eia_example,
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = stringResource(R.string.info_smd_multipliers_headline),
            modifier = Modifier.padding(bottom = 12.dp),
            style = MaterialTheme.typography.titleMedium,
        )
        Text(
            text = stringResource(R.string.info_smd_multipliers_body),
            modifier = Modifier.padding(bottom = 16.dp),
            style = MaterialTheme.typography.bodyMedium.gray(),
        )
        MultiplierTable()
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = stringResource(R.string.info_smd_code_value_headline),
            modifier = Modifier.padding(bottom = 12.dp),
            style = MaterialTheme.typography.titleMedium,
        )
        Text(
            text = stringResource(R.string.info_smd_code_value_body),
            modifier = Modifier.padding(bottom = 16.dp),
            style = MaterialTheme.typography.bodyMedium.gray(),
        )
        CodeLookupTable()
        DisclaimerText()
        BottomScreenSpacer()
    }
}

@Composable
private fun CodeSystemSectionContent(
    headlineRes: Int,
    bodyRes: Int,
    formulaRes: Int,
    exampleLabelRes: Int,
    exampleRes: Int,
) {
    Text(
        text = stringResource(id = headlineRes),
        modifier = Modifier.padding(bottom = 12.dp),
        style = MaterialTheme.typography.titleMedium,
    )
    Text(
        text = stringResource(id = bodyRes),
        modifier = Modifier.padding(bottom = 12.dp),
        style = MaterialTheme.typography.bodyMedium.gray(),
    )
    M3CallOutCard(stringResource(id = formulaRes), color = elevatedCardColor())
    Text(
        text = stringResource(id = exampleLabelRes),
        modifier = Modifier.padding(vertical = 12.dp),
        style = MaterialTheme.typography.bodyMedium.gray(),
    )
    M3CallOutCard(stringResource(id = exampleRes), color = elevatedCardColor())
}

@Composable
private fun MultiplierTable() {
    M3OutlinedCard {
        M3Table(
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

@Composable
private fun CodeLookupTable() {
    val rows: List<List<String>> = Lists.CODE_LOOKUP_TABLE
        .chunked(2)
        .map { pair ->
            pair.flatMap { codeValueItemPO ->
                listOf(codeValueItemPO.code, codeValueItemPO.value)
            }
        }
    M3OutlinedCard {
        M3Table(
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

@AppScreenPreviews
@AppLongScreenPreview
@Composable
private fun LearnSmdCodesScreenPreview() {
    ResistorCalculatorTheme { LearnSmdCodesScreen {} }
}
