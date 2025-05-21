package com.brandoncano.resistancecalculator.ui.composables

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

// TODO - put into shared library
@Composable
fun BottomScreenSpacer() {
    Spacer(
        modifier = Modifier
            .height(24.dp)
            .navigationBarsPadding(),
    )
}
