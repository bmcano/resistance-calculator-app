package com.brandoncano.resistancecalculator.ui.screens.smd

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.brandoncano.resistancecalculator.R
import com.brandoncano.resistancecalculator.to.SmdResistor
import com.brandoncano.resistancecalculator.ui.theme.ResistorCalculatorTheme
import com.brandoncano.resistancecalculator.ui.theme.white
import com.brandoncano.resistancecalculator.util.resistor.formatResistance
import com.brandoncano.sharedcomponents.composables.AppCard
import com.brandoncano.sharedcomponents.composables.AppComponentPreviews
import com.brandoncano.sharedcomponents.text.textStyleLargeTitle
import com.brandoncano.sharedcomponents.text.textStyleTitle

@Composable
fun SmdResistorLayout(resistor: SmdResistor, isError: Boolean) {
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
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = Modifier.clip(RoundedCornerShape(4.dp)),
            contentAlignment = Alignment.Center,
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_smd_resistor),
                contentDescription = stringResource(id = R.string.smd_resistor_content_description),
            )
            Text(
                text = code,
                style = textStyleLargeTitle().white(),
            )
        }
        SmdResistanceText(resistance)
    }
}

@Composable
fun SmdResistanceText(resistance: String) {
    AppCard(modifier = Modifier.padding(top = 12.dp)) {
        Text(
            text = resistance,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(vertical = 8.dp, horizontal = 16.dp),
            style = textStyleTitle(),
        )
    }
}

@AppComponentPreviews
@Composable
private fun SmdResistorLayoutPreview() {
    ResistorCalculatorTheme {
        val resistor = SmdResistor(code = "1R4")
        SmdResistorLayout(resistor, false)
    }
}
