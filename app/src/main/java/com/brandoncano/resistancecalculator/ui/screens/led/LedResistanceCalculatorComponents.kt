package com.brandoncano.resistancecalculator.ui.screens.led

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.LightbulbCircle
import androidx.compose.material.icons.outlined.LinearScale
import androidx.compose.material.icons.outlined.Tune
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.brandoncano.resistancecalculator.R
import com.brandoncano.sharedcomponents.data.NavigationBarOptions

@Composable
fun ledNavigationBarOptions(): List<NavigationBarOptions> {
    return listOf(
        NavigationBarOptions(
            label = stringResource(id = R.string.led_navbar_single),
            imageVector = Icons.Outlined.LightbulbCircle,
        ),
        NavigationBarOptions(
            label = stringResource(id = R.string.led_navbar_series),
            imageVector = Icons.Outlined.LinearScale,
        ),
        NavigationBarOptions(
            label = stringResource(id = R.string.led_navbar_parallel),
            imageVector = Icons.Outlined.Tune,
        ),
    )
}
