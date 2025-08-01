package com.brandoncano.inductancecalculator.ui.screens.info

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.brandoncano.inductancecalculator.R
import com.brandoncano.inductancecalculator.data.SmdTolerance
import com.brandoncano.inductancecalculator.ui.theme.InductorCalculatorTheme
import com.brandoncano.library.m3.BottomScreenSpacer
import com.brandoncano.library.m3.LongScreenPreview
import com.brandoncano.library.m3.M3CallOutCard
import com.brandoncano.library.m3.M3LazyColumn
import com.brandoncano.library.m3.M3OutlinedCard
import com.brandoncano.library.m3.M3Scaffold
import com.brandoncano.library.m3.M3Table
import com.brandoncano.library.m3.M3TopAppBar
import com.brandoncano.library.m3.ScreenPreviews
import com.brandoncano.library.m3.elevatedCardColor
import com.brandoncano.library.theme.gray

@OptIn(ExperimentalMaterial3Api::class) // For TopAppBar
@Composable
fun LearnSmdCodesScreen(
    onNavigateBack: () -> Unit,
) {
    M3Scaffold(
        topBar = {
            M3TopAppBar(
                titleText = stringResource(R.string.info_smd_title),
                navigationIcon =  Icons.Filled.Close,
                onNavigateBack = onNavigateBack,
                scrollBehavior = it,
            )
        },
    ) { paddingValues ->
        LearnSmdCodesScreenContent(paddingValues)
    }
}

@Composable
private fun LearnSmdCodesScreenContent(paddingValues: PaddingValues) {
    M3LazyColumn(
        paddingValues = paddingValues,
    ) {
        item {
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = stringResource(R.string.smd_info_intro_body),
                modifier = Modifier.padding(bottom = 32.dp),
                style = MaterialTheme.typography.bodyMedium.gray(),
            )
        }
        item {
            Text(
                text = stringResource(R.string.smd_info_code_title),
                modifier = Modifier.padding(bottom = 12.dp),
                style = MaterialTheme.typography.titleMedium,
            )
            Text(
                text = stringResource(R.string.smd_info_code_body_1),
                modifier = Modifier.padding(bottom = 12.dp),
                style = MaterialTheme.typography.bodyMedium.gray(),
            )
            Text(
                text = stringResource(R.string.smd_info_code_body_2),
                modifier = Modifier.padding(bottom = 12.dp),
                style = MaterialTheme.typography.bodyMedium.gray(),
            )
            M3CallOutCard(stringResource(R.string.smd_info_three_digit_formula), color = elevatedCardColor())
            Text(
                text = stringResource(R.string.smd_info_three_digit_example_label),
                modifier = Modifier.padding(vertical = 12.dp),
                style = MaterialTheme.typography.bodyMedium.gray(),
            )
            M3CallOutCard(stringResource(R.string.smd_info_three_digit_example), color = elevatedCardColor())
        }

        // TODO: something about using 'R' in the codes.
        item {
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = stringResource(R.string.smd_info_tolerance_title),
                modifier = Modifier.padding(bottom = 12.dp),
                style =MaterialTheme.typography.titleMedium,
            )
            Text(
                text = stringResource(R.string.smd_info_tolerance_body),
                modifier = Modifier.padding(bottom = 16.dp),
                style = MaterialTheme.typography.bodyMedium.gray(),
            )
        }
        item {
            SmdToleranceTable()
        }
        item {
            DisclaimerText()
            BottomScreenSpacer()
        }
    }
}

@Composable
private fun SmdToleranceTable() {
    val tableData = SmdTolerance.getTolerancePairs()
    M3OutlinedCard {
        M3Table(
            columnTitles = listOf(
                stringResource(R.string.smd_info_tolerance_symbol),
                stringResource(R.string.smd_info_tolerance_value),
            ),
            rows = tableData,
        )
    }
}

@ScreenPreviews
@LongScreenPreview
@Composable
private fun LearnSmdCodesScreenPreview() {
    InductorCalculatorTheme { LearnSmdCodesScreen {} }
}
