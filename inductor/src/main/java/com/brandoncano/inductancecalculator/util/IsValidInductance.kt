package com.brandoncano.inductancecalculator.util

import com.Ostermiller.util.SignificantFigures
import com.brandoncano.inductancecalculator.constants.Symbols

/**
 * Job: Checks if an inductance value is valid for constant checking
 */
object IsValidInductance {

    fun execute(input: String, units: String): Boolean {
        input.ifEmpty { return true }
        if (input.contains(" ")) return false

        val sigFigs: Int
        try {
            sigFigs = SignificantFigures(input).numberSignificantFigures
        } catch (e: NumberFormatException) {
            return false
        }
        return when {
            input.length > 1 && input[0] == '0' && input[1] != '.' || input.isNotEmpty() && input.startsWith(".") -> false
            input.startsWith("0.00") || sigFigs >= 2 && input.startsWith("0.0") -> false
            sigFigs == 3 && input.endsWith(".0") || sigFigs == 2 && input.startsWith("0.") || sigFigs <= 2 && '.' in input -> true
            sigFigs > 2 -> false
            units == Symbols.UH && input.length > 7 -> false
            units == Symbols.MH && input.length > 4 -> false
            else -> true
        }
    }
}
