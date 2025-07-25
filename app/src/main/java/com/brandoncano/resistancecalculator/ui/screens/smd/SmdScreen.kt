package com.brandoncano.resistancecalculator.ui.screens.smd

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.outlined.Explicit
import androidx.compose.material.icons.outlined.Looks3
import androidx.compose.material.icons.outlined.Looks4
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.brandoncano.resistancecalculator.R
import com.brandoncano.resistancecalculator.constants.DropdownLists
import com.brandoncano.resistancecalculator.to.SmdResistor
import com.brandoncano.resistancecalculator.ui.composables.AboutAppMenuItem
import com.brandoncano.resistancecalculator.ui.composables.AppTextField
import com.brandoncano.resistancecalculator.ui.composables.BottomScreenSpacer
import com.brandoncano.resistancecalculator.ui.composables.ClearSelectionsMenuItem
import com.brandoncano.resistancecalculator.ui.composables.FeedbackMenuItem
import com.brandoncano.resistancecalculator.ui.composables.M3CardContent
import com.brandoncano.resistancecalculator.ui.composables.MenuIconButton
import com.brandoncano.resistancecalculator.ui.composables.ShareImageMenuItem
import com.brandoncano.resistancecalculator.ui.composables.ShareTextMenuItem
import com.brandoncano.resistancecalculator.ui.composables.TextDropDownMenu
import com.brandoncano.resistancecalculator.ui.composables.m3.M3OutlinedCard
import com.brandoncano.resistancecalculator.ui.composables.m3.M3TopAppBar
import com.brandoncano.resistancecalculator.ui.theme.ResistorCalculatorTheme
import com.brandoncano.sharedcomponents.composables.AppNavigationBar
import com.brandoncano.sharedcomponents.composables.AppScreenPreviews
import com.brandoncano.sharedcomponents.data.NavigationBarOptions
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class) // For TopAppBar
@Composable
fun SmdScreen(
    resistor: SmdResistor,
    isError: Boolean,
    onNavigateBack: () -> Unit,
    onShareImageTapped: (@Composable (() -> Unit)) -> Unit,
    onShareTextTapped: (String) -> Unit,
    onClearSelectionsTapped: () -> Unit,
    onFeedbackTapped: () -> Unit,
    onAboutTapped: () -> Unit,
    onValueChanged: (String) -> Unit,
    onOptionSelected: (String) -> Unit,
    onNavBarSelectionChanged: (Int) -> Unit,
    onLearnSmdCodesTapped: () -> Unit,
) {
    var showMenu by remember { mutableStateOf(false) }
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            M3TopAppBar(
                titleText = stringResource(R.string.smd_title),
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
                            onShareTextTapped.invoke(resistor.toString())
                            showMenu = false
                        }
                        ShareImageMenuItem {
                            onShareImageTapped.invoke {
                                SmdResistorLayout(resistor, isError, 32.dp)
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
        },
        contentWindowInsets = WindowInsets.safeDrawing,
    ) { paddingValues ->
        SmdScreenContent(
            paddingValues = paddingValues,
            resistor = resistor,
            isError = isError,
            onValueChanged = onValueChanged,
            onOptionSelected = onOptionSelected,
            onLearnSmdCodesTapped = onLearnSmdCodesTapped,
        )
    }
}

@Composable
private fun SmdScreenContent(
    paddingValues: PaddingValues,
    resistor: SmdResistor,
    isError: Boolean,
    onValueChanged: (String) -> Unit,
    onOptionSelected: (String) -> Unit,
    onLearnSmdCodesTapped: () -> Unit,
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
        Spacer(modifier = Modifier.padding(top = 32.dp))
        SmdResistorLayout(resistor, isError)
        AppTextField(
            label = stringResource(id = R.string.smd_code_hint),
            modifier = Modifier.padding(top = 32.dp),
            value = resistor.code,
            isError = isError,
            errorMessage = stringResource(id = R.string.error_invalid_code),
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Characters,
                autoCorrectEnabled = false,
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done,
            ),
            onValueChange = { onValueChanged(it.uppercase(Locale.getDefault())) },
        )
        TextDropDownMenu(
            label = stringResource(id = R.string.units_hint),
            modifier = Modifier.padding(top = 12.dp),
            selectedOption = resistor.units,
            items = DropdownLists.UNITS_LIST,
            onOptionSelected = { onOptionSelected(it) },
        )
        Spacer(modifier = Modifier.height(24.dp))
        M3OutlinedCard {
            M3CardContent(
                headline = stringResource(R.string.smd_info_card_title_text),
                subhead = stringResource(R.string.smd_info_card_subhead_text),
                bodyText = stringResource(R.string.smd_info_card_body_text),
                primaryButtonText = stringResource(R.string.smd_info_card_cta_text),
                onPrimaryClick = onLearnSmdCodesTapped,
            )
        }
        BottomScreenSpacer()
    }
}

@AppScreenPreviews
@Composable
private fun SmdScreenPreview() {
    ResistorCalculatorTheme {
        SmdScreen(
            resistor = SmdResistor(),
            isError = false,
            onNavigateBack = {},
            onClearSelectionsTapped = {},
            onShareTextTapped = {},
            onShareImageTapped = {},
            onFeedbackTapped = {},
            onAboutTapped = {},
            onValueChanged = {},
            onOptionSelected = {},
            onNavBarSelectionChanged = { _ -> },
            onLearnSmdCodesTapped = {},
        )
    }
}
