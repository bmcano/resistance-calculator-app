package com.brandoncano.resistancecalculator.ui.screens.vtc

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
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
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
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
import com.brandoncano.resistancecalculator.constants.DropdownLists
import com.brandoncano.resistancecalculator.data.ESeriesCardContent
import com.brandoncano.resistancecalculator.to.ResistorVtc
import com.brandoncano.resistancecalculator.ui.composables.AboutAppMenuItem
import com.brandoncano.resistancecalculator.ui.composables.AppTextField
import com.brandoncano.resistancecalculator.ui.composables.BottomScreenSpacer
import com.brandoncano.resistancecalculator.ui.composables.ClearSelectionsMenuItem
import com.brandoncano.resistancecalculator.ui.composables.FeedbackMenuItem
import com.brandoncano.resistancecalculator.ui.composables.ImageTextDropDownMenu
import com.brandoncano.resistancecalculator.ui.composables.MenuIconButton
import com.brandoncano.resistancecalculator.ui.composables.ShareImageMenuItem
import com.brandoncano.resistancecalculator.ui.composables.ShareTextMenuItem
import com.brandoncano.resistancecalculator.ui.composables.TextDropDownMenu
import com.brandoncano.resistancecalculator.ui.composables.m3.M3FilledButton
import com.brandoncano.resistancecalculator.ui.composables.m3.M3TopAppBar
import com.brandoncano.resistancecalculator.ui.screens.ctv.navigationBarOptions
import com.brandoncano.resistancecalculator.ui.theme.ResistorCalculatorTheme
import com.brandoncano.resistancecalculator.util.resistor.shareableText
import com.brandoncano.sharedcomponents.composables.AppNavigationBar
import com.brandoncano.sharedcomponents.composables.AppScreenPreviews


