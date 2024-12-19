package com.brandoncano.resistancecalculator.ui.screens.circuit

import androidx.compose.foundation.Image
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.brandoncano.resistancecalculator.R
import com.brandoncano.resistancecalculator.constants.Symbols
import com.brandoncano.resistancecalculator.data.DropdownLists
import com.brandoncano.resistancecalculator.ui.composables.AboutAppMenuItem
import com.brandoncano.resistancecalculator.ui.theme.ResistorCalculatorTheme
import com.brandoncano.resistancecalculator.util.circuit.IsValidNumber
import com.brandoncano.sharedcomponents.composables.AppDropDownMenu
import com.brandoncano.sharedcomponents.composables.AppMenuTopAppBar
import com.brandoncano.sharedcomponents.composables.AppScreenPreviews
import com.brandoncano.sharedcomponents.composables.AppTextField
import com.brandoncano.sharedcomponents.composables.ClearSelectionsMenuItem
import com.brandoncano.sharedcomponents.composables.FeedbackMenuItem
import com.brandoncano.sharedcomponents.text.onSurfaceVariant
import com.brandoncano.sharedcomponents.text.textStyleBody
import com.brandoncano.sharedcomponents.text.textStyleLargeTitle

@Composable
fun SeriesCalculatorScreen(
    openMenu: MutableState<Boolean>,
    reset: MutableState<Boolean>,
    onNavigateBack: () -> Unit,
    onClearSelectionsTapped: () -> Unit,
    onAboutTapped: () -> Unit,
    onValueChanged: (Boolean, Int) -> Unit,
    totalResistance: MutableState<String>,
    resistorInputs: SnapshotStateList<String>,
) {
    Scaffold(
        topBar = {
            AppMenuTopAppBar(
                titleText = stringResource(R.string.title_series_resistors),
                interactionSource = remember { MutableInteractionSource() },
                showMenu = openMenu,
                navigationIcon = Icons.AutoMirrored.Filled.ArrowBack,
                onNavigateBack = onNavigateBack,
            ) {
                ClearSelectionsMenuItem(onClearSelectionsTapped)
                FeedbackMenuItem(
                    app = Symbols.APP_NAME,
                    showMenu = openMenu,
                )
                AboutAppMenuItem(onAboutTapped)
            }
        },
    ) { paddingValues ->
        ColorToValueScreenContent(
            paddingValues = paddingValues,
            reset = reset,
            onValueChanged = onValueChanged,
            totalResistance = totalResistance,
            resistorInputs = resistorInputs,
        )
    }
}

@Composable
private fun ColorToValueScreenContent(
    paddingValues: PaddingValues,
    reset: MutableState<Boolean>,
    onValueChanged: (Boolean, Int) -> Unit,
    totalResistance: MutableState<String>,
    resistorInputs: SnapshotStateList<String>,
) {
    val sidePadding = dimensionResource(R.dimen.app_side_padding)
    var sameValues by remember { mutableStateOf(false) }
    var resistorCount by remember { mutableIntStateOf(2) }
    var units by remember { mutableStateOf(Symbols.OHMS) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(horizontal = sidePadding)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(R.drawable.img_series_resistors),
            contentDescription = null,
            modifier = Modifier
                .padding(top = 16.dp)
                .size(width = 288.dp, height = 112.dp)
                .align(Alignment.CenterHorizontally),
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurfaceVariant)
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "${totalResistance.value} $units",
            modifier = Modifier,
            style = textStyleLargeTitle()
        )
        Spacer(modifier = Modifier.height(24.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.circuit_use_same_values),
                modifier = Modifier.weight(1f),
                style = textStyleBody().onSurfaceVariant(),
            )
            Switch(
                modifier = Modifier.padding(start = 16.dp),
                checked = sameValues,
                onCheckedChange = {
                    sameValues = it
                    onValueChanged(sameValues, resistorCount)
                }
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            AppDropDownMenu(
                label = stringResource(id = R.string.circuit_num_resistors_label),
                modifier = Modifier
                    .weight(0.5f)
                    .padding(end = 8.dp),
                selectedOption = resistorCount.toString(),
                items = DropdownLists.RESISTOR_COUNT_LIST,
            ) { selectedValue ->
                resistorCount = selectedValue.toIntOrNull() ?: 2
                onValueChanged(sameValues, resistorCount)
            }
            AppDropDownMenu(
                label = stringResource(id = R.string.circuit_units_label),
                modifier = Modifier
                    .weight(0.5f)
                    .padding(start = 8.dp),
                selectedOption = units,
                items = DropdownLists.UNITS_LIST,
            ) {
                it -> units = it
            }
        }
        Spacer(modifier = Modifier.height(12.dp))
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val fieldsToShow = if (sameValues) 1 else resistorCount
            repeat(fieldsToShow) { index ->
                val labelText = if (sameValues) {
                    stringResource(id = R.string.circuit_text_field_label, units)
                } else {
                    stringResource(id = R.string.circuit_text_field_label_multiple, index + 1, units)
                }
                AppTextField(
                    label = labelText,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    value = remember { mutableStateOf(resistorInputs[index]) },
                    reset = reset.value,
                    isError = !IsValidNumber.execute(resistorInputs[index]),
                ) { newValue ->
                    resistorInputs[index] = newValue
                    onValueChanged(sameValues, resistorCount)
                }
            }
        }
        Spacer(modifier = Modifier.height(48.dp))
    }
}

@AppScreenPreviews
@Composable
private fun ColorToValueScreen4BandPreview() {
    ResistorCalculatorTheme {
        SeriesCalculatorScreen(
            openMenu = remember { mutableStateOf(false) },
            reset = remember { mutableStateOf(false) },
            onNavigateBack = {},
            onClearSelectionsTapped = {},
            onAboutTapped = {},
            onValueChanged = { _, _ -> },
            totalResistance = remember { mutableStateOf("0.0") },
            resistorInputs = remember { mutableStateListOf(*Array(8) { "" }) }
        )
    }
}
