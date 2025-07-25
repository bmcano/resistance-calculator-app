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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.brandoncano.resistancecalculator.R
import com.brandoncano.resistancecalculator.to.ResistorCtv
import com.brandoncano.resistancecalculator.ui.composables.BottomScreenSpacer
import com.brandoncano.resistancecalculator.ui.composables.m3.M3TopAppBar
import com.brandoncano.resistancecalculator.ui.theme.ResistorCalculatorTheme
import com.brandoncano.resistancecalculator.ui.theme.gray
import com.brandoncano.sharedcomponents.composables.AppLongScreenPreview
import com.brandoncano.sharedcomponents.composables.AppScreenPreviews
import com.brandoncano.sharedcomponents.text.onSurfaceVariant
import com.brandoncano.sharedcomponents.text.textStyleBody
import com.brandoncano.resistancecalculator.constants.Colors as C

/**
 * Note: Information originated from - https://eepower.com/resistor-guide/resistor-standards-and-codes/resistor-color-code/#
 */

@OptIn(ExperimentalMaterial3Api::class) // For TopAppBar
@Composable
fun LearnColorCodesScreen(
    onNavigateBack: () -> Unit,
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            M3TopAppBar(
                titleText = stringResource(R.string.info_color_title),
                navigationIcon =  Icons.AutoMirrored.Filled.ArrowBack,
                onNavigateBack = onNavigateBack,
                scrollBehavior = scrollBehavior,
            )
        },
        contentWindowInsets = WindowInsets.safeDrawing,
    )  { paddingValues ->
        LearnColorCodesScreenContent(paddingValues)
    }
}

@Composable
private fun LearnColorCodesScreenContent(paddingValues: PaddingValues) {
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
        BandSection(
            headlineRes = R.string.info_color_three_band_headline,
            bodyRes = R.string.info_color_three_band_body,
            codeRes = R.string.info_color_three_band_code,
            descriptionRes = R.string.info_color_three_band_description,
            resistor = ResistorCtv(C.RED, C.VIOLET, "", C.ORANGE, "", "", 0),
        )
        Spacer(modifier = Modifier.height(32.dp))
        BandSection(
            headlineRes = R.string.info_color_four_band_headline,
            bodyRes = R.string.info_color_four_band_body,
            codeRes = R.string.info_color_four_band_code,
            descriptionRes = R.string.info_color_four_band_description,
            resistor = ResistorCtv(C.YELLOW, C.VIOLET, "", C.RED, C.GOLD, "", 1),
        )
        Spacer(modifier = Modifier.height(32.dp))
        BandSection(
            headlineRes = R.string.info_color_five_band_headline,
            bodyRes = R.string.info_color_five_band_body,
            codeRes = R.string.info_color_five_band_code,
            descriptionRes = R.string.info_color_five_band_description,
            resistor = ResistorCtv(C.BROWN, C.GREEN, C.BLACK, C.ORANGE, C.BROWN, "", 2),
        )
        Spacer(modifier = Modifier.height(32.dp))
        BandSection(
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

@AppScreenPreviews
@AppLongScreenPreview
@Composable
private fun LearnColorCodesScreenPreview() {
    ResistorCalculatorTheme { LearnColorCodesScreen {} }
}
