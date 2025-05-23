package com.brandoncano.resistancecalculator.ui.screens.info

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.brandoncano.resistancecalculator.R
import com.brandoncano.resistancecalculator.constants.ESeries
import com.brandoncano.resistancecalculator.ui.composables.BottomScreenSpacer
import com.brandoncano.resistancecalculator.ui.theme.ResistorCalculatorTheme
import com.brandoncano.sharedcomponents.composables.AppBulletList
import com.brandoncano.sharedcomponents.composables.AppDivider
import com.brandoncano.sharedcomponents.composables.AppLongScreenPreview
import com.brandoncano.sharedcomponents.composables.AppScreenPreviews
import com.brandoncano.sharedcomponents.composables.AppTopAppBar
import com.brandoncano.sharedcomponents.text.onSurfaceVariant
import com.brandoncano.sharedcomponents.text.textStyleBody
import com.brandoncano.sharedcomponents.text.textStyleHeadline
import com.brandoncano.sharedcomponents.text.textStyleSubhead

/**
 * Note: Information originated from - https://eepower.com/resistor-guide/resistor-standards-and-codes/resistor-values/
 */

@Composable
fun LearnPreferredValuesScreen(
    onNavigateBack: () -> Unit,
) {
    Scaffold(
        topBar = {
            AppTopAppBar(
                titleText = stringResource(R.string.info_values_preferred_values_title),
                navigationIcon =  Icons.AutoMirrored.Filled.ArrowBack,
                onNavigateBack = onNavigateBack,
            )
        },
        contentWindowInsets = WindowInsets.safeDrawing,
    ) { paddingValues ->
        LearnPreferredValuesScreenContent(paddingValues)
    }
}

@Composable
private fun LearnPreferredValuesScreenContent(paddingValues: PaddingValues) {
    val sidePadding = dimensionResource(R.dimen.app_side_padding)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(paddingValues)
            .padding(horizontal = sidePadding),
    ) {
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = stringResource(R.string.info_values_body1),
            modifier = Modifier.padding(bottom = 16.dp),
            style = textStyleBody().onSurfaceVariant(),
        )
        Text(
            text = stringResource(R.string.info_values_body2),
            style = textStyleBody().onSurfaceVariant(),
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
            style = textStyleBody().onSurfaceVariant(),
        )
        AppBulletList(
            bulletStrings = listOf(
                stringResource(R.string.info_values_preferred_values_bullet1),
                stringResource(R.string.info_values_preferred_values_bullet2),
                stringResource(R.string.info_values_preferred_values_bullet3),
            ),
            textStyle = textStyleBody().onSurfaceVariant(),
            bulletVerticalSpace = 4.dp,
        )
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = stringResource(R.string.info_values_preferred_values_tables_headline),
            modifier = Modifier.padding(bottom = 12.dp),
            style = textStyleHeadline(),
        )
        Text(
            text = stringResource(R.string.info_values_preferred_values_low_precision),
            modifier = Modifier.padding(bottom = 16.dp),
            style = textStyleSubhead().onSurfaceVariant(),
        )
        ESeriesTable(stringResource(R.string.info_values_e6_header), ESeries.E6)
        AppDivider(modifier = Modifier.padding(vertical = 16.dp))
        Text(
            text = stringResource(R.string.info_values_preferred_values_medium_precision),
            modifier = Modifier.padding(bottom = 16.dp),
            style = textStyleSubhead().onSurfaceVariant(),
        )
        ESeriesTable(stringResource(R.string.info_values_e12_header), ESeries.E12)
        Spacer(modifier = Modifier.height(24.dp))
        ESeriesTable(stringResource(R.string.info_values_e24_header), ESeries.E24)
        AppDivider(modifier = Modifier.padding(vertical = 16.dp))
        Text(
            text = stringResource(R.string.info_values_preferred_values_high_precision),
            modifier = Modifier.padding(bottom = 16.dp),
            style = textStyleSubhead().onSurfaceVariant(),
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

@AppScreenPreviews
@AppLongScreenPreview
@Composable
private fun LearnPreferredValuesScreenPreview() {
    ResistorCalculatorTheme { LearnPreferredValuesScreen {} }
}
