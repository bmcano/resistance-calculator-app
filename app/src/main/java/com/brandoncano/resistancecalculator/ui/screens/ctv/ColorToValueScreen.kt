package com.brandoncano.resistancecalculator.ui.screens.ctv

import android.graphics.Picture
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.outlined.Lightbulb
import androidx.compose.material.icons.outlined.Looks3
import androidx.compose.material.icons.outlined.Looks4
import androidx.compose.material.icons.outlined.Looks5
import androidx.compose.material.icons.outlined.Looks6
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
import androidx.compose.ui.unit.dp
import com.brandoncano.resistancecalculator.R
import com.brandoncano.resistancecalculator.constants.DropdownLists
import com.brandoncano.resistancecalculator.constants.Links
import com.brandoncano.resistancecalculator.to.ResistorCtv
import com.brandoncano.resistancecalculator.ui.composables.ImageTextDropDownMenu
import com.brandoncano.resistancecalculator.ui.theme.ResistorCalculatorTheme
import com.brandoncano.resistancecalculator.util.Sdk
import com.brandoncano.resistancecalculator.util.resistor.shareableText
import com.brandoncano.sharedcomponents.composables.AboutAppMenuItem
import com.brandoncano.sharedcomponents.composables.AppMenuTopAppBar
import com.brandoncano.sharedcomponents.composables.AppNavigationBar
import com.brandoncano.sharedcomponents.composables.AppScreenPreviews
import com.brandoncano.sharedcomponents.composables.ClearSelectionsMenuItem
import com.brandoncano.sharedcomponents.composables.FeedbackMenuItem
import com.brandoncano.sharedcomponents.composables.ShareImageMenuItem
import com.brandoncano.sharedcomponents.composables.ShareTextMenuItem
import com.brandoncano.sharedcomponents.data.NavigationBarOptions

@Composable
fun ColorToValueScreen(
    openMenu: MutableState<Boolean>,
    reset: MutableState<Boolean>,
    resistor: ResistorCtv,
    onNavigateBack: () -> Unit,
    onClearSelectionsTapped: () -> Unit,
    onAboutTapped: () -> Unit,
    onUpdateBand: (Int, String) -> Unit,
    onNavBarSelectionChanged: (Int) -> Unit,
    onLearnColorCodesTapped: () -> Unit,
) {
    // TODO - find a better way to share the image, can make a nicer looking component that's not directly on the screen
    val picture = remember { Picture() }
    Scaffold(
        topBar = {
            AppMenuTopAppBar(
                titleText = stringResource(R.string.ctv_title),
                interactionSource = remember { MutableInteractionSource() },
                showMenu = openMenu,
                navigationIcon = Icons.Filled.Close,
                onNavigateBack = onNavigateBack,
            ) {
                ClearSelectionsMenuItem(onClearSelectionsTapped)
                ShareTextMenuItem(
                    text = resistor.shareableText(),
                    showMenu = openMenu,
                )
                if (Sdk.isAtLeastAndroid7()) {
                    ShareImageMenuItem(
                        applicationId = Links.APPLICATION_ID,
                        showMenu = openMenu,
                        picture = picture,
                    )
                }
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
                        label = stringResource(id = R.string.navbar_three_band),
                        imageVector = Icons.Outlined.Looks3,
                    ),
                    NavigationBarOptions(
                        label = stringResource(id = R.string.navbar_four_band),
                        imageVector = Icons.Outlined.Looks4,
                    ),
                    NavigationBarOptions(
                        label = stringResource(id = R.string.navbar_five_band),
                        imageVector = Icons.Outlined.Looks5,
                    ),
                    NavigationBarOptions(
                        label = stringResource(id = R.string.navbar_six_band),
                        imageVector = Icons.Outlined.Looks6,
                    ),
                ),
            )
        },
        contentWindowInsets = WindowInsets.safeDrawing,
    ) { paddingValues ->
        ColorToValueScreenContent(
            paddingValues = paddingValues,
            picture = picture,
            reset = reset,
            resistor = resistor,
            onUpdateBand = onUpdateBand,
            onLearnColorCodesTapped = onLearnColorCodesTapped,
        )
    }
}

