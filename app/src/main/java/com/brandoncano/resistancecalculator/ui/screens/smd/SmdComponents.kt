package com.brandoncano.resistancecalculator.ui.screens.smd

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.brandoncano.resistancecalculator.R
import com.brandoncano.resistancecalculator.to.SmdResistor
import com.brandoncano.resistancecalculator.ui.screens.ctv.ResistanceText
import com.brandoncano.resistancecalculator.ui.theme.ResistorCalculatorTheme
import com.brandoncano.resistancecalculator.ui.theme.black
import com.brandoncano.resistancecalculator.ui.theme.resistor_wire
import com.brandoncano.resistancecalculator.ui.theme.white
import com.brandoncano.resistancecalculator.util.resistor.formatResistance
import com.brandoncano.sharedcomponents.composables.AppComponentPreviews

@Composable
fun SmdResistorLayout(resistor: SmdResistor, isError: Boolean, verticalPadding: Dp = 0.dp) {
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
        ResistanceText(resistance)
    }
}

@Composable
fun SmdResistorImage(content: @Composable (BoxScope.() -> Unit)) {
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
fun pxToDp(px: Float): Dp {
    val density = LocalDensity.current
    return with(density) { px.toDp() }
}

@AppComponentPreviews
@Composable
private fun SmdResistorLayoutPreview() {
    ResistorCalculatorTheme {
        val resistor = SmdResistor(code = "1R4")
        SmdResistorLayout(resistor, false)
    }
}
