package com.brandoncano.resistancecalculator.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Apps
import androidx.compose.material.icons.outlined.Colorize
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Functions
import androidx.compose.material.icons.outlined.Grade
import androidx.compose.material.icons.outlined.LinearScale
import androidx.compose.material.icons.outlined.Memory
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Tune
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.brandoncano.resistancecalculator.R
import com.brandoncano.library.data.ArrowCardButtonPO
import com.brandoncano.resistancecalculator.ui.composables.AboutAppMenuItem
import com.brandoncano.resistancecalculator.ui.composables.AppThemeMenuItem
import com.brandoncano.resistancecalculator.ui.composables.FeedbackMenuItem
import com.brandoncano.resistancecalculator.ui.composables.MenuIconButton
import com.brandoncano.library.m3.BottomScreenSpacer
import com.brandoncano.library.m3.LongScreenPreview
import com.brandoncano.library.m3.M3ArrowButtonCardContent
import com.brandoncano.library.m3.M3ElevatedCard
import com.brandoncano.library.m3.M3Scaffold
import com.brandoncano.library.m3.M3ScreenColumn
import com.brandoncano.library.m3.M3TopAppBar
import com.brandoncano.library.m3.ScreenPreviews
import com.brandoncano.library.m3.elevatedCardColor
import com.brandoncano.resistancecalculator.ui.theme.ResistorCalculatorTheme

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
    M3Scaffold(
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
                scrollBehavior = it,
            )
        },
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
    M3ScreenColumn(
        paddingValues = paddingValues,
    ) {
        Text(
            text = stringResource(id = R.string.home_calculators_header_text),
            modifier = Modifier.padding(top = 24.dp, bottom = 12.dp),
            style = MaterialTheme.typography.titleMedium,
        )
        M3ElevatedCard(
            modifier = Modifier.fillMaxWidth(),
        ) {
            M3ArrowButtonCardContent(
                color = elevatedCardColor(),
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

@Composable
fun InformationCardButtons(
    onViewColorCodeIecTapped: () -> Unit,
    onViewPreferredValuesIecTapped: () -> Unit,
    onViewSmdCodeIecTapped: () -> Unit,
    onViewCircuitEquationsTapped: () -> Unit,
) {
    Column {
        Text(
            text = stringResource(id = R.string.home_info_header),
            modifier = Modifier
                .padding(bottom = 12.dp)
                .align(Alignment.Start),
            style = MaterialTheme.typography.titleMedium,
        )
        M3ElevatedCard(
            modifier = Modifier.fillMaxWidth(),
            defaultElevation = 0.dp,
        ) {
            M3ArrowButtonCardContent(
                color = elevatedCardColor(),
                ArrowCardButtonPO(
                    imageVector = Icons.Outlined.Colorize,
                    text = stringResource(id = R.string.home_standard_iec_button),
                    onClick = onViewColorCodeIecTapped,
                ),
                ArrowCardButtonPO(
                    imageVector = Icons.Outlined.Search,
                    text = stringResource(id = R.string.home_preferred_values_iec_button),
                    onClick = onViewPreferredValuesIecTapped,
                ),
                ArrowCardButtonPO(
                    imageVector = Icons.Outlined.Memory,
                    text = stringResource(id = R.string.home_smd_iec_button),
                    onClick = onViewSmdCodeIecTapped,
                ),
                ArrowCardButtonPO(
                    imageVector = Icons.Outlined.Functions,
                    text = stringResource(id = R.string.home_circuit_equations_button),
                    onClick = onViewCircuitEquationsTapped,
                ),
            )
        }
    }
}

@Composable
fun OurAppsButtons(
    onRateThisAppTapped: () -> Unit,
    onViewOurAppsTapped: () -> Unit,
    onDonateTapped: () -> Unit,
) {
    Column {
        Text(
            text = stringResource(id = R.string.home_support_us_header),
            modifier = Modifier.align(Alignment.Start),
            style = MaterialTheme.typography.titleMedium,
        )
        Spacer(modifier = Modifier.height(12.dp))
        M3ElevatedCard(
            modifier = Modifier.fillMaxWidth(),
            defaultElevation = 0.dp,
        ) {
            M3ArrowButtonCardContent(
                color = elevatedCardColor(),
                ArrowCardButtonPO(
                    imageVector = Icons.Outlined.Grade,
                    text = stringResource(id = R.string.home_rate_us),
                    onClick = onRateThisAppTapped,
                ),
                ArrowCardButtonPO(
                    imageVector = Icons.Outlined.Apps,
                    text = stringResource(id = R.string.home_view_our_apps),
                    onClick = onViewOurAppsTapped,
                ),
                ArrowCardButtonPO(
                    imageVector = Icons.Outlined.FavoriteBorder,
                    text = stringResource(R.string.home_donate),
                    onClick = onDonateTapped,
                ),
            )
        }
    }
}

@ScreenPreviews
@LongScreenPreview
@Composable
private fun HomeScreenPreview() {
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
