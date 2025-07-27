package com.brandoncano.resistancecalculator.ui.composables.m3

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun BottomScreenSpacer(bottomSpacing: Dp = 24.dp) {
    Spacer(
        modifier = Modifier
            .height(bottomSpacing)
            .navigationBarsPadding(),
    )
}
