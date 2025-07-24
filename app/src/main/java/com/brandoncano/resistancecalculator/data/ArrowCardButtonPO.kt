package com.brandoncano.resistancecalculator.data

import androidx.compose.ui.graphics.vector.ImageVector

data class ArrowCardButtonPO(
    val text: String,
    val imageVector: ImageVector,
    val onClick: () -> Unit
)
