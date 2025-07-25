package com.brandoncano.resistancecalculator.ui.screens.circuit

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.brandoncano.resistancecalculator.R
import com.brandoncano.resistancecalculator.constants.DropdownLists
import com.brandoncano.resistancecalculator.to.Circuit
import com.brandoncano.resistancecalculator.ui.composables.AboutAppMenuItem
import com.brandoncano.resistancecalculator.ui.composables.AppTextField
import com.brandoncano.resistancecalculator.ui.composables.BottomScreenSpacer
import com.brandoncano.resistancecalculator.ui.composables.ClearSelectionsMenuItem
import com.brandoncano.resistancecalculator.ui.composables.FeedbackMenuItem
import com.brandoncano.resistancecalculator.ui.composables.M3CardContent
import com.brandoncano.resistancecalculator.ui.composables.MenuIconButton
import com.brandoncano.resistancecalculator.ui.composables.TextDropDownMenu
import com.brandoncano.resistancecalculator.ui.composables.m3.M3OutlinedCard
import com.brandoncano.resistancecalculator.ui.composables.m3.M3Switch
import com.brandoncano.resistancecalculator.ui.composables.m3.M3TopAppBar
import com.brandoncano.resistancecalculator.ui.screens.ctv.ResistanceText
import com.brandoncano.resistancecalculator.ui.theme.ResistorCalculatorTheme
import com.brandoncano.resistancecalculator.util.circuit.IsValidNumber
import com.brandoncano.sharedcomponents.composables.AppScreenPreviews

@OptIn(ExperimentalMaterial3Api::class) // For TopAppBar
@Composable
fun CircuitCalculatorScreen(
    circuitTitle: Int,
    circuitVector: Int,
    vectorSize: Pair<Dp, Dp>,
    circuit: Circuit,
    onNavigateBack: () -> Unit,
    onClearSelectionsTapped: () -> Unit,
    onFeedbackTapped: () -> Unit,
    onAboutTapped: () -> Unit,
    onValueChanged: (Boolean, Int, String) -> Unit,
    onLearnCircuitEquationsTapped: () -> Unit,
) {
    var showMenu by remember { mutableStateOf(false) }
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            M3TopAppBar(
                titleText = stringResource(id = circuitTitle),
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
        contentWindowInsets = WindowInsets.safeDrawing,
    ) { paddingValues ->
        CircuitCalculatorScreenContent(
            paddingValues = paddingValues,
            circuitVector = circuitVector,
            vectorSize = vectorSize,
            circuit = circuit,
            onValueChanged = onValueChanged,
            onLearnCircuitEquationsTapped = onLearnCircuitEquationsTapped,
        )
    }
}

@Composable
private fun CircuitCalculatorScreenContent(
    paddingValues: PaddingValues,
    circuitVector: Int,
    vectorSize: Pair<Dp, Dp>,
    circuit: Circuit,
    onValueChanged: (Boolean, Int, String) -> Unit,
    onLearnCircuitEquationsTapped: () -> Unit,
) {
    val sidePadding = dimensionResource(R.dimen.app_side_padding)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(paddingValues)
            .padding(horizontal = sidePadding),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(circuitVector),
            contentDescription = null,
            modifier = Modifier
                .padding(vertical = 16.dp)
                .size(width = vectorSize.first, height = vectorSize.second)
                .align(Alignment.CenterHorizontally),
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurface),
        )
        ResistanceText("${circuit.totalResistance} ${circuit.units}")
        Spacer(modifier = Modifier.height(24.dp))
        M3Switch(
            labelText = stringResource(R.string.circuit_use_same_values),
            checked = circuit.isSameValues,
            onCheckedChange = {
                onValueChanged(it, circuit.resistorCount, circuit.units)
            },
            horizontalInsetPadding = 0.dp,
        )
        Spacer(modifier = Modifier.height(24.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            TextDropDownMenu(
                label = stringResource(id = R.string.circuit_num_resistors_label),
                modifier = Modifier
                    .weight(0.5f)
                    .padding(end = 8.dp),
                selectedOption = circuit.resistorCount.toString(),
                items = DropdownLists.RESISTOR_COUNT_LIST,
            ) {
                val resistorCount = it.toIntOrNull() ?: 2
                onValueChanged(circuit.isSameValues, resistorCount, circuit.units)
            }
            TextDropDownMenu(
                label = stringResource(id = R.string.circuit_units_label),
                modifier = Modifier
                    .weight(0.5f)
                    .padding(start = 8.dp),
                selectedOption = circuit.units,
                items = DropdownLists.UNITS_LIST,
                onOptionSelected = { onValueChanged(circuit.isSameValues, circuit.resistorCount, it) }
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .animateContentSize(animationSpec = tween(durationMillis = 300)),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            val fieldsToShow = if (circuit.isSameValues) 1 else circuit.resistorCount
            repeat(fieldsToShow) { index ->
                val labelText = getResistanceLabelText(circuit.isSameValues, circuit.units, index + 1)
                AppTextField(
                    label = labelText,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp),
                    value = circuit.resistorInputs[index],
                    isError = !IsValidNumber.execute(circuit.resistorInputs[index]),
                ) { newValue ->
                    circuit.resistorInputs[index] = newValue
                    onValueChanged(circuit.isSameValues, circuit.resistorCount, circuit.units)
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
        }
        M3OutlinedCard {
            M3CardContent(
                headline = stringResource(R.string.circuit_info_card_header),
                subhead = stringResource(R.string.circuit_info_card_subhead),
                bodyText = stringResource(R.string.circuit_info_card_body),
                primaryButtonText = stringResource(R.string.circuit_info_card_cta_text),
                onPrimaryClick = onLearnCircuitEquationsTapped,
            )
        }
        BottomScreenSpacer()
    }
}

@AppScreenPreviews
@Composable
private fun CircuitCalculatorScreenSeriesPreview() {
    ResistorCalculatorTheme {
        CircuitCalculatorScreen(
            circuitTitle = R.string.circuit_title_series,
            circuitVector = R.drawable.img_resistors_series,
            vectorSize = Pair(390.dp, 106.dp),
            circuit = Circuit(),
            onNavigateBack = {},
            onClearSelectionsTapped = {},
            onFeedbackTapped = {},
            onAboutTapped = {},
            onValueChanged = { _, _, _ -> },
            onLearnCircuitEquationsTapped = {},
        )
    }
}

@AppScreenPreviews
@Composable
private fun CircuitCalculatorScreenParallelPreview() {
    ResistorCalculatorTheme {
        CircuitCalculatorScreen(
            circuitTitle = R.string.circuit_title_parallel,
            circuitVector = R.drawable.img_resistors_parallel,
            vectorSize = Pair(301.dp, 177.dp),
            circuit = Circuit(),
            onNavigateBack = {},
            onClearSelectionsTapped = {},
            onFeedbackTapped = {},
            onAboutTapped = {},
            onValueChanged = { _, _, _ -> },
            onLearnCircuitEquationsTapped = {},
        )
    }
}