@OptIn(ExperimentalMaterial3Api::class) // For TopAppBar
@Composable
fun ValueToColorScreen(
    resistor: ResistorVtc,
    isError: Boolean,
    eSeriesCardContent: ESeriesCardContent,
    onNavigateBack: () -> Unit,
    onShareImageTapped: (@Composable (() -> Unit)) -> Unit,
    onShareTextTapped: (String) -> Unit,
    onClearSelectionsTapped: () -> Unit,
    onFeedbackTapped: () -> Unit,
    onAboutTapped: () -> Unit,
    onValueChanged: (String) -> Unit,
    onOptionSelected: (String, String, String) -> Unit,
    onNavBarSelectionChanged: (Int) -> Unit,
    onValidateResistanceTapped: () -> Unit,
    onUseValueTapped: () -> String,
    onLearnMoreTapped: () -> Unit,
) {
    var showMenu by remember { mutableStateOf(false) }
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            M3TopAppBar(
                titleText = stringResource(R.string.vtc_title),
                navigationIcon = Icons.Filled.Close,
                onNavigateBack = onNavigateBack,
                actions = {
                    MenuIconButton { showMenu = true }
                    DropdownMenu(
                        expanded = showMenu,
                        onDismissRequest = { showMenu = false },
                    ) {
                        ClearSelectionsMenuItem {
                            onClearSelectionsTapped.invoke()
                            showMenu = false
                        }
                        ShareTextMenuItem {
                            onShareTextTapped.invoke(resistor.shareableText())
                            showMenu = false
                        }
                        ShareImageMenuItem {
                            onShareImageTapped.invoke {
                                ResistorLayout(resistor, isError, 32.dp)
                            }
                            showMenu = false
                        }
                        FeedbackMenuItem {
                            onFeedbackTapped.invoke()
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
        bottomBar = {
            AppNavigationBar(
                selection = resistor.navBarSelection,
                onClick = { onNavBarSelectionChanged(it) },
                options = navigationBarOptions(),
            )
        },
        contentWindowInsets = WindowInsets.safeDrawing,
    ) { paddingValues ->
        ValueToColorScreenContent(
            paddingValues = paddingValues,
            resistor = resistor,
            isError = isError,
            eSeriesCardContent = eSeriesCardContent,
            onValueChanged = onValueChanged,
            onOptionSelected = onOptionSelected,
            onValidateResistanceTapped = onValidateResistanceTapped,
            onUseValueTapped = onUseValueTapped,
            onLearnMoreTapped = onLearnMoreTapped,
        )
    }
}

@Composable
private fun ValueToColorScreenContent(
    paddingValues: PaddingValues,
    resistor: ResistorVtc,
    isError: Boolean,
    eSeriesCardContent: ESeriesCardContent,
    onValueChanged: (String) -> Unit,
    onOptionSelected: (String, String, String) -> Unit,
    onValidateResistanceTapped: () -> Unit,
    onUseValueTapped: () -> String,
    onLearnMoreTapped: () -> Unit,
) {
    val sidePadding = dimensionResource(R.dimen.app_side_padding)
    val navBarSelection = resistor.navBarSelection
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(paddingValues)
            .padding(horizontal = sidePadding),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.padding(top = 32.dp))
        ResistorLayout(resistor, isError)
        AppTextField(
            label = stringResource(id = R.string.type_resistance_hint),
            modifier = Modifier.padding(top = 32.dp),
            value = resistor.resistance,
            isError = isError,
            errorMessage = stringResource(id = R.string.error_invalid_resistance),
            onValueChange = { onValueChanged(it) }
        )
        TextDropDownMenu(
            label = stringResource(id = R.string.units_hint),
            modifier = Modifier.padding(top = 12.dp),
            selectedOption = resistor.units,
            items = DropdownLists.UNITS_LIST,
            onOptionSelected = {
                onOptionSelected(it, resistor.band5, resistor.band6)
            },
        )
        AnimatedVisibility(
            visible = navBarSelection != 0,
            enter = fadeIn(animationSpec = tween(durationMillis = 300)) + expandVertically(),
            exit = fadeOut(animationSpec = tween(durationMillis = 300)) + shrinkVertically(),
        ) {
            ImageTextDropDownMenu(
                modifier = Modifier.padding(top = 12.dp),
                label = stringResource(R.string.tolerance_band_hint),
                selectedOption = resistor.band5,
                items = DropdownLists.TOLERANCE_LIST,
                isValueToColor = true,
                onOptionSelected = { onOptionSelected(resistor.units, it, resistor.band6) },
            )
        }
        AnimatedVisibility(
            visible = navBarSelection == 3,
            enter = fadeIn(animationSpec = tween(durationMillis = 300)) + expandVertically(),
            exit = fadeOut(animationSpec = tween(durationMillis = 300)) + shrinkVertically(),
        ) {
            ImageTextDropDownMenu(
                modifier = Modifier.padding(top = 12.dp),
                label = stringResource(R.string.ppm_band_hint),
                selectedOption = resistor.band6,
                items = DropdownLists.PPM_LIST,
                isValueToColor = true,
                onOptionSelected = { onOptionSelected(resistor.units, resistor.band5, it) },
            )
        }
        Spacer(modifier = Modifier.height(24.dp))
        M3FilledButton(
            buttonLabel = stringResource(R.string.vtc_validate_e_series_button),
            onClick = onValidateResistanceTapped,
            modifier = Modifier.fillMaxWidth(),
            enabled = !resistor.isEmpty() && !isError,
            useSquareShape = true,
        )
        Spacer(modifier = Modifier.height(12.dp))
        ESeriesCard(
            eSeriesCardContent = eSeriesCardContent,
            onLearnMoreTapped = onLearnMoreTapped,
            onUseValueTapped = { resistor.resistance = onUseValueTapped() }
        )
        BottomScreenSpacer()
    }
}

@AppScreenPreviews
@Composable
private fun ValueToColorScreenPreview() {
    ResistorCalculatorTheme {
        ValueToColorScreen(
            resistor = ResistorVtc(),
            isError = false,
            eSeriesCardContent = ESeriesCardContent.DefaultContent,
            onNavigateBack = {},
            onClearSelectionsTapped = {},
            onShareImageTapped = {},
            onShareTextTapped = {},
            onFeedbackTapped = {},
            onAboutTapped = {},
            onValueChanged = {},
            onOptionSelected = { _, _, _ -> },
            onNavBarSelectionChanged = { _ -> },
            onValidateResistanceTapped = {},
            onUseValueTapped = { "" },
            onLearnMoreTapped = {},
        )
    }
}
