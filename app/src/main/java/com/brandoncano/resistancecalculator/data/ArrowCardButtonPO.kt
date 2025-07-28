package com.brandoncano.resistancecalculator.data

import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Job: Holds the content for each card button
 */
data class ArrowCardButtonPO(
    val text: String,
    val imageVector: ImageVector,
    val onClick: () -> Unit
)
