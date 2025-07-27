package com.brandoncano.resistancecalculator.data

/**
 * Job: Serves as the PO for each entry in the resistor color code table
 */
data class ResistorColorCodeItemPO(
    val colorResId: Int,
    val significantFigures: String,
    val multiplier: String,
    val tolerance: String,
    val tempCoefficient: String,
)