@Composable
private fun ColorToValueScreenContent(
    paddingValues: PaddingValues,
    picture: Picture,
    reset: MutableState<Boolean>,
    resistor: ResistorCtv,
    onUpdateBand: (Int, String) -> Unit,
    onLearnColorCodesTapped: () -> Unit,
) {
    val sidePadding = dimensionResource(R.dimen.app_side_padding)
    val navBarSelection = resistor.navBarSelection
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(paddingValues)
            .padding(horizontal = sidePadding),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.padding(top = 32.dp))
        ResistorDisplay(picture, resistor)
        ImageTextDropDownMenu(
            modifier = Modifier.padding(top = 32.dp),
            label = R.string.number_band_hint1,
            selectedOption = resistor.band1,
            items = DropdownLists.NUMBER_LIST_NO_BLACK,
            reset = reset.value,
            onOptionSelected = { onUpdateBand(1, it) },
        )
        ImageTextDropDownMenu(
            modifier = Modifier.padding(top = 12.dp),
            label = R.string.number_band_hint2,
            selectedOption = resistor.band2,
            items = DropdownLists.NUMBER_LIST,
            reset = reset.value,
            onOptionSelected = { onUpdateBand(2, it) },
        )
        AnimatedVisibility(
            visible = navBarSelection == 2 || navBarSelection == 3,
            enter = fadeIn(animationSpec = tween(durationMillis = 300)) + expandVertically(),
            exit = fadeOut(animationSpec = tween(durationMillis = 300)) + shrinkVertically()
        ) {
            ImageTextDropDownMenu(
                modifier = Modifier.padding(top = 12.dp),
                label = R.string.number_band_hint3,
                selectedOption = resistor.band3,
                items = DropdownLists.NUMBER_LIST,
                reset = reset.value,
                onOptionSelected = { onUpdateBand(3, it) },
            )
        }
        ImageTextDropDownMenu(
            modifier = Modifier.padding(top = 12.dp),
            label = R.string.multiplier_band_hint,
            selectedOption = resistor.band4,
            items = DropdownLists.MULTIPLIER_LIST,
            reset = reset.value,
            onOptionSelected = { onUpdateBand(4, it) },
        )
        AnimatedVisibility(
            visible = navBarSelection != 0,
            enter = fadeIn(animationSpec = tween(durationMillis = 300)) + expandVertically(),
            exit = fadeOut(animationSpec = tween(durationMillis = 300)) + shrinkVertically()
        ) {
            ImageTextDropDownMenu(
                modifier = Modifier.padding(top = 12.dp),
                label = R.string.tolerance_band_hint,
                selectedOption = resistor.band5,
                items = DropdownLists.TOLERANCE_LIST,
                reset = reset.value,
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
                label = R.string.ppm_band_hint,
                selectedOption = resistor.band6,
                items = DropdownLists.PPM_LIST,
                reset = reset.value,
                onOptionSelected = { onUpdateBand(6, it) },
            )
        }
        Spacer(modifier = Modifier.height(24.dp))
        AppActionCard(
            icon = Icons.Outlined.Lightbulb,
            iconTint = MaterialTheme.colorScheme.primary,
            cardTitle = stringResource(R.string.ctv_info_card_title_text),
            cardBody = stringResource(R.string.ctv_info_card_body_text),
            leftActionButton = CardAction(
                buttonLabel = stringResource(R.string.ctv_info_card_cta_text),
                onClick = onLearnColorCodesTapped,
            )
        )
        Spacer(modifier = Modifier.height(24.dp))
    }
}

@AppScreenPreviews
@Composable
private fun ColorToValueScreen4BandPreview() {
    ResistorCalculatorTheme {
        ColorToValueScreen(
            openMenu = remember { mutableStateOf(false) },
            reset = remember { mutableStateOf(false) },
            resistor = ResistorCtv(),
            onNavigateBack = {},
            onClearSelectionsTapped = {},
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
            openMenu = remember { mutableStateOf(false) },
            reset = remember { mutableStateOf(false) },
            resistor = ResistorCtv(navBarSelection = 0),
            onNavigateBack = {},
            onClearSelectionsTapped = {},
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
            openMenu = remember { mutableStateOf(false) },
            reset = remember { mutableStateOf(false) },
            resistor = ResistorCtv(navBarSelection = 2),
            onNavigateBack = {},
            onClearSelectionsTapped = {},
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
            openMenu = remember { mutableStateOf(false) },
            reset = remember { mutableStateOf(false) },
            resistor = ResistorCtv(navBarSelection = 3),
            onNavigateBack = {},
            onClearSelectionsTapped = {},
            onAboutTapped = {},
            onUpdateBand = { _, _ -> },
            onNavBarSelectionChanged = { _ -> },
            onLearnColorCodesTapped = {},
        )
    }
}
