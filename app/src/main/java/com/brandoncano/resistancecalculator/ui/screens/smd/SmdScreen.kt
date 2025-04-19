package com.brandoncano.resistancecalculator.ui.screens.smd

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.outlined.Explicit
import androidx.compose.material.icons.outlined.Lightbulb
import androidx.compose.material.icons.outlined.Looks3
import androidx.compose.material.icons.outlined.Looks4
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.brandoncano.resistancecalculator.R
import com.brandoncano.resistancecalculator.constants.DropdownLists
import com.brandoncano.resistancecalculator.constants.Links
import com.brandoncano.resistancecalculator.to.SmdResistor
import com.brandoncano.resistancecalculator.ui.composables.ShareImageMenuItem
import com.brandoncano.resistancecalculator.ui.screens.ctv.AppActionCard
import com.brandoncano.resistancecalculator.ui.screens.ctv.CardAction
import com.brandoncano.resistancecalculator.ui.theme.ResistorCalculatorTheme
import com.brandoncano.sharedcomponents.composables.AboutAppMenuItem
import com.brandoncano.sharedcomponents.composables.AppDropDownMenu
import com.brandoncano.sharedcomponents.composables.AppMenuTopAppBar
import com.brandoncano.sharedcomponents.composables.AppNavigationBar
import com.brandoncano.sharedcomponents.composables.AppScreenPreviews
import com.brandoncano.sharedcomponents.composables.AppTextField
import com.brandoncano.sharedcomponents.composables.ClearSelectionsMenuItem
import com.brandoncano.sharedcomponents.composables.FeedbackMenuItem
import com.brandoncano.sharedcomponents.composables.ShareTextMenuItem
import com.brandoncano.sharedcomponents.data.NavigationBarOptions
import java.util.Locale

@Composable
fun SmdScreen(
    openMenu: MutableState<Boolean>,
    reset: MutableState<Boolean>,
    resistor: SmdResistor,
    isError: Boolean,
    onNavigateBack: () -> Unit,
    onClearSelectionsTapped: () -> Unit,
    onAboutTapped: () -> Unit,
    onValueChanged: (String, String, Boolean) -> Unit,
    onNavBarSelectionChanged: (Int) -> Unit,
    onLearnSmdCodesTapped: () -> Unit,
) {
    Scaffold(
        topBar = {
            AppMenuTopAppBar(
                titleText = stringResource(R.string.smd_title),
                interactionSource = remember { MutableInteractionSource() },
                showMenu = openMenu,
                navigationIcon = Icons.Filled.Close,
                onNavigateBack = onNavigateBack,
            ) {
                ClearSelectionsMenuItem(onClearSelectionsTapped)
                ShareTextMenuItem(
                    text = resistor.toString(),
                    showMenu = openMenu,
                )
                ShareImageMenuItem(
                    applicationId = Links.APPLICATION_ID,
                    showMenu = openMenu,
                    content = { SmdResistorLayout(resistor, isError) }
                )
                FeedbackMenuItem(
                    app = Links.APP_NAME,
                    showMenu = openMenu,
                )
                AboutAppMenuItem(onAboutTapped)
            }
        },
        bottomBar = {
            AppNavigationBar(
                selection = resistor.navBarSelection,
                onClick = { onNavBarSelectionChanged(it) },
                options = listOf(
                    NavigationBarOptions(
                        label = stringResource(id = R.string.smd_navbar_three_eia),
                        imageVector = Icons.Outlined.Looks3,
                    ),
                    NavigationBarOptions(
                        label = stringResource(id = R.string.smd_navbar_four_eia),
                        imageVector = Icons.Outlined.Looks4,
                    ),
                    NavigationBarOptions(
                        label = stringResource(id = R.string.smd_navbar_eia_96),
                        imageVector = Icons.Outlined.Explicit,
                    ),
                ),
            )
        }
    ) { paddingValues ->
        SmdScreenContent(
            paddingValues = paddingValues,
            reset = reset,
            resistor = resistor,
            isError = isError,
            onValueChanged = onValueChanged,
            onLearnSmdCodesTapped = onLearnSmdCodesTapped,
        )
    }
}

@Composable
private fun SmdScreenContent(
    paddingValues: PaddingValues,
    reset: MutableState<Boolean>,
    resistor: SmdResistor,
    isError: Boolean,
    onValueChanged: (String, String, Boolean) -> Unit,
    onLearnSmdCodesTapped: () -> Unit,
) {
    val code = remember { mutableStateOf(resistor.code) }
    val sidePadding = dimensionResource(R.dimen.app_side_padding)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(paddingValues)
            .padding(horizontal = sidePadding),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.padding(top = 32.dp))
        SmdResistorLayout(resistor, isError)
        AppTextField(
            label = stringResource(id = R.string.smd_code_hint),
            modifier = Modifier.padding(top = 32.dp),
            value = code,
            reset = reset.value,
            isError = isError,
            errorMessage = stringResource(id = R.string.error_invalid_code),
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Characters,
                autoCorrectEnabled = false,
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            )
        ) {
            code.value = it.uppercase(Locale.getDefault())
            onValueChanged(code.value, resistor.units, false)
        }
        AppDropDownMenu(
            label = stringResource(id = R.string.units_hint),
            modifier = Modifier.padding(top = 12.dp),
            selectedOption = resistor.units,
            items = DropdownLists.UNITS_LIST,
            reset = reset.value,
            onOptionSelected = { onValueChanged(code.value, it, true) }
        )
        Spacer(modifier = Modifier.height(24.dp))
        AppActionCard(
            icon = Icons.Outlined.Lightbulb,
            iconTint = MaterialTheme.colorScheme.primary,
            cardTitle = stringResource(R.string.smd_info_card_title_text),
            cardBody = stringResource(R.string.smd_info_card_body_text),
            leftActionButton = CardAction(
                buttonLabel = stringResource(R.string.smd_info_card_cta_text),
                onClick = onLearnSmdCodesTapped,
            )
        )
        Spacer(modifier = Modifier.height(24.dp))
    }
}

@AppScreenPreviews
@Composable
private fun SmdScreenPreview() {
    ResistorCalculatorTheme {
        SmdScreen(
            openMenu = remember { mutableStateOf(false) },
            reset = remember { mutableStateOf(false) },
            resistor = SmdResistor(),
            isError = false,
            onNavigateBack = {},
            onClearSelectionsTapped = {},
            onAboutTapped = {},
            onValueChanged = { _, _, _-> },
            onNavBarSelectionChanged = { _ -> },
            onLearnSmdCodesTapped = {},
        )
    }
}
