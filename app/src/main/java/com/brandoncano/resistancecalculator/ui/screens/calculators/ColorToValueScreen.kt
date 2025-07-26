package com.brandoncano.resistancecalculator.ui.screens.calculators

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
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
import androidx.compose.material.icons.outlined.Looks3
import androidx.compose.material.icons.outlined.Looks4
import androidx.compose.material.icons.outlined.Looks5
import androidx.compose.material.icons.outlined.Looks6
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
import com.brandoncano.resistancecalculator.R
import com.brandoncano.resistancecalculator.constants.Lists
import com.brandoncano.resistancecalculator.data.NavigationBarItemPO
import com.brandoncano.resistancecalculator.data.ResistorImageColorPair
import com.brandoncano.resistancecalculator.to.ResistorCtv
import com.brandoncano.resistancecalculator.ui.composables.AboutAppMenuItem
import com.brandoncano.resistancecalculator.ui.composables.BottomScreenSpacer
import com.brandoncano.resistancecalculator.ui.composables.ClearSelectionsMenuItem
import com.brandoncano.resistancecalculator.ui.composables.FeedbackMenuItem
import com.brandoncano.resistancecalculator.ui.composables.ImageTextDropDownMenu
import com.brandoncano.resistancecalculator.ui.composables.MenuIconButton
import com.brandoncano.resistancecalculator.ui.composables.ShareImageMenuItem
import com.brandoncano.resistancecalculator.ui.composables.ShareTextMenuItem
import com.brandoncano.resistancecalculator.ui.composables.m3.M3CardContent
import com.brandoncano.resistancecalculator.ui.composables.m3.M3DisplayCard
import com.brandoncano.resistancecalculator.ui.composables.m3.M3NavigationBar
import com.brandoncano.resistancecalculator.ui.composables.m3.M3OutlinedCard
import com.brandoncano.resistancecalculator.ui.composables.m3.M3Scaffold
import com.brandoncano.resistancecalculator.ui.composables.m3.M3ScreenColumn
import com.brandoncano.resistancecalculator.ui.composables.m3.M3TopAppBar
import com.brandoncano.resistancecalculator.ui.theme.ResistorCalculatorTheme
import com.brandoncano.resistancecalculator.util.ColorFinder
import com.brandoncano.resistancecalculator.util.resistor.ResistorImageBuilder
import com.brandoncano.resistancecalculator.util.resistor.formatResistance
import com.brandoncano.resistancecalculator.util.resistor.shareableText
import com.brandoncano.sharedcomponents.composables.AppScreenPreviews
import kotlin.collections.forEach

@OptIn(ExperimentalMaterial3Api::class) // For TopAppBar
@Composable
fun ColorToValueScreen(
    resistor: ResistorCtv,
    onNavigateBack: () -> Unit,
    onShareImageTapped: (@Composable (() -> Unit)) -> Unit,
    onShareTextTapped: (String) -> Unit,
    onClearSelectionsTapped: () -> Unit,
    onFeedbackTapped: () -> Unit,
    onAboutTapped: () -> Unit,
    onUpdateBand: (Int, String) -> Unit,
    onNavBarSelectionChanged: (Int) -> Unit,
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
                            onShareTextTapped.invoke(resistor.shareableText())
                            showMenu = false
                        }
                        ShareImageMenuItem {
                            onShareImageTapped.invoke {
                                ResistorLayout(resistor, 32.dp)
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
                options = navigationBarItemPOs(),
            )
        },
    ) { paddingValues ->
        ColorToValueScreenContent(
            paddingValues = paddingValues,
            resistor = resistor,
            onUpdateBand = onUpdateBand,
            onLearnColorCodesTapped = onLearnColorCodesTapped,
        )
    }
}

