package com.brandoncano.resistancecalculator.data

import androidx.annotation.DrawableRes

/**
 * Job: Holds the content for each resistor piece of the display image
 */
data class ResistorImageColorPair(
    @DrawableRes val drawableRes: Int,
    val color: String
)
