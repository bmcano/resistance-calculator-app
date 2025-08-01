package com.brandoncano.inductancecalculator.util

import com.brandoncano.inductancecalculator.constants.Colors
import com.brandoncano.inductancecalculator.constants.Symbols
import com.brandoncano.inductancecalculator.to.InductorCtv

object InductanceFormatter {

    private const val ZERO_HENRY = "0 ${Symbols.UH}"

    fun execute(inductor: InductorCtv): String {
        if (inductor.isEmpty()) return "Select colors" // we need this for sharing as text

        val band1 = ValueFinder.getNumeral(inductor.band1)
        val band2 = ValueFinder.getNumeral(inductor.band2)
        val inductance = formatInductance(band1, band2, inductor.band3)
        val tolerance = ValueFinder.getTolerance(inductor.band4)
        return "$inductance $tolerance".trimEnd(' ')
    }

    private fun formatInductance(band1: String, band2: String, band3: String): String {
        val value = (band1 + band2).toIntOrNull() ?: return ZERO_HENRY
        val multiplier = ValueFinder.getMultiplier(band3)

        var inductance = value.times(multiplier)
        val units = when {
            inductance >= 1000 -> Symbols.MH
            else -> Symbols.UH
        }
        while (inductance >= 1000) {
            inductance /= 1000
        }

        val noDecimal = inductance >= 10
        val decimalPrecision = when {
            noDecimal -> "%.0f"
            band3 == Colors.SILVER -> "%.2f"
            else -> "%.1f"
        }
        return "${decimalPrecision.format(inductance)} $units"
    }
}