@Composable
private fun ColorToValueScreenContent(
    paddingValues: PaddingValues,
    resistor: ResistorCtv,
    onUpdateBand: (Int, String) -> Unit,
    onLearnColorCodesTapped: () -> Unit,
) {
    val navBarSelection = resistor.navBarSelection
    M3ScreenColumn(
        paddingValues = paddingValues,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.padding(top = 32.dp))
        ResistorLayout(resistor)
        ImageTextDropDownMenu(
            modifier = Modifier.padding(top = 32.dp),
            label = stringResource(R.string.number_band_hint1),
            selectedOption = resistor.band1,
            items = Lists.RESISTOR_SIG_FIGS_NO_BLACK,
            onOptionSelected = { onUpdateBand(1, it) },
        )
        ImageTextDropDownMenu(
            modifier = Modifier.padding(top = 12.dp),
            label = stringResource(R.string.number_band_hint2),
            selectedOption = resistor.band2,
            items = Lists.RESISTOR_SIG_FIGS,
            onOptionSelected = { onUpdateBand(2, it) },
        )
        AnimatedVisibility(
            visible = navBarSelection == 2 || navBarSelection == 3,
            enter = fadeIn(animationSpec = tween(durationMillis = 300)) + expandVertically(),
            exit = fadeOut(animationSpec = tween(durationMillis = 300)) + shrinkVertically()
        ) {
            ImageTextDropDownMenu(
                modifier = Modifier.padding(top = 12.dp),
                label = stringResource(R.string.number_band_hint3),
                selectedOption = resistor.band3,
                items = Lists.RESISTOR_SIG_FIGS,
                onOptionSelected = { onUpdateBand(3, it) },
            )
        }
        ImageTextDropDownMenu(
            modifier = Modifier.padding(top = 12.dp),
            label = stringResource(R.string.multiplier_band_hint),
            selectedOption = resistor.band4,
            items = Lists.RESISTOR_MULTIPLIERS,
            onOptionSelected = { onUpdateBand(4, it) },
        )
        AnimatedVisibility(
            visible = navBarSelection != 0,
            enter = fadeIn(animationSpec = tween(durationMillis = 300)) + expandVertically(),
            exit = fadeOut(animationSpec = tween(durationMillis = 300)) + shrinkVertically()
        ) {
            ImageTextDropDownMenu(
                modifier = Modifier.padding(top = 12.dp),
                label = stringResource(R.string.tolerance_band_hint),
                selectedOption = resistor.band5,
                items = Lists.RESISTOR_TOLERANCES,
                onOptionSelected = { onUpdateBand(5, it) },
            )
        }
        AnimatedVisibility(
            visible = navBarSelection == 3,
            enter = fadeIn(animationSpec = tween(durationMillis = 300)) + expandVertically(),
            exit = fadeOut(animationSpec = tween(durationMillis = 300)) + shrinkVertically()
        ) {
            ImageTextDropDownMenu(
                modifier = Modifier.padding(top = 12.dp),
                label = stringResource(R.string.ppm_band_hint),
                selectedOption = resistor.band6,
                items = Lists.RESISTOR_PPM,
                onOptionSelected = { onUpdateBand(6, it) },
            )
        }
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
private fun ResistorLayout(resistor: ResistorCtv, verticalPadding: Dp = 0.dp) {
    val imageColorPairs = remember(resistor) {
        ResistorImageBuilder.execute(resistor)
    }
    Column(
        modifier = Modifier.padding(horizontal = 0.dp, vertical = verticalPadding),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        ResistorRow(imageColorPairs)
        Spacer(modifier = Modifier.height(16.dp))
        M3DisplayCard(
            text = if (resistor.isEmpty()) {
                stringResource(id = R.string.ctv_default_value)
            } else {
                resistor.formatResistance()
            },
        )
    }
}

/**
 * Note: Used for both CtV and VtC
 */
@Composable
fun navigationBarItemPOs(): List<NavigationBarItemPO> {
    return listOf(
        NavigationBarItemPO(
            label = stringResource(id = R.string.navbar_three_band),
            imageVector = Icons.Outlined.Looks3,
        ),
        NavigationBarItemPO(
            label = stringResource(id = R.string.navbar_four_band),
            imageVector = Icons.Outlined.Looks4,
        ),
        NavigationBarItemPO(
            label = stringResource(id = R.string.navbar_five_band),
            imageVector = Icons.Outlined.Looks5,
        ),
        NavigationBarItemPO(
            label = stringResource(id = R.string.navbar_six_band),
            imageVector = Icons.Outlined.Looks6,
        ),
    )
}

@Composable
fun ResistorRow(resistorImages: List<ResistorImageColorPair>) {
    Row(
        horizontalArrangement = Arrangement.Absolute.Center,
    ) {
        resistorImages.forEach { (drawableRes, color) ->
            val tint = remember(color) { ColorFinder.textToColor(color) }
            Image(
                painter = painterResource(id = drawableRes),
                contentDescription = null,
                colorFilter = ColorFilter.tint(tint),
            )
        }
    }
}

@AppScreenPreviews
@Composable
private fun ColorToValueScreen4BandPreview() {
    ResistorCalculatorTheme {
        ColorToValueScreen(
            resistor = ResistorCtv(),
            onNavigateBack = {},
            onClearSelectionsTapped = {},
            onShareImageTapped = {},
            onShareTextTapped = {},
            onFeedbackTapped = {},
            onAboutTapped = {},
            onUpdateBand = { _, _ -> },
            onNavBarSelectionChanged = { _ -> },
            onLearnColorCodesTapped = {},
        )
    }
}

@AppScreenPreviews
@Composable
private fun ColorToValueScreen3BandPreview() {
    ResistorCalculatorTheme {
        ColorToValueScreen(
            resistor = ResistorCtv(navBarSelection = 0),
            onNavigateBack = {},
            onClearSelectionsTapped = {},
            onShareImageTapped = {},
            onShareTextTapped = {},
            onFeedbackTapped = {},
            onAboutTapped = {},
            onUpdateBand = { _, _ -> },
            onNavBarSelectionChanged = { _ -> },
            onLearnColorCodesTapped = {},
        )
    }
}

@AppScreenPreviews
@Composable
private fun ColorToValueScreen5BandPreview() {
    ResistorCalculatorTheme {
        ColorToValueScreen(
            resistor = ResistorCtv(navBarSelection = 2),
            onNavigateBack = {},
            onClearSelectionsTapped = {},
            onShareImageTapped = {},
            onShareTextTapped = {},
            onFeedbackTapped = {},
            onAboutTapped = {},
            onUpdateBand = { _, _ -> },
            onNavBarSelectionChanged = { _ -> },
            onLearnColorCodesTapped = {},
        )
    }
}

@AppScreenPreviews
@Composable
private fun ColorToValueScreen6BandPreview() {
    ResistorCalculatorTheme {
        ColorToValueScreen(
            resistor = ResistorCtv(navBarSelection = 3),
            onNavigateBack = {},
            onShareImageTapped = {},
            onShareTextTapped = {},
            onClearSelectionsTapped = {},
            onFeedbackTapped = {},
            onAboutTapped = {},
            onUpdateBand = { _, _ -> },
            onNavBarSelectionChanged = { _ -> },
            onLearnColorCodesTapped = {},
        )
    }
}
