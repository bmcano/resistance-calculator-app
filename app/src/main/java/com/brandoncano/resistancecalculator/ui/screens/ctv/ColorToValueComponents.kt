package com.brandoncano.resistancecalculator.ui.screens.ctv

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Looks3
import androidx.compose.material.icons.outlined.Looks4
import androidx.compose.material.icons.outlined.Looks5
import androidx.compose.material.icons.outlined.Looks6
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.brandoncano.resistancecalculator.R
import com.brandoncano.resistancecalculator.data.ResistorImageColorPair
import com.brandoncano.resistancecalculator.to.ResistorCtv
import com.brandoncano.resistancecalculator.ui.composables.m3.M3Card
import com.brandoncano.resistancecalculator.ui.theme.ResistorCalculatorTheme
import com.brandoncano.resistancecalculator.util.ColorFinder
import com.brandoncano.resistancecalculator.util.resistor.ResistorImageBuilder
import com.brandoncano.resistancecalculator.util.resistor.formatResistance
import com.brandoncano.sharedcomponents.composables.AppComponentPreviews
import com.brandoncano.sharedcomponents.data.NavigationBarOptions

@Composable
fun navigationBarOptions(): List<NavigationBarOptions> {
    return listOf(
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
    )
}

@Composable
fun ResistorLayout(resistor: ResistorCtv, verticalPadding: Dp = 0.dp) {
    val imageColorPairs = remember(resistor) {
        ResistorImageBuilder.execute(resistor)
    }
    Column(
        modifier = Modifier.padding(horizontal = 0.dp, vertical = verticalPadding),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        ResistorRow(imageColorPairs)
        ResistanceText(
            if (resistor.isEmpty()) {
                stringResource(id = R.string.ctv_default_value)
            } else {
                resistor.formatResistance()
            }
        )
    }
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

@Composable
fun ResistanceText(resistance: String) {
    M3Card(modifier = Modifier.padding(top = 16.dp)) {
        Text(
            text = resistance,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(vertical = 12.dp, horizontal = 16.dp),
            style = MaterialTheme.typography.headlineLarge,
        )
    }
}

@AppComponentPreviews
@Composable
private fun ResistorLayoutsPreview() {
    ResistorCalculatorTheme {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            ResistorLayout(ResistorCtv())
            ResistorLayout(ResistorCtv(navBarSelection = 2))
            ResistorLayout(ResistorCtv("Red", "Orange", "", "Yellow", "", "", 0))
            ResistorLayout(ResistorCtv("Red", "Orange", "", "Yellow", "Green", "", 1))
            ResistorLayout(ResistorCtv("Red", "Orange", "Black", "Yellow", "Green", "", 2))
            ResistorLayout(ResistorCtv("Red", "Orange", "Black", "Yellow", "Green", "Blue", 3))
        }
    }
}
