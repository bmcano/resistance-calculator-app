package com.brandoncano.inductancecalculator.util

import com.brandoncano.inductancecalculator.constants.Colors as C
import com.brandoncano.inductancecalculator.constants.Symbols
import com.brandoncano.inductancecalculator.to.InductorVtc
import java.util.Locale

/**
 * Job: takes an inductance value and formats the inductor color bands
 */
object InductorFormatter {

    private val LOCALE = Locale.US
    private val colorsMap = mapOf(
        0 to C.SILVER, 1 to C.GOLD, 2 to C.BLACK, 3 to C.BROWN,
        4 to C.RED, 5 to C.ORANGE, 6 to C.YELLOW,
    )

    fun execute(inductor: InductorVtc) {
        if (inductor.isEmpty()) return
        val inductance = inductor.inductance
        val multiplier = when (inductor.units) {
            Symbols.MH -> 1000
            else -> 1 // μH
        }
        val inductDouble = inductance.toDoubleOrNull()?.times(multiplier) ?: return
        inductor.band3 = decimalInputMultiplier(inductDouble)

        // remove decimal and check leading zeros
        val numberBands = arrayOf(0, 0, 0)
        val formattedInductance = checkLeadingZeros(String.format(LOCALE, "%.2f", inductDouble))
        formattedInductance.forEachIndexed { index, digit ->
            // if a invalid character makes its way through, set to -1 for a blank band
            if (index < 3) numberBands[index] = digit.digitToIntOrNull() ?: -1
        }

        inductor.band1 = ColorFinder.numberToText(numberBands[0])
        inductor.band2 = ColorFinder.numberToText(numberBands[1])
    }

    private fun decimalInputMultiplier(inductance: Double): String {
        val induct = String.format(LOCALE, "%.2f", inductance)
        var index = induct.indexOf(".")
        if (index == -1) index = induct.length
        if (induct.startsWith("0")) {
            index = 0
        }
        if (!induct.startsWith("0.")) index--
        return colorsMap[index] ?: C.INDUCTOR_GREEN
    }

    private fun checkLeadingZeros(value: String): String {
        val values = value.replace(".", "")
        val numbers = values.toCharArray()
        val validSize = numbers.size == 2 || numbers.size == 3 || numbers.size == 4
        if (validSize && numbers[0] == '0') {
            return values.substring(1, values.length)
        }
        return values
    }
}
