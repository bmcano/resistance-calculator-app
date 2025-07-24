package com.brandoncano.resistancecalculator.ui.screens.home

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
import androidx.compose.material.icons.outlined.Colorize
import androidx.compose.material.icons.outlined.LinearScale
import androidx.compose.material.icons.outlined.Memory
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Tune
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.brandoncano.resistancecalculator.R
import com.brandoncano.resistancecalculator.data.ArrowCardButtonPO
import com.brandoncano.resistancecalculator.ui.composables.AboutAppMenuItem
import com.brandoncano.resistancecalculator.ui.composables.AppThemeMenuItem
import com.brandoncano.resistancecalculator.ui.composables.ArrowCardButtonContent
import com.brandoncano.resistancecalculator.ui.composables.BottomScreenSpacer
import com.brandoncano.resistancecalculator.ui.composables.FeedbackMenuItem
import com.brandoncano.resistancecalculator.ui.composables.MenuIconButton
import com.brandoncano.resistancecalculator.ui.composables.m3.M3OutlinedCard
import com.brandoncano.resistancecalculator.ui.composables.m3.M3TopAppBar
import com.brandoncano.resistancecalculator.ui.theme.ResistorCalculatorTheme
import com.brandoncano.sharedcomponents.composables.AppScreenPreviews

@OptIn(ExperimentalMaterial3Api::class) // For TopAppBar
@Composable
fun HomeScreen(
    onFeedbackTapped: () -> Unit,
    onOpenAppThemeDialog: () -> Unit,
    onAboutTapped: () -> Unit,
    onColorToValueTapped: () -> Unit,
    onValueToColorTapped: () -> Unit,
    onSmdTapped: () -> Unit,
    onSeriesCalculatorTapped: () -> Unit,
    onParallelCalculatorTapped: () -> Unit,
    onViewColorCodeIecTapped: () -> Unit,
    onViewPreferredValuesIecTapped: () -> Unit,
    onViewSmdCodeIecTapped: () -> Unit,
    onViewCircuitEquationsTapped: () -> Unit,
    onRateThisAppTapped: () -> Unit,
    onViewOurAppsTapped: () -> Unit,
    onDonateTapped: () -> Unit,
) {
    var showMenu by remember { mutableStateOf(false) }
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            M3TopAppBar(
                titleText = stringResource(R.string.app_name),
                actions = {
                    MenuIconButton { showMenu = true }
                    DropdownMenu(
                        expanded = showMenu,
                        onDismissRequest = { showMenu = false },
                    ) {
                        FeedbackMenuItem {
                            onFeedbackTapped.invoke()
                            showMenu = false
                        }
                        AppThemeMenuItem {
                            onOpenAppThemeDialog.invoke()
                            showMenu = false
                        }
                        AboutAppMenuItem {
                            onAboutTapped.invoke()
                            showMenu = false
                        }
                    }
                },
                scrollBehavior = scrollBehavior,
            )
        },
        contentWindowInsets = WindowInsets.safeDrawing,
    ) { paddingValues ->
        HomeScreenContent(
            paddingValues = paddingValues,
            onColorToValueTapped = onColorToValueTapped,
            onValueToColorTapped = onValueToColorTapped,
            onSmdTapped = onSmdTapped,
            onSeriesCalculatorTapped = onSeriesCalculatorTapped,
            onParallelCalculatorTapped = onParallelCalculatorTapped,
            onViewColorCodeIecTapped = onViewColorCodeIecTapped,
            onViewPreferredValuesIecTapped = onViewPreferredValuesIecTapped,
            onViewSmdCodeIecTapped = onViewSmdCodeIecTapped,
            onViewCircuitEquationsTapped = onViewCircuitEquationsTapped,
            onRateThisAppTapped = onRateThisAppTapped,
            onViewOurAppsTapped = onViewOurAppsTapped,
            onDonateTapped = onDonateTapped,
        )
    }
}

