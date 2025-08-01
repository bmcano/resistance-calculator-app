package com.brandoncano.inductancecalculator.ui.screens.calculators

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.brandoncano.inductancecalculator.R
import com.brandoncano.inductancecalculator.constants.Lists
import com.brandoncano.inductancecalculator.to.InductorVtc
import com.brandoncano.inductancecalculator.ui.composables.AboutAppMenuItem
import com.brandoncano.inductancecalculator.ui.composables.ClearSelectionsMenuItem
import com.brandoncano.inductancecalculator.ui.composables.FeedbackMenuItem
import com.brandoncano.inductancecalculator.ui.composables.ImageTextDropDownMenu
import com.brandoncano.inductancecalculator.ui.composables.MenuIconButton
import com.brandoncano.inductancecalculator.ui.composables.ShareImageMenuItem
import com.brandoncano.inductancecalculator.ui.composables.ShareTextMenuItem
import com.brandoncano.inductancecalculator.util.InductorImageBuilder
import com.brandoncano.inductancecalculator.util.shareableText
import com.brandoncano.library.m3.BottomScreenSpacer
import com.brandoncano.library.m3.M3CardContent
import com.brandoncano.library.m3.M3DisplayCard
import com.brandoncano.library.m3.M3Divider
import com.brandoncano.library.m3.M3OutlinedCard
import com.brandoncano.library.m3.M3Scaffold
import com.brandoncano.library.m3.M3ScreenColumn
import com.brandoncano.library.m3.M3TextDropDownMenu
import com.brandoncano.library.m3.M3TextField
import com.brandoncano.library.m3.M3TopAppBar
import com.brandoncano.library.m3.ScreenPreviews

@OptIn(ExperimentalMaterial3Api::class) // For TopAppBar
@Composable
fun ValueToColorScreen(
    inductor: InductorVtc,
    isError: Boolean,
    onNavigateBack: () -> Unit,
    onShareImageTapped: (@Composable (() -> Unit)) -> Unit,
    onShareTextTapped: (String) -> Unit,
    onClearSelectionsTapped: () -> Unit,
    onFeedbackTapped: () -> Unit,
    onAboutTapped: () -> Unit,
    onValueChanged: (String) -> Unit,
    onOptionSelected: (String, String) -> Unit,
    onLearnColorCodesTapped: () -> Unit,
) {
    var showMenu by remember { mutableStateOf(false) }
    M3Scaffold(
        topBar = {
            M3TopAppBar(
                titleText = stringResource(R.string.vtc_title),
                navigationIcon = Icons.AutoMirrored.Filled.ArrowBack,
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
                            onShareTextTapped.invoke(inductor.shareableText())
                            showMenu = false
                        }
                        ShareImageMenuItem {
                            onShareImageTapped.invoke {
                                InductorLayout(inductor, isError, 32.dp)
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
    ) { paddingValues ->
        ValueToColorScreenContent(
            paddingValues = paddingValues,
            inductor = inductor,
            isError = isError,
            onValueChanged = onValueChanged,
            onOptionSelected = onOptionSelected,
            onLearnColorCodesTapped = onLearnColorCodesTapped,
        )
    }
}

@Composable
private fun ValueToColorScreenContent(
    paddingValues: PaddingValues,
    inductor: InductorVtc,
    isError: Boolean,
    onValueChanged: (String) -> Unit,
    onOptionSelected: (String, String) -> Unit,
    onLearnColorCodesTapped: () -> Unit,
) {
    M3ScreenColumn(
        paddingValues = paddingValues,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        InductorLayout(inductor, isError)
        M3TextField(
            label = stringResource(id = R.string.vtc_hint_inductance),
            modifier = Modifier.padding(top = 32.dp),
            value = inductor.inductance,
            isError = isError,
            errorMessage = stringResource(id = R.string.error_invalid_inductance),
            onValueChange = { onValueChanged(it) }
        )
        M3TextDropDownMenu(
            label = stringResource(id = R.string.vtc_hint_units),
            modifier = Modifier.padding(top = 12.dp),
            selectedOption = inductor.units,
            items = Lists.UNITS,
            onOptionSelected = { onOptionSelected(it, inductor.tolerance) },
        )
        ImageTextDropDownMenu(
            modifier = Modifier.padding(top = 12.dp),
            label = stringResource(R.string.ctv_hint_band_4),
            selectedOption = inductor.tolerance,
            items = Lists.INDUCTOR_TOLERANCES,
            isValueToColor = true,
            onOptionSelected = { onOptionSelected(inductor.units, it) },
        )
        M3Divider(modifier = Modifier.padding(vertical = 24.dp))
        M3OutlinedCard {
            M3CardContent(
                headline = stringResource(R.string.ctv_info_card_title_text),
                subhead = stringResource(R.string.ctv_info_card_subhead_text),
                bodyText = stringResource(R.string.ctv_info_card_body_text),
                primaryButtonText = stringResource(R.string.ctv_info_card_cta_text),
                onPrimaryClick = onLearnColorCodesTapped,
            )
        }
        BottomScreenSpacer()
    }
}

@Composable
private fun InductorLayout(inductor: InductorVtc, isError: Boolean, verticalPadding: Dp = 0.dp) {
    val imageColorPairs = remember(inductor) {
        InductorImageBuilder.execute(inductor)
    }
    val text = when {
        inductor.isEmpty() -> stringResource(id = R.string.vtc_default_value)
        isError -> stringResource(id = R.string.error_na)
        else -> inductor.getInductanceValue()
    }
    Column(
        modifier = Modifier.padding(horizontal = 0.dp, vertical = verticalPadding),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        InductorRow(imageColorPairs)
        Spacer(modifier = Modifier.height(16.dp))
        M3DisplayCard(text)
    }
}

@ScreenPreviews
@Composable
private fun ValueToColorScreenPreview() {
    ValueToColorScreen(
        inductor = InductorVtc(),
        isError = false,
        onNavigateBack = {},
        onClearSelectionsTapped = {},
        onShareTextTapped = {},
        onShareImageTapped = {},
        onFeedbackTapped = {},
        onAboutTapped = {},
        onValueChanged = {},
        onOptionSelected = { _, _ -> },
        onLearnColorCodesTapped = {},
    )
}
