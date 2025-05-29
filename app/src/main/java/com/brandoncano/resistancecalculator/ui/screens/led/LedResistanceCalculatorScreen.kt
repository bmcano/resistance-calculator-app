package com.brandoncano.resistancecalculator.ui.screens.led

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.brandoncano.resistancecalculator.R
import com.brandoncano.resistancecalculator.constants.Links
import com.brandoncano.resistancecalculator.constants.Symbols
import com.brandoncano.resistancecalculator.data.LedType
import com.brandoncano.resistancecalculator.to.ResistorCtv
import com.brandoncano.resistancecalculator.to.led.LedCircuit
import com.brandoncano.resistancecalculator.ui.composables.BottomScreenSpacer
import com.brandoncano.resistancecalculator.ui.screens.ctv.ResistorRow
import com.brandoncano.resistancecalculator.ui.theme.ResistorCalculatorTheme
import com.brandoncano.resistancecalculator.util.resistor.ResistorImageBuilder
import com.brandoncano.sharedcomponents.composables.AboutAppMenuItem
import com.brandoncano.sharedcomponents.composables.AppButton
import com.brandoncano.sharedcomponents.composables.AppDivider
import com.brandoncano.sharedcomponents.composables.AppDropDownMenu
import com.brandoncano.sharedcomponents.composables.AppLongScreenPreview
import com.brandoncano.sharedcomponents.composables.AppMenuTopAppBar
import com.brandoncano.sharedcomponents.composables.AppNavigationBar
import com.brandoncano.sharedcomponents.composables.AppScreenPreviews
import com.brandoncano.sharedcomponents.composables.AppTextField
import com.brandoncano.sharedcomponents.composables.ClearSelectionsMenuItem
import com.brandoncano.sharedcomponents.composables.FeedbackMenuItem
import com.brandoncano.sharedcomponents.text.onSurfaceVariant
import com.brandoncano.sharedcomponents.text.textStyleHeadline
import com.brandoncano.sharedcomponents.text.textStyleSubhead
import kotlinx.coroutines.launch

@Composable
fun LedResistanceCalculatorScreen(
    ledCircuit: LedCircuit,
    isError: Boolean,
    openMenu: MutableState<Boolean>,
    reset: MutableState<Boolean>,
    onNavigateBack: () -> Unit,
    onClearSelectionsTapped: () -> Unit,
    onAboutTapped: () -> Unit,
    onValueChanged: (String, String, String) -> Unit,
    onOptionSelected: (String) -> Unit,
    onNavBarSelectionChanged: (Int) -> Unit,
    onLearnMoreTapped: () -> Unit,
) {
    Scaffold(
        topBar = {
            AppMenuTopAppBar(
                titleText = stringResource(R.string.led_title),
                interactionSource = remember { MutableInteractionSource() },
                showMenu = openMenu,
                navigationIcon = Icons.Filled.Close,
                onNavigateBack = onNavigateBack,
            ) {
                ClearSelectionsMenuItem(onClearSelectionsTapped)
                FeedbackMenuItem(
                    app = Links.APP_NAME,
                    showMenu = openMenu,
                )
                AboutAppMenuItem(onAboutTapped)
            }
        },
        bottomBar = {
            AppNavigationBar(
                selection = ledCircuit.navBarSelection,
                onClick = { onNavBarSelectionChanged(it) },
                options = ledNavigationBarOptions(),
            )
        },
        contentWindowInsets = WindowInsets.safeDrawing,
    ) { paddingValues ->
        LedResistanceCalculatorScreenContent(
            paddingValues = paddingValues,
            ledCircuit = ledCircuit,
            isError = isError,
            reset = reset,
            onValueChanged = onValueChanged,
            onOptionSelected = onOptionSelected,
            onLearnMoreTapped = onLearnMoreTapped,
        )
    }
}

