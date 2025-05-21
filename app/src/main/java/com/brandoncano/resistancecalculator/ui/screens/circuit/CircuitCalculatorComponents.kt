package com.brandoncano.resistancecalculator.ui.screens.circuit

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.brandoncano.resistancecalculator.R
import com.brandoncano.sharedcomponents.composables.AppCard
import com.brandoncano.sharedcomponents.text.textStyleLargeTitle

@Composable
fun CircuitResistanceText(resistance: String) {
    AppCard {
        Text(
            text = resistance,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(vertical = 8.dp, horizontal = 16.dp),
            style = textStyleLargeTitle(),
        )
    }
}

@Composable
fun getResistanceLabelText(sameValues: Boolean, units: String, count: Int): String {
    return if (sameValues) {
        stringResource(id = R.string.circuit_text_field_label, units)
    } else {
        stringResource(id = R.string.circuit_text_field_label_multiple, count, units)
    }
}
