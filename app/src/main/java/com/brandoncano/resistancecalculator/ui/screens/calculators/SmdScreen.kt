package com.brandoncano.resistancecalculator.ui.screens.calculators

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.outlined.Explicit
import androidx.compose.material.icons.outlined.Looks3
import androidx.compose.material.icons.outlined.Looks4
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.brandoncano.resistancecalculator.R
import com.brandoncano.resistancecalculator.constants.Lists
import com.brandoncano.resistancecalculator.data.NavigationBarItemPO
import com.brandoncano.resistancecalculator.to.SmdResistor
import com.brandoncano.resistancecalculator.ui.composables.AboutAppMenuItem
import com.brandoncano.resistancecalculator.ui.composables.m3.M3TextField
import com.brandoncano.resistancecalculator.ui.composables.m3.BottomScreenSpacer
import com.brandoncano.resistancecalculator.ui.composables.ClearSelectionsMenuItem
import com.brandoncano.resistancecalculator.ui.composables.FeedbackMenuItem
import com.brandoncano.resistancecalculator.ui.composables.m3.M3CardContent
import com.brandoncano.resistancecalculator.ui.composables.MenuIconButton
import com.brandoncano.resistancecalculator.ui.composables.ShareImageMenuItem
import com.brandoncano.resistancecalculator.ui.composables.ShareTextMenuItem
import com.brandoncano.resistancecalculator.ui.composables.m3.M3DisplayCard
import com.brandoncano.resistancecalculator.ui.composables.m3.M3NavigationBar
import com.brandoncano.resistancecalculator.ui.composables.m3.M3OutlinedCard
import com.brandoncano.resistancecalculator.ui.composables.m3.M3Scaffold
import com.brandoncano.resistancecalculator.ui.composables.m3.M3ScreenColumn
import com.brandoncano.resistancecalculator.ui.composables.m3.M3TextDropDownMenu
import com.brandoncano.resistancecalculator.ui.composables.m3.M3TopAppBar
import com.brandoncano.resistancecalculator.ui.composables.m3.ScreenPreviews
import com.brandoncano.resistancecalculator.ui.theme.ResistorCalculatorTheme
import com.brandoncano.resistancecalculator.ui.theme.black
import com.brandoncano.resistancecalculator.ui.theme.resistor_wire
import com.brandoncano.resistancecalculator.ui.theme.white
import com.brandoncano.resistancecalculator.util.resistor.formatResistance
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
    M3Scaffold(
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
                scrollBehavior = it,
            )
        },
        bottomBar = {
            M3NavigationBar(
                selection = resistor.navBarSelection,
                onClick = { onNavBarSelectionChanged(it) },
                options = listOf(
                    NavigationBarItemPO(
                        label = stringResource(id = R.string.smd_navbar_three_eia),
                        imageVector = Icons.Outlined.Looks3,
                    ),
                    NavigationBarItemPO(
                        label = stringResource(id = R.string.smd_navbar_four_eia),
                        imageVector = Icons.Outlined.Looks4,
                    ),
                    NavigationBarItemPO(
                        label = stringResource(id = R.string.smd_navbar_eia_96),
                        imageVector = Icons.Outlined.Explicit,
                    ),
                ),
            )
        },
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
    M3ScreenColumn(
        paddingValues = paddingValues,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.padding(top = 32.dp))
        SmdResistorLayout(resistor, isError)
        M3TextField(
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
        M3TextDropDownMenu(
            label = stringResource(id = R.string.units_hint),
            modifier = Modifier.padding(top = 12.dp),
            selectedOption = resistor.units,
            items = Lists.UNITS,
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

@Composable
private fun SmdResistorLayout(resistor: SmdResistor, isError: Boolean, verticalPadding: Dp = 0.dp) {
    val code = if (isError) {
        stringResource(id = R.string.error_na)
    }  else {
        resistor.code
    }
    val resistance = when {
        resistor.isEmpty() -> stringResource(id = R.string.smd_default_value)
        isError -> stringResource(id = R.string.error_na)
        else -> resistor.formatResistance()
    }
    Column(
        modifier = Modifier.padding(horizontal = 32.dp, vertical = verticalPadding),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        SmdResistorImage {
            Text(
                text = code,
                style = MaterialTheme.typography.headlineLarge.white(),
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        M3DisplayCard(text = resistance)
    }
}

@Composable
private fun SmdResistorImage(content: @Composable (BoxScope.() -> Unit)) {
    Box(
        modifier = Modifier
            .width(pxToDp(512f))
            .height(pxToDp(224f))
            .clip(RoundedCornerShape(4.dp))
            .background(color = resistor_wire),
        contentAlignment = Alignment.Center,
    ) {
        Box(
            modifier = Modifier
                .width(pxToDp(448f))
                .height(pxToDp(200f))
                .background(color = black),
            contentAlignment = Alignment.Center,
            content = content,
        )
    }
}

@Composable
private fun pxToDp(px: Float): Dp {
    val density = LocalDensity.current
    return with(density) { px.toDp() }
}

@ScreenPreviews
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
