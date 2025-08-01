package com.brandoncano.inductancecalculator.data

import androidx.annotation.DrawableRes

/**
 * Job: Holds the content for each inductor piece of the display image
 */
data class InductorImageColorPairPO(@param:DrawableRes val drawableRes: Int, val color: String)
