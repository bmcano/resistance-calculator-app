package com.brandoncano.resistancecalculator.ui.screens.info

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.brandoncano.resistancecalculator.R
import com.brandoncano.resistancecalculator.ui.theme.ResistorCalculatorTheme
import com.brandoncano.sharedcomponents.composables.AppLongScreenPreview
import com.brandoncano.sharedcomponents.composables.AppScreenPreviews
import com.brandoncano.sharedcomponents.composables.AppTopAppBar
import com.brandoncano.sharedcomponents.text.onSurfaceVariant
import com.brandoncano.sharedcomponents.text.textStyleBody
import com.brandoncano.sharedcomponents.text.textStyleHeadline
import com.brandoncano.sharedcomponents.text.textStyleSubhead

/**
 * Note: Information originated from - https://eepower.com/resistor-guide/resistor-standards-and-codes/resistor-smd-code/
 */

@Composable
fun LearnSmdCodesScreen(
    onNavigateBack: () -> Unit,
) {
    Scaffold(
        topBar = {
            AppTopAppBar(
                titleText = stringResource(R.string.info_smd_title),
                navigationIcon =  Icons.AutoMirrored.Filled.ArrowBack,
                onNavigateBack = onNavigateBack,
            )
        },
        contentWindowInsets = WindowInsets.safeDrawing,
    ) { paddingValues ->
        LearnSmdCodesScreenContent(paddingValues)
    }
}

@Composable
private fun LearnSmdCodesScreenContent(paddingValues: PaddingValues) {
    val sidePadding = dimensionResource(R.dimen.app_side_padding)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(paddingValues)
            .padding(horizontal = sidePadding),
        horizontalAlignment = Alignment.Start,
    ) {
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = stringResource(R.string.info_smd_body1),
            modifier = Modifier.padding(bottom = 16.dp),
            style = textStyleBody().onSurfaceVariant(),
        )
        Text(
            text = stringResource(R.string.info_smd_body2),
            modifier = Modifier.padding(bottom = 32.dp),
            style = textStyleBody().onSurfaceVariant(),
        )
        CodeInfoSection(
            headlineRes = R.string.info_smd_three_code_headline,
            bodyRes = R.string.info_smd_three_code_body,
            formulaRes = R.string.info_smd_three_digit_formula,
            exampleLabelRes = R.string.info_smd_three_digit_example_label,
            exampleRes = R.string.info_smd_three_digit_example,
        )
        Spacer(modifier = Modifier.height(32.dp))
        CodeInfoSection(
            headlineRes = R.string.info_smd_four_code_headline,
            bodyRes = R.string.info_smd_four_code_body,
            formulaRes = R.string.info_smd_four_digit_formula,
            exampleLabelRes = R.string.info_smd_four_digit_example_label,
            exampleRes = R.string.info_smd_four_digit_example,
        )
        Text(
            text = stringResource(R.string.info_smd_four_digit_body2),
            modifier = Modifier.padding(top = 16.dp),
            style = textStyleSubhead().onSurfaceVariant(),
        )
        Spacer(modifier = Modifier.height(32.dp))
        CodeInfoSection(
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
            style = textStyleHeadline(),
        )
        Text(
            text = stringResource(R.string.info_smd_multipliers_body),
            modifier = Modifier.padding(bottom = 16.dp),
            style = textStyleSubhead().onSurfaceVariant(),
        )
        MultiplierTable()
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = stringResource(R.string.info_smd_code_value_headline),
            modifier = Modifier.padding(bottom = 12.dp),
            style = textStyleHeadline(),
        )
        Text(
            text = stringResource(R.string.info_smd_code_value_body),
            modifier = Modifier.padding(bottom = 16.dp),
            style = textStyleSubhead().onSurfaceVariant(),
        )
        CodeValueTable()
        DisclaimerText()
        BottomScreenSpacer()
    }
}

@AppScreenPreviews
@AppLongScreenPreview
@Composable
private fun LearnSmdCodesScreenPreview() {
    ResistorCalculatorTheme { LearnSmdCodesScreen {} }
}
