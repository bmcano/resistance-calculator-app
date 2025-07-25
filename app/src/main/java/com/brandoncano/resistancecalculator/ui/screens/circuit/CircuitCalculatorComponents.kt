package com.brandoncano.resistancecalculator.ui.screens.circuit

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.brandoncano.resistancecalculator.R

@Composable
fun getResistanceLabelText(sameValues: Boolean, units: String, count: Int): String {
    return if (sameValues) {
        stringResource(id = R.string.circuit_text_field_label, units)
    } else {
        stringResource(id = R.string.circuit_text_field_label_multiple, count, units)
    }
}