@Composable
private fun HomeScreenContent(
    paddingValues: PaddingValues,
    onColorToValueTapped: () -> Unit,
    onValueToColorTapped: () -> Unit,
    onSmdTapped: () -> Unit,
    onSeriesCalculatorTapped: () -> Unit,
    onParallelCalculatorTapped: () -> Unit,
    onViewColorCodeIecTapped: () -> Unit,
    onViewPreferredValuesIecTapped: () -> Unit,
    onViewSmdCodeIecTapped: () -> Unit,
    onViewCircuitEquationsTapped: () -> Unit,
    onRateThisAppTapped: () -> Unit,
    onViewOurAppsTapped: () -> Unit,
    onDonateTapped: () -> Unit,
) {
    val sidePadding = dimensionResource(R.dimen.app_side_padding)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(paddingValues)
            .padding(horizontal = sidePadding),
        horizontalAlignment = Alignment.Start,
    ) {
        Text(
            text = stringResource(id = R.string.home_calculators_header_text),
            modifier = Modifier.padding(top = 24.dp, bottom = 12.dp),
            style = MaterialTheme.typography.titleMedium,
        )
        M3OutlinedCard(
            modifier = Modifier.fillMaxWidth(),
        ) {
            ArrowCardButtonContent(
                isOutlinedCard = true,
                ArrowCardButtonPO(
                    text = stringResource(id = R.string.home_button_color_to_value),
                    imageVector = Icons.Outlined.Colorize,
                    onClick = onColorToValueTapped,
                ),
                ArrowCardButtonPO(
                    text = stringResource(id = R.string.home_button_value_to_color),
                    imageVector = Icons.Outlined.Search,
                    onClick = onValueToColorTapped,
                ),
                ArrowCardButtonPO(
                    text = stringResource(id = R.string.home_button_smd),
                    imageVector = Icons.Outlined.Memory,
                    onClick = onSmdTapped,
                ),
                ArrowCardButtonPO(
                    text = stringResource(id = R.string.home_button_series_calculator),
                    imageVector = Icons.Outlined.LinearScale,
                    onClick = onSeriesCalculatorTapped,
                ),
                ArrowCardButtonPO(
                    text = stringResource(id = R.string.home_button_parallel_calculator),
                    imageVector = Icons.Outlined.Tune,
                    onClick = onParallelCalculatorTapped,
                ),
            )
        }
        Spacer(modifier = Modifier.height(32.dp))
        InformationCardButtons(
            onViewColorCodeIecTapped = onViewColorCodeIecTapped,
            onViewPreferredValuesIecTapped = onViewPreferredValuesIecTapped,
            onViewSmdCodeIecTapped = onViewSmdCodeIecTapped,
            onViewCircuitEquationsTapped = onViewCircuitEquationsTapped,
        )
        Spacer(modifier = Modifier.height(32.dp))
        OurAppsButtons(
            onRateThisAppTapped = onRateThisAppTapped,
            onViewOurAppsTapped = onViewOurAppsTapped,
            onDonateTapped = onDonateTapped,
        )
        BottomScreenSpacer()
    }
}

@AppScreenPreviews
@Composable
private fun HomePreview() {
    ResistorCalculatorTheme {
        HomeScreen(
            onFeedbackTapped = {},
            onOpenAppThemeDialog = {},
            onAboutTapped = {},
            onColorToValueTapped = {},
            onValueToColorTapped = {},
            onSmdTapped = {},
            onSeriesCalculatorTapped = {},
            onParallelCalculatorTapped = {},
            onViewColorCodeIecTapped = {},
            onViewPreferredValuesIecTapped = {},
            onViewSmdCodeIecTapped = {},
            onViewCircuitEquationsTapped = {},
            onRateThisAppTapped = {},
            onViewOurAppsTapped = {},
            onDonateTapped = {},
        )
    }
}
