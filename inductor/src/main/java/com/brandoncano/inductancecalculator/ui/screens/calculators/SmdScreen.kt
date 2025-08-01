package com.brandoncano.inductancecalculator.ui.screens.calculators

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.brandoncano.inductancecalculator.R
import com.brandoncano.inductancecalculator.data.SmdTolerance
import com.brandoncano.inductancecalculator.to.SmdInductor
import com.brandoncano.inductancecalculator.ui.composables.AboutAppMenuItem
import com.brandoncano.inductancecalculator.ui.composables.ClearSelectionsMenuItem
import com.brandoncano.inductancecalculator.ui.composables.FeedbackMenuItem
import com.brandoncano.inductancecalculator.ui.composables.MenuIconButton
import com.brandoncano.inductancecalculator.ui.composables.ShareImageMenuItem
import com.brandoncano.inductancecalculator.ui.composables.ShareTextMenuItem
import com.brandoncano.inductancecalculator.ui.theme.InductorCalculatorTheme
import com.brandoncano.inductancecalculator.util.formatInductance
import com.brandoncano.library.m3.BottomScreenSpacer
import com.brandoncano.library.m3.M3CardContent
import com.brandoncano.library.m3.M3DisplayCard
import com.brandoncano.library.m3.M3OutlinedCard
import com.brandoncano.library.m3.M3Scaffold
import com.brandoncano.library.m3.M3ScreenColumn
import com.brandoncano.library.m3.M3TextDropDownMenu
import com.brandoncano.library.m3.M3TextField
import com.brandoncano.library.m3.M3TopAppBar
import com.brandoncano.library.m3.ScreenPreviews
import com.brandoncano.library.theme.white

@OptIn(ExperimentalMaterial3Api::class) // For TopAppBar
@Composable
fun SmdScreen(
    inductor: SmdInductor,
    isError: Boolean,
    onNavigateBack: () -> Unit,
    onShareImageTapped: (@Composable (() -> Unit)) -> Unit,
    onShareTextTapped: (String) -> Unit,
    onClearSelectionsTapped: () -> Unit,
    onFeedbackTapped: () -> Unit,
    onAboutTapped: () -> Unit,
    onValueChanged: (String, String, Boolean) -> Unit,
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
                            onShareTextTapped.invoke(inductor.toString())
                            showMenu = false
                        }
                        ShareImageMenuItem {
                            onShareImageTapped.invoke {
                                SmdInductorLayout(inductor, isError, 32.dp)
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
        SmdScreenContent(
            paddingValues = paddingValues,
            inductor = inductor,
            isError = isError,
            onValueChanged = onValueChanged,
            onLearnSmdCodesTapped = onLearnSmdCodesTapped,
        )
    }
}

@Composable
private fun SmdScreenContent(
    paddingValues: PaddingValues,
    inductor: SmdInductor,
    isError: Boolean,
    onValueChanged: (String, String, Boolean) -> Unit,
    onLearnSmdCodesTapped: () -> Unit,
) {
    M3ScreenColumn(
        paddingValues = paddingValues,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        SmdInductorLayout(inductor, isError)
        M3TextField (
            label = stringResource(id = R.string.smd_hint_code),
            modifier = Modifier.padding(top = 32.dp),
            value = inductor.code,
            isError = isError,
            errorMessage = stringResource(id = R.string.error_invalid_code),
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Characters,
                autoCorrectEnabled = false,
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            onValueChange = { onValueChanged(it, inductor.tolerance, false) },
        )
        M3TextDropDownMenu(
            label = stringResource(id = R.string.ctv_hint_band_4),
            modifier = Modifier.padding(top = 12.dp),
            selectedOption = inductor.tolerance,
            items = SmdTolerance.getLetterList(),
            onOptionSelected = { onValueChanged(inductor.code, it, true) }
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
fun SmdInductorLayout(inductor: SmdInductor, isError: Boolean, verticalPadding: Dp = 0.dp) {
    val code = if (isError) {
        stringResource(id = R.string.error_na)
    }  else {
        inductor.code + inductor.tolerance
    }
    Column(
        modifier = Modifier.padding(horizontal = 32.dp, vertical = verticalPadding),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = Modifier.clip(RoundedCornerShape(4.dp)),
            contentAlignment = Alignment.Center,
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_smd_inductor),
                contentDescription = null,
            )
            Text(
                text = code,
                modifier = Modifier.padding(bottom = 90.dp),
                style = MaterialTheme.typography.headlineLarge.white()
            )
        }
        val text = when {
            inductor.isEmpty() -> stringResource(id = R.string.smd_default_value)
            isError -> stringResource(id = R.string.error_na)
            else -> "${inductor.formatInductance()} ${SmdTolerance.getTolerance(inductor.tolerance)}".trimEnd()
        }
        M3DisplayCard(text)
    }
}

@ScreenPreviews
@Composable
private fun SmdScreenPreview() {
    InductorCalculatorTheme {
        SmdScreen(
            inductor = SmdInductor(),
            isError = false,
            onNavigateBack = {},
            onClearSelectionsTapped = {},
            onShareImageTapped = {},
            onShareTextTapped = {},
            onFeedbackTapped = {},
            onAboutTapped = {},
            onValueChanged = { _, _, _ -> },
            onLearnSmdCodesTapped = {},
        )
    }
}
