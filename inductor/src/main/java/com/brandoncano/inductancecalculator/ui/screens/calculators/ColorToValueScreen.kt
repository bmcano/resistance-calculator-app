package com.brandoncano.inductancecalculator.ui.screens.calculators

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.brandoncano.inductancecalculator.R
import com.brandoncano.inductancecalculator.constants.Lists
import com.brandoncano.inductancecalculator.data.InductorImageColorPairPO
import com.brandoncano.inductancecalculator.to.InductorCtv
import com.brandoncano.inductancecalculator.ui.composables.AboutAppMenuItem
import com.brandoncano.inductancecalculator.ui.composables.ClearSelectionsMenuItem
import com.brandoncano.inductancecalculator.ui.composables.FeedbackMenuItem
import com.brandoncano.inductancecalculator.ui.composables.ImageTextDropDownMenu
import com.brandoncano.inductancecalculator.ui.composables.MenuIconButton
import com.brandoncano.inductancecalculator.ui.composables.ShareImageMenuItem
import com.brandoncano.inductancecalculator.ui.composables.ShareTextMenuItem
import com.brandoncano.inductancecalculator.ui.theme.InductorCalculatorTheme
import com.brandoncano.inductancecalculator.util.ColorFinder
import com.brandoncano.inductancecalculator.util.InductorImageBuilder
import com.brandoncano.inductancecalculator.util.formatInductance
import com.brandoncano.inductancecalculator.util.shareableText
import com.brandoncano.library.m3.BottomScreenSpacer
import com.brandoncano.library.m3.M3CardContent
import com.brandoncano.library.m3.M3DisplayCard
import com.brandoncano.library.m3.M3OutlinedCard
import com.brandoncano.library.m3.M3Scaffold
import com.brandoncano.library.m3.M3ScreenColumn
import com.brandoncano.library.m3.M3TopAppBar
import com.brandoncano.library.m3.ScreenPreviews

@OptIn(ExperimentalMaterial3Api::class) // For TopAppBar
@Composable
fun ColorToValueScreen(
    inductor: InductorCtv,
    onNavigateBack: () -> Unit,
    onShareImageTapped: (@Composable (() -> Unit)) -> Unit,
    onShareTextTapped: (String) -> Unit,
    onClearSelectionsTapped: () -> Unit,
    onFeedbackTapped: () -> Unit,
    onAboutTapped: () -> Unit,
    onUpdateBand: (Int, String) -> Unit,
    onLearnColorCodesTapped: () -> Unit,
) {
    var showMenu by remember { mutableStateOf(false) }
    M3Scaffold(
        topBar = {
            M3TopAppBar(
                titleText = stringResource(R.string.ctv_title),
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
                            onShareTextTapped.invoke(inductor.shareableText())
                            showMenu = false
                        }
                        ShareImageMenuItem {
                            onShareImageTapped.invoke {
                                InductorLayout(inductor, 32.dp)
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
        ColorToValueScreenContent(
            paddingValues = paddingValues,
            inductor = inductor,
            onUpdateBand = onUpdateBand,
            onLearnColorCodesTapped = onLearnColorCodesTapped,
        )
    }
}

@Composable
private fun ColorToValueScreenContent(
    paddingValues: PaddingValues,
    inductor: InductorCtv,
    onUpdateBand: (Int, String) -> Unit,
    onLearnColorCodesTapped: () -> Unit,
) {
    M3ScreenColumn(
        paddingValues = paddingValues,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.padding(top = 32.dp))
        InductorLayout(inductor)
        ImageTextDropDownMenu(
            modifier = Modifier.padding(top = 32.dp),
            label = stringResource(R.string.ctv_hint_band_1),
            selectedOption = inductor.band1,
            items = Lists.INDUCTOR_SIG_FIGS_NO_BLACK,
            onOptionSelected = { onUpdateBand(1, it) },
        )
        ImageTextDropDownMenu(
            modifier = Modifier.padding(top = 12.dp),
            label = stringResource(R.string.ctv_hint_band_2),
            selectedOption = inductor.band2,
            items = Lists.INDUCTOR_SIG_FIGS,
            onOptionSelected = { onUpdateBand(2, it) },
        )
        ImageTextDropDownMenu(
            modifier = Modifier.padding(top = 12.dp),
            label = stringResource(R.string.ctv_hint_band_3),
            selectedOption = inductor.band3,
            items = Lists.INDUCTOR_MULTIPLIERS,
            onOptionSelected = { onUpdateBand(3, it) },
        )
        ImageTextDropDownMenu(
            modifier = Modifier.padding(top = 12.dp),
            label = stringResource(R.string.ctv_hint_band_4),
            selectedOption = inductor.band4,
            items = Lists.INDUCTOR_TOLERANCES,
            onOptionSelected = { onUpdateBand(4, it) },
        )
        Spacer(modifier = Modifier.height(24.dp))
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
private fun InductorLayout(inductor: InductorCtv, verticalPadding: Dp = 0.dp) {
    val imageColorPairs = remember(inductor) {
        InductorImageBuilder.execute(inductor)
    }
    Column(
        modifier = Modifier.padding(horizontal = 0.dp, vertical = verticalPadding),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        InductorRow(imageColorPairs)
        Spacer(modifier = Modifier.height(16.dp))
        M3DisplayCard(
            text = if (inductor.isEmpty()) {
                stringResource(id = R.string.ctv_default_value)
            } else {
                inductor.formatInductance()
            },
        )
    }
}

@Composable
fun InductorRow(inductorImages: List<InductorImageColorPairPO>) {
    Row(
        horizontalArrangement = Arrangement.Absolute.Center,
    ) {
        inductorImages.forEach { (drawableRes, color) ->
            val tint = remember(color) { ColorFinder.textToColor(color) }
            Image(
                painter = painterResource(id = drawableRes),
                contentDescription = null,
                colorFilter = ColorFilter.tint(tint),
            )
        }
    }
}

@ScreenPreviews
@Composable
private fun ColorToValueScreenPreview() {
    InductorCalculatorTheme {
        ColorToValueScreen(
            inductor = InductorCtv(),
            onNavigateBack = {},
            onClearSelectionsTapped = {},
            onShareTextTapped = {},
            onShareImageTapped = {},
            onFeedbackTapped = {},
            onAboutTapped = {},
            onUpdateBand = { _, _ -> },
            onLearnColorCodesTapped = {},
        )
    }
}
