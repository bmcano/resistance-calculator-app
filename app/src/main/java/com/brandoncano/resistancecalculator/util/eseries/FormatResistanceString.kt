package com.brandoncano.resistancecalculator.util.eseries

import kotlin.math.abs
import kotlin.math.floor
import kotlin.math.log10
import kotlin.math.max

/**
 * Job: Formats the closest value from the E-Series and formats it properly for the text field.
 *
 * Notes:
 * + We take floor(log10(abs(value))) and add 1 to get how many digits lie to the left of the decimal point.
 * + Then we subtract that count from the total sigFigs‐figure goal and clamp at zero to know how many decimal places we need.
 * + Finally, we format the number with exactly that many decimals.
 */
object FormatResistanceString {

    fun execute(value: Double, sigFigs: Int): String {
        if (value.equals(0.0)) return "0"
        val intDigits = floor(log10(abs(value))).toInt() + 1
        val decimals = max(0, sigFigs - intDigits)
        return "%.${decimals}f".format(value)
    }
}
