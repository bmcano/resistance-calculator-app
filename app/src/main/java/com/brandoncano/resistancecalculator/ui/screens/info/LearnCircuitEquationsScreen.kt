package com.brandoncano.resistancecalculator.ui.screens.info

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.brandoncano.resistancecalculator.R
import com.brandoncano.resistancecalculator.ui.composables.BottomScreenSpacer
import com.brandoncano.resistancecalculator.ui.theme.ResistorCalculatorTheme
import com.brandoncano.sharedcomponents.composables.AppDivider
import com.brandoncano.sharedcomponents.composables.AppLongScreenPreview
import com.brandoncano.sharedcomponents.composables.AppScreenPreviews
import com.brandoncano.sharedcomponents.composables.AppTopAppBar
import com.brandoncano.sharedcomponents.text.onSurfaceVariant
import com.brandoncano.sharedcomponents.text.textStyleHeadline
import com.brandoncano.sharedcomponents.text.textStyleSubhead

@Composable
fun LearnCircuitEquationsScreen(
    onNavigateBack: () -> Unit,
) {
    Scaffold(
        topBar = {
            AppTopAppBar(
                titleText = stringResource(R.string.info_circuit_title),
                navigationIcon =  Icons.AutoMirrored.Filled.ArrowBack,
                onNavigateBack = onNavigateBack,
            )
        },
        contentWindowInsets = WindowInsets.safeDrawing,
    ) { paddingValues ->
        LearnCircuitEquationsScreenContent(paddingValues)
    }
}

@Composable
private fun LearnCircuitEquationsScreenContent(paddingValues: PaddingValues) {
    val sidePadding = dimensionResource(R.dimen.app_side_padding)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(paddingValues)
            .padding(horizontal = sidePadding),
    ) {
        Spacer(modifier = Modifier.padding(top = 24.dp))
        Text(
            text = stringResource(R.string.info_circuit_series_header),
            modifier = Modifier.padding(bottom = 12.dp),
            style = textStyleHeadline(),
        )
        Text(
            text = stringResource(R.string.info_circuit_series_body1),
            modifier = Modifier.padding(bottom = 24.dp),
            style = textStyleSubhead().onSurfaceVariant(),
        )
        Image(
            painter = painterResource(R.drawable.img_series_equation),
            contentDescription = stringResource(R.string.info_circuit_series_equation_cd),
            modifier = Modifier.fillMaxWidth(),
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurface),
        )
        Text(
            text = stringResource(R.string.info_circuit_series_body2),
            modifier = Modifier.padding(vertical = 24.dp),
            style = textStyleSubhead().onSurfaceVariant(),
        )
        Image(
            painter = painterResource(R.drawable.img_series_special_case),
            contentDescription = stringResource(R.string.info_circuit_series_equation_cd),
            modifier = Modifier.fillMaxWidth(),
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurface),
        )
        AppDivider(modifier = Modifier.padding(vertical = 24.dp))
        Text(
            text = stringResource(R.string.info_circuit_parallel_header),
            modifier = Modifier.padding(bottom = 12.dp),
            style = textStyleHeadline(),
        )
        Text(
            text = stringResource(R.string.info_circuit_parallel_body1),
            modifier = Modifier.padding(bottom = 24.dp),
            style = textStyleSubhead().onSurfaceVariant(),
        )
        Image(
            painter = painterResource(R.drawable.img_parallel_equation_1),
            contentDescription = stringResource(R.string.info_circuit_parallel_equation_cd),
            modifier = Modifier.fillMaxWidth(),
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurface),
        )
        Text(
            text = stringResource(R.string.info_circuit_parallel_body2),
            modifier = Modifier.padding(vertical  = 24.dp),
            style = textStyleSubhead().onSurfaceVariant(),
        )
        Image(
            painter = painterResource(R.drawable.img_parallel_equation_2),
            contentDescription = stringResource(R.string.info_circuit_parallel_equation_cd),
            modifier = Modifier.fillMaxWidth(),
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurface),
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = stringResource(R.string.info_circuit_parallel_body3),
            modifier = Modifier.padding(bottom = 24.dp),
            style = textStyleSubhead().onSurfaceVariant(),
        )
        Image(
            painter = painterResource(R.drawable.img_parallel_special_case),
            contentDescription = stringResource(R.string.info_circuit_parallel_equation_cd),
            modifier = Modifier.fillMaxWidth(),
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurface),
        )
        BottomScreenSpacer()
    }
}

@AppScreenPreviews
@AppLongScreenPreview
@Composable
private fun LearnCircuitEquationsScreenPreview() {
    ResistorCalculatorTheme { LearnCircuitEquationsScreen {} }
}
