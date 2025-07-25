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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.brandoncano.resistancecalculator.R
import com.brandoncano.resistancecalculator.ui.composables.BottomScreenSpacer
import com.brandoncano.resistancecalculator.ui.composables.m3.M3Divider
import com.brandoncano.resistancecalculator.ui.composables.m3.M3TopAppBar
import com.brandoncano.resistancecalculator.ui.theme.ResistorCalculatorTheme
import com.brandoncano.resistancecalculator.ui.theme.gray
import com.brandoncano.sharedcomponents.composables.AppLongScreenPreview
import com.brandoncano.sharedcomponents.composables.AppScreenPreviews

@OptIn(ExperimentalMaterial3Api::class) // For TopAppBar
@Composable
fun LearnCircuitEquationsScreen(
    onNavigateBack: () -> Unit,
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            M3TopAppBar(
                titleText = stringResource(R.string.info_circuit_title),
                navigationIcon =  Icons.AutoMirrored.Filled.ArrowBack,
                onNavigateBack = onNavigateBack,
                scrollBehavior = scrollBehavior,
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
            style = MaterialTheme.typography.titleMedium,
        )
        Text(
            text = stringResource(R.string.info_circuit_series_body1),
            modifier = Modifier.padding(bottom = 24.dp),
            style = MaterialTheme.typography.bodyMedium.gray(),
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
            style = MaterialTheme.typography.bodyMedium.gray(),
        )
        Image(
            painter = painterResource(R.drawable.img_series_special_case),
            contentDescription = stringResource(R.string.info_circuit_series_equation_cd),
            modifier = Modifier.fillMaxWidth(),
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurface),
        )
        M3Divider(modifier = Modifier.padding(vertical = 24.dp))
        Text(
            text = stringResource(R.string.info_circuit_parallel_header),
            modifier = Modifier.padding(bottom = 12.dp),
            style = MaterialTheme.typography.titleMedium,
        )
        Text(
            text = stringResource(R.string.info_circuit_parallel_body1),
            modifier = Modifier.padding(bottom = 24.dp),
            style = MaterialTheme.typography.bodyMedium.gray(),
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
            style = MaterialTheme.typography.bodyMedium.gray(),
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
            style = MaterialTheme.typography.bodyMedium.gray(),
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
