package com.brandoncano.resistancecalculator.ui.screens.info

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.brandoncano.library.m3.M3BulletList
import com.brandoncano.resistancecalculator.R
import com.brandoncano.resistancecalculator.constants.ESeries
import com.brandoncano.library.m3.BottomScreenSpacer
import com.brandoncano.library.m3.LongScreenPreview
import com.brandoncano.library.m3.M3Table
import com.brandoncano.library.m3.M3Divider
import com.brandoncano.library.m3.M3OutlinedCard
import com.brandoncano.library.m3.M3Scaffold
import com.brandoncano.library.m3.M3ScreenColumn
import com.brandoncano.library.m3.M3TopAppBar
import com.brandoncano.library.m3.ScreenPreviews
import com.brandoncano.resistancecalculator.ui.theme.ResistorCalculatorTheme
import com.brandoncano.resistancecalculator.ui.theme.gray

/**
 * Note: Information originated from - https://eepower.com/resistor-guide/resistor-standards-and-codes/resistor-values/
 */

@OptIn(ExperimentalMaterial3Api::class) // For TopAppBar
@Composable
fun LearnPreferredValuesScreen(
    onNavigateBack: () -> Unit,
) {
    M3Scaffold(
        topBar = {
            M3TopAppBar(
                titleText = stringResource(R.string.info_values_preferred_values_title),
                navigationIcon =  Icons.AutoMirrored.Filled.ArrowBack,
                onNavigateBack = onNavigateBack,
                scrollBehavior = it,
            )
        },
    )  { paddingValues ->
        LearnPreferredValuesScreenContent(paddingValues)
    }
}

@Composable
private fun LearnPreferredValuesScreenContent(paddingValues: PaddingValues) {
    M3ScreenColumn(
        paddingValues = paddingValues,
    ) {
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = stringResource(R.string.info_values_body1),
            modifier = Modifier.padding(bottom = 16.dp),
            style = MaterialTheme.typography.bodyMedium.gray(),
        )
        Text(
            text = stringResource(R.string.info_values_body2),
            style = MaterialTheme.typography.bodyMedium.gray(),
        )
        Image(
            painter = painterResource(R.drawable.e_series_equation),
            contentDescription = stringResource(R.string.info_content_description_e_series_equation),
            modifier = Modifier
                .padding(vertical = 8.dp)
                .size(128.dp, 56.dp)
                .align(Alignment.CenterHorizontally),
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurfaceVariant)
        )
        Text(
            text = stringResource(R.string.info_values_preferred_values_where),
            modifier = Modifier.padding(bottom = 8.dp),
            style = MaterialTheme.typography.bodyMedium.gray(),
        )
        M3BulletList(
            bulletStrings = listOf(
                stringResource(R.string.info_values_preferred_values_bullet1),
                stringResource(R.string.info_values_preferred_values_bullet2),
                stringResource(R.string.info_values_preferred_values_bullet3),
            ),
            textStyle = MaterialTheme.typography.bodyMedium.gray(),
            bulletVerticalSpace = 4.dp,
        )
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = stringResource(R.string.info_values_preferred_values_tables_headline),
            modifier = Modifier.padding(bottom = 12.dp),
            style = MaterialTheme.typography.titleMedium.gray(),
        )
        Text(
            text = stringResource(R.string.info_values_preferred_values_low_precision),
            modifier = Modifier.padding(bottom = 16.dp),
            style = MaterialTheme.typography.bodyMedium.gray(),
        )
        ESeriesTable(stringResource(R.string.info_values_e6_header), ESeries.E6)
        M3Divider(modifier = Modifier.padding(vertical = 16.dp))
        Text(
            text = stringResource(R.string.info_values_preferred_values_medium_precision),
            modifier = Modifier.padding(bottom = 16.dp),
            style = MaterialTheme.typography.bodyMedium.gray(),
        )
        ESeriesTable(stringResource(R.string.info_values_e12_header), ESeries.E12)
        Spacer(modifier = Modifier.height(24.dp))
        ESeriesTable(stringResource(R.string.info_values_e24_header), ESeries.E24)
        M3Divider(modifier = Modifier.padding(vertical = 16.dp))
        Text(
            text = stringResource(R.string.info_values_preferred_values_high_precision),
            modifier = Modifier.padding(bottom = 16.dp),
            style = MaterialTheme.typography.bodyMedium.gray(),
        )
        ESeriesTable(stringResource(R.string.info_values_e48_header), ESeries.E48)
        Spacer(modifier = Modifier.height(24.dp))
        ESeriesTable(stringResource(R.string.info_values_e96_header), ESeries.E96)
        Spacer(modifier = Modifier.height(24.dp))
        ESeriesTable(stringResource(R.string.info_values_e192_header), ESeries.E192)
        DisclaimerText()
        BottomScreenSpacer()
    }
}

@Composable
private fun ESeriesTable(seriesName: String, values: List<Int>) {
    val rows: List<List<String>> = values
        .chunked(6)
        .map { chunk -> chunk.map { it.toString() } }
    M3OutlinedCard(
        modifier = Modifier.fillMaxWidth(),
    ) {
        M3Table(
            columnTitles = listOf(seriesName),
            rows = rows,
        )
    }
}

@ScreenPreviews
@LongScreenPreview
@Composable
private fun LearnPreferredValuesScreenPreview() {
    ResistorCalculatorTheme { LearnPreferredValuesScreen {} }
}
