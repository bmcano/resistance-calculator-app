package com.brandoncano.resistancecalculator.ui.screens.led

import androidx.compose.foundation.Image
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.brandoncano.resistancecalculator.R
import com.brandoncano.resistancecalculator.constants.Links
import com.brandoncano.resistancecalculator.constants.Symbols
import com.brandoncano.resistancecalculator.to.ResistorCtv
import com.brandoncano.resistancecalculator.ui.composables.BottomScreenSpacer
import com.brandoncano.resistancecalculator.ui.screens.ctv.ResistorLayout
import com.brandoncano.resistancecalculator.ui.screens.ctv.ResistorRow
import com.brandoncano.resistancecalculator.ui.theme.ResistorCalculatorTheme
import com.brandoncano.resistancecalculator.util.resistor.ResistorImageBuilder
import com.brandoncano.sharedcomponents.composables.AboutAppMenuItem
import com.brandoncano.sharedcomponents.composables.AppBulletList
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
import com.brandoncano.sharedcomponents.text.textStyleBody
import com.brandoncano.sharedcomponents.text.textStyleHeadline
import com.brandoncano.sharedcomponents.text.textStyleSubhead
import kotlinx.coroutines.launch

@Composable
fun LedResistanceCalculatorScreen(
    isError: Boolean,
    openMenu: MutableState<Boolean>,
    reset: MutableState<Boolean>,
    onNavigateBack: () -> Unit,
    onClearSelectionsTapped: () -> Unit,
    onAboutTapped: () -> Unit,
    onLearnMoreTapped: () -> Unit,
) {
    Scaffold(
        topBar = {
            AppMenuTopAppBar(
                titleText = "LED Resistance",
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
                selection = 0,
                onClick = {},
                options = ledNavigationBarOptions(),
            )
        },
        contentWindowInsets = WindowInsets.safeDrawing,
    ) { paddingValues ->
        LedResistanceCalculatorScreenContent(
            paddingValues = paddingValues,
            isError = isError,
            reset = reset,
            onLearnMoreTapped = onLearnMoreTapped,
        )
    }
}

@Composable
private fun LedResistanceCalculatorScreenContent(
    paddingValues: PaddingValues,
    isError: Boolean,
    reset: MutableState<Boolean>,
    onLearnMoreTapped: () -> Unit,
) {
    val bringIntoViewRequester = remember { BringIntoViewRequester() }
    val coroutineScope = rememberCoroutineScope()
    val scrollState = rememberScrollState()

    val sidePadding = dimensionResource(R.dimen.app_side_padding)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(paddingValues)
            .padding(horizontal = sidePadding),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        //Spacer(modifier = Modifier.padding(top = 32.dp))
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
            label = "Source voltage [volts]",
            value = remember { mutableStateOf("") },
            onOptionSelected = {},
        )
        AppDropDownMenu(
            label = "LED",
            modifier = Modifier.padding(top = 12.dp),
            selectedOption = "Custom",
            items = listOf("Custom"),
            reset = reset.value,
            onOptionSelected = { _ -> },
        )

        AppTextField(
            label = "LED forward voltage [volts]",
            modifier = Modifier.padding(top = 12.dp),
            value = remember { mutableStateOf("") },
            onOptionSelected = {},
        )
        AppTextField(
            label = "LED current [mA]",
            modifier = Modifier.padding(top = 12.dp),
            value = remember { mutableStateOf("") },
            onOptionSelected = {},
        )

        AppDropDownMenu(
            label = "Resistor tolerance (E-series)",
            modifier = Modifier.padding(top = 12.dp),
            selectedOption = "E24 (5%)",
            items = listOf("E12 (10%)", "E24 (5%)"),
            reset = reset.value,
            onOptionSelected = { _ -> },
        )
        Spacer(modifier = Modifier.height(12.dp))
        // TODO - will need to modify based on navbar, temp logic for placeholder, will add animated visibility
        //  0 - Single: No more Text Fields
        //  1 - Series: 1 extra text field
        //  2 - Parallel: 2 extra text fields
        val navBarSelection = 2
        if (navBarSelection == 1) {
            AppTextField(
                label = "Number of LEDs in series",
                value = remember { mutableStateOf("") },
                onOptionSelected = {},
            )
        }

        if (navBarSelection == 2) {
            AppTextField(
                label = "Number of LEDs in one branch",
                value = remember { mutableStateOf("") },
                onOptionSelected = {},
            )
            Spacer(modifier = Modifier.height(12.dp))
            AppTextField(
                label = "Number of branches",
                value = remember { mutableStateOf("") },
                onOptionSelected = {},
            )
        }

        // Might want to disable if not ready to calculate
        AppButton(
            label = "Calculate",
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
            isError = false,
            openMenu = remember { mutableStateOf(false) },
            reset = remember { mutableStateOf(false) },
            onNavigateBack = {},
            onClearSelectionsTapped = {},
            onAboutTapped = {},
            onLearnMoreTapped = {},
        )
    }
}
