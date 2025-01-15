package com.brandoncano.resistancecalculator.ui.screens.home

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Colorize
import androidx.compose.material.icons.outlined.LinearScale
import androidx.compose.material.icons.outlined.Memory
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Tune
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.brandoncano.resistancecalculator.R
import com.brandoncano.resistancecalculator.constants.Links
import com.brandoncano.resistancecalculator.ui.theme.ResistorCalculatorTheme
import com.brandoncano.sharedcomponents.composables.AboutAppMenuItem
import com.brandoncano.sharedcomponents.composables.AppArrowCardButton
import com.brandoncano.sharedcomponents.composables.AppHomeTopAppBar
import com.brandoncano.sharedcomponents.composables.AppScreenPreviews
import com.brandoncano.sharedcomponents.composables.AppThemeMenuItem
import com.brandoncano.sharedcomponents.composables.FeedbackMenuItem
import com.brandoncano.sharedcomponents.data.ArrowCardButtonContents
import com.brandoncano.sharedcomponents.screen.OurAppsButtons
import com.brandoncano.sharedcomponents.text.textStyleHeadline

@Composable
fun HomeScreen(
    openMenu: MutableState<Boolean>,
    onOpenThemeDialog: () -> Unit,
    onAboutTapped: () -> Unit,
    onColorToValueTapped: () -> Unit,
    onValueToColorTapped: () -> Unit,
    onSmdTapped: () -> Unit,
    onSeriesCalculatorTapped: () -> Unit,
    onParallelCalculatorTapped: () -> Unit,
    onRateThisAppTapped: () -> Unit,
    onViewOurAppsTapped: () -> Unit,
    onDonateTapped: () -> Unit,
) {
    Scaffold(
        topBar = {
            AppHomeTopAppBar(
                titleText = stringResource(R.string.app_name),
                interactionSource = remember { MutableInteractionSource() },
                showMenu = openMenu,
                appIcon = painterResource(R.drawable.img_app_icon),
                content = {
                    FeedbackMenuItem(Links.APP_NAME, openMenu)
                    AppThemeMenuItem(openMenu, onOpenThemeDialog)
                    AboutAppMenuItem(onAboutTapped)
                }
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
            modifier = Modifier.padding(top = 16.dp, bottom = 12.dp),
            style = textStyleHeadline(),
        )
        AppArrowCardButton(
            ArrowCardButtonContents(
                imageVector = Icons.Outlined.Colorize,
                text = stringResource(id = R.string.home_button_color_to_value),
                onClick = onColorToValueTapped
            ),
            ArrowCardButtonContents(
                imageVector = Icons.Outlined.Search,
                text = stringResource(id = R.string.home_button_value_to_color),
                onClick = onValueToColorTapped
            ),
            ArrowCardButtonContents(
                imageVector = Icons.Outlined.Memory,
                text = stringResource(id = R.string.home_button_smd),
                onClick = onSmdTapped
            ),
            ArrowCardButtonContents(
                imageVector = Icons.Outlined.LinearScale,
                text = stringResource(id = R.string.home_button_series_calculator),
                onClick = onSeriesCalculatorTapped
            ),
            ArrowCardButtonContents(
                imageVector = Icons.Outlined.Tune,
                text = stringResource(id = R.string.home_button_parallel_calculator),
                onClick = onParallelCalculatorTapped
            ),
        )
        Spacer(modifier = Modifier.height(32.dp))
        OurAppsButtons(
            onRateThisAppTapped = onRateThisAppTapped,
            onViewOurAppsTapped = onViewOurAppsTapped,
            onDonateTapped = onDonateTapped,
        )
        Spacer(modifier = Modifier.height(48.dp))
    }
}

@AppScreenPreviews
@Composable
private fun HomePreview() {
    ResistorCalculatorTheme {
        HomeScreen(
            openMenu = remember { mutableStateOf(false) },
            onOpenThemeDialog = {},
            onAboutTapped = {},
            onColorToValueTapped = {},
            onValueToColorTapped = {},
            onSmdTapped = {},
            onSeriesCalculatorTapped = {},
            onParallelCalculatorTapped = {},
            onRateThisAppTapped = {},
            onViewOurAppsTapped = {},
            onDonateTapped = {},
        )
    }
}