@Composable
private fun LedResistanceCalculatorScreenContent(
    paddingValues: PaddingValues,
    ledCircuit: LedCircuit,
    isError: Boolean,
    reset: MutableState<Boolean>,
    onValueChanged: (String, String, String) -> Unit,
    onOptionSelected: (String) -> Unit,
    onLearnMoreTapped: () -> Unit,
) {
    val sourceVoltage = remember { mutableStateOf(ledCircuit.sourceVoltage) }
    val ledForwardVoltage = remember { mutableStateOf(ledCircuit.ledForwardVoltage) }
    val ledCurrent = remember { mutableStateOf(ledCircuit.ledCurrent) }
    val navBarSelection = ledCircuit.navBarSelection

    val bringIntoViewRequester = remember { BringIntoViewRequester() }
    val coroutineScope = rememberCoroutineScope()
    val scrollState = rememberScrollState()

    LaunchedEffect(ledCircuit.ledForwardVoltage) {
        ledForwardVoltage.value = ledCircuit.ledForwardVoltage
    }

    LaunchedEffect(ledCircuit.ledCurrent) {
        ledCurrent.value = ledCircuit.ledCurrent
    }


    val sidePadding = dimensionResource(R.dimen.app_side_padding)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(paddingValues)
            .padding(horizontal = sidePadding),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        // TODO - Create LED circuit SVGs
        Image(
            painter = painterResource(R.drawable.img_resistors_parallel),
            contentDescription = null,
            modifier = Modifier
                .padding(vertical = 16.dp)
                .size(width = 301.dp, height = 177.dp)
                .align(Alignment.CenterHorizontally),
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurface),
        )

        AppTextField(
            label = stringResource(R.string.led_source_voltage_text_field_label),
            value = sourceVoltage,
            reset = reset.value,
            isError = false,
            errorMessage = stringResource(R.string.error_invalid_voltage),
            onOptionSelected = {
                onValueChanged(it, ledForwardVoltage.value, ledCurrent.value)
            },
        )
        AppDropDownMenu(
            label = stringResource(R.string.led_type_dropdown_label),
            modifier = Modifier.padding(top = 12.dp),
            selectedOption = ledCircuit.led.display,
            items = LedType.getDropDownList(),
            reset = reset.value,
            onOptionSelected = { onOptionSelected(it) },
        )
        AppTextField(
            label = stringResource(R.string.led_forward_voltage_text_field_label),
            modifier = Modifier.padding(top = 12.dp),
            value = ledForwardVoltage,
            reset = reset.value,
            isError = false,
            errorMessage = stringResource(R.string.error_invalid_voltage),
            onOptionSelected = {
                onValueChanged(sourceVoltage.value, it, ledCurrent.value)
            },
        )
        AppTextField(
            label = stringResource(R.string.led_current_text_field_label),
            modifier = Modifier.padding(top = 12.dp),
            value = ledCurrent,
            reset = reset.value,
            isError = false,
            errorMessage = stringResource(R.string.error_invalid_current),
            onOptionSelected = {
                onValueChanged(sourceVoltage.value, ledForwardVoltage.value, it)
            },
        )
        AppDropDownMenu(
            label = stringResource(R.string.led_tolerance_dropdown_label),
            modifier = Modifier.padding(top = 12.dp),
            selectedOption = ledCircuit.resistorTolerance,
            items = listOf(Symbols.E12, Symbols.E24),
            reset = reset.value,
            onOptionSelected = { _ -> },
        )
        // Might make these dropdowns similar to circuit calculations with number of resistors
        // Would not need to validate selection other than type conversion
        AnimatedVisibility(
            visible = navBarSelection != 0,
            enter = fadeIn(animationSpec = tween(durationMillis = 300)) + expandVertically(),
            exit = fadeOut(animationSpec = tween(durationMillis = 300)) + shrinkVertically(),
        ) {
            val labelRes = if (navBarSelection == 1) R.string.led_per_branch_series else R.string.led_per_branch_parallel
            AppTextField(
                label = stringResource(id = labelRes),
                modifier = Modifier.padding(top = 12.dp),
                value = remember { mutableStateOf("") },
                reset = reset.value,
                isError = false,
                errorMessage = stringResource(R.string.error_invalid_number), // error message WIP
                onOptionSelected = {},
            )
        }
        AnimatedVisibility(
            visible = navBarSelection == 2,
            enter = fadeIn(animationSpec = tween(durationMillis = 300)) + expandVertically(),
            exit = fadeOut(animationSpec = tween(durationMillis = 300)) + shrinkVertically(),
        ) {
            AppTextField(
                label = stringResource(R.string.led_number_of_branches_label),
                modifier = Modifier.padding(top = 12.dp),
                value = remember { mutableStateOf("") },
                reset = reset.value,
                isError = false,
                errorMessage = stringResource(R.string.error_invalid_number), // error message WIP
                onOptionSelected = {},
            )
        }

        // Might want to disable if not ready to calculate
        AppButton(
            label = stringResource(R.string.led_calculate_button),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp),
            onClick = {
                // callback to VM
                coroutineScope.launch {
                    bringIntoViewRequester.bringIntoView()
                }
            },
        )

        // results - Example UI layout below
        // Will likely need to create TOs and components for each section of information, maybe bullet lists?
        // Resistance
        // - Exact calculated resistance
        // - Recommended resistor (image and text) - based on E-series, need next closest higher resistance option
        // Individual Components
        // - Effective current
        // - Resistor dissipation
        // - LED dissipation
        // Circuit Info
        // - Total current
        // - Total dissipation
        // - Power Efficiency

        // 2nd app items
        // - total current
        // - power at resistor
        // - power at single LED
        // - total power

        // "You need 1 resistor and 1 LED"
        Column(
            modifier = Modifier.bringIntoViewRequester(bringIntoViewRequester),
        ) {
            AppDivider(modifier = Modifier.padding(top = 24.dp, bottom = 16.dp))
            Text(
                text = "Resistance:",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                style = textStyleHeadline(),
            )
            Text(
                text = "Calculated resistance: 290 ${Symbols.OHMS}",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 4.dp),
                style = textStyleSubhead().onSurfaceVariant(),
            )
            Text(
                text = "Recommended resistor: 300 ${Symbols.OHMS} | 1/8 Watts",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp),
                style = textStyleSubhead(),
            )
            ResistorRow(
                ResistorImageBuilder.execute(
                    ResistorCtv(
                        "Orange",
                        "Black",
                        "",
                        "Brown",
                        "Gold"
                    )
                )
            )
            AppDivider(modifier = Modifier.padding(top = 24.dp, bottom = 16.dp))
            Text(
                text = "Individual components:",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                style = textStyleHeadline(),
            )
            Text(
                text = "Effective current: 9.7mA",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 4.dp),
                style = textStyleSubhead().onSurfaceVariant(),
            )
            Text(
                text = "Resistor dissipation: 28 mWatts",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 4.dp),
                style = textStyleSubhead().onSurfaceVariant(),
            )
            Text(
                text = "LED dissipation: 20mWatts",
                modifier = Modifier.fillMaxWidth(),
                style = textStyleSubhead().onSurfaceVariant(),
            )
            AppDivider(modifier = Modifier.padding(vertical = 16.dp))
            Text(
                // For series resistance
                text = "Series info:",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                style = textStyleHeadline(),
            )
            Text(
                text = "LEDs dissipation: 40 mWatts",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 4.dp),
                style = textStyleSubhead().onSurfaceVariant(),
            )
            Text(
                // Add on in parallel calculator
                text = "One branch dissipation: 47 mWatts",
                modifier = Modifier.fillMaxWidth(),
                style = textStyleSubhead().onSurfaceVariant(),
            )
            AppDivider(modifier = Modifier.padding(vertical = 16.dp))
            Text(
                text = "Circuit info:",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                style = textStyleHeadline(),
            )
            Text(
                // added in for parallel - only if we have separate resistors per branch
                text = "Total resistor dissipation: 14 mWatts",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 4.dp),
                style = textStyleSubhead().onSurfaceVariant(),
            )
            Text(
                text = "Total LEDs dissipation: 80mWatts",
                // added in for parallel
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 4.dp),
                style = textStyleSubhead().onSurfaceVariant(),
            )
            Text(
                text = "Total current: 9.7mA",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 4.dp),
                style = textStyleSubhead().onSurfaceVariant(),
            )
            Text(
                text = "Total dissipation: 48 mWatts",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 4.dp),
                style = textStyleSubhead().onSurfaceVariant(),
            )
            Text(
                text = "Power Efficiency: 41%",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                style = textStyleSubhead().onSurfaceVariant(),
            )
        }

        BottomScreenSpacer()
    }
}

@AppScreenPreviews
@AppLongScreenPreview
@Composable
private fun LedResistanceCalculatorScreenPreview() {
    ResistorCalculatorTheme {
        LedResistanceCalculatorScreen(
            ledCircuit = LedCircuit(),
            isError = false,
            openMenu = remember { mutableStateOf(false) },
            reset = remember { mutableStateOf(false) },
            onNavigateBack = {},
            onClearSelectionsTapped = {},
            onAboutTapped = {},
            onValueChanged = { _, _, _ -> },
            onOptionSelected = {},
            onNavBarSelectionChanged = {},
            onLearnMoreTapped = {},
        )
    }
}
