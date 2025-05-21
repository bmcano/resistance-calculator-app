package com.brandoncano.resistancecalculator.ui.screens.circuit

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.material.icons.outlined.Lightbulb
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.brandoncano.resistancecalculator.R
import com.brandoncano.resistancecalculator.constants.DropdownLists
import com.brandoncano.resistancecalculator.constants.Links
import com.brandoncano.resistancecalculator.to.Circuit
import com.brandoncano.resistancecalculator.ui.composables.BottomScreenSpacer
import com.brandoncano.resistancecalculator.ui.theme.ResistorCalculatorTheme
import com.brandoncano.resistancecalculator.util.circuit.IsValidNumber
import com.brandoncano.sharedcomponents.composables.AboutAppMenuItem
import com.brandoncano.sharedcomponents.composables.AppActionCard
import com.brandoncano.sharedcomponents.composables.AppDropDownMenu
import com.brandoncano.sharedcomponents.composables.AppMenuTopAppBar
import com.brandoncano.sharedcomponents.composables.AppScreenPreviews
import com.brandoncano.sharedcomponents.composables.AppTextField
import com.brandoncano.sharedcomponents.composables.ClearSelectionsMenuItem
import com.brandoncano.sharedcomponents.composables.FeedbackMenuItem
import com.brandoncano.sharedcomponents.data.CardAction
import com.brandoncano.sharedcomponents.text.textStyleBody

@Composable
fun CircuitCalculatorScreen(
    circuitTitle: Int,
    circuitVector: Int,
    vectorSize: Pair<Dp, Dp>,
    circuit: Circuit,
    openMenu: MutableState<Boolean>,
    reset: MutableState<Boolean>,
    onNavigateBack: () -> Unit,
    onClearSelectionsTapped: () -> Unit,
    onAboutTapped: () -> Unit,
    onValueChanged: (Boolean, Int, String) -> Unit,
    onLearnCircuitEquationsTapped: () -> Unit,
) {
    Scaffold(
        topBar = {
            AppMenuTopAppBar(
                titleText = stringResource(circuitTitle),
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
        contentWindowInsets = WindowInsets.safeDrawing,
    ) { paddingValues ->
        CircuitCalculatorScreenContent(
            paddingValues = paddingValues,
            circuitVector = circuitVector,
            vectorSize = vectorSize,
            circuit = circuit,
            reset = reset,
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
    reset: MutableState<Boolean>,
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
        CircuitResistanceText("${circuit.totalResistance} ${circuit.units}")
        Spacer(modifier = Modifier.height(24.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.circuit_use_same_values),
                modifier = Modifier.weight(1f),
                style = textStyleBody(),
            )
            Switch(
                modifier = Modifier.padding(start = 16.dp),
                checked = circuit.isSameValues,
                onCheckedChange = {
                    onValueChanged(it, circuit.resistorCount, circuit.units)
                }
            )
        }
        Spacer(modifier = Modifier.height(24.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            AppDropDownMenu(
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
            AppDropDownMenu(
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
                    value = remember { mutableStateOf(circuit.resistorInputs[index]) },
                    reset = reset.value,
                    isError = !IsValidNumber.execute(circuit.resistorInputs[index]),
                ) { newValue ->
                    circuit.resistorInputs[index] = newValue
                    onValueChanged(circuit.isSameValues, circuit.resistorCount, circuit.units)
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
        }
        AppActionCard(
            icon = Icons.Outlined.Lightbulb,
            iconTint = MaterialTheme.colorScheme.primary,
            cardTitle = stringResource(R.string.circuit_info_card_title_text),
            cardBody = stringResource(R.string.circuit_info_card_body_text),
            leftActionButton = CardAction(
                buttonLabel = stringResource(R.string.circuit_info_card_cta_text),
                onClick = onLearnCircuitEquationsTapped,
            )
        )
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
            openMenu = remember { mutableStateOf(false) },
            reset = remember { mutableStateOf(false) },
            onNavigateBack = {},
            onClearSelectionsTapped = {},
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
            openMenu = remember { mutableStateOf(false) },
            reset = remember { mutableStateOf(false) },
            onNavigateBack = {},
            onClearSelectionsTapped = {},
            onAboutTapped = {},
            onValueChanged = { _, _, _ -> },
            onLearnCircuitEquationsTapped = {},
        )
    }
}
