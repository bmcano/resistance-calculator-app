package com.brandoncano.resistancecalculator.util.resistor


import com.brandoncano.resistancecalculator.to.ResistorVtc
import com.brandoncano.resistancecalculator.util.ColorFinder
import com.brandoncano.resistancecalculator.util.MultiplierFromUnits
import java.util.Locale
import com.brandoncano.resistancecalculator.constants.Colors as C

/**
 * Job: Formats the resistor based resistance that has been entered (VtC).
 */
object ResistorFormatter {

    private val LOCALE = Locale.US
    private val colorsMap = mapOf(
        0 to C.SILVER, 1 to C.GOLD, 2 to C.BLACK, 3 to C.BROWN, 4 to C.RED, 5 to C.ORANGE,
        6 to C.YELLOW, 7 to C.GREEN, 8 to C.BLUE, 9 to C.VIOLET, 10 to C.GRAY, 11 to C.WHITE,
    )

    fun generateResistor(resistor: ResistorVtc) {
        if (resistor.isEmpty()) return
        val resistance = resistor.resistance
        val multiplier = MultiplierFromUnits.execute(resistor.units)
        val resLong: Long? = resistance.toLongOrNull()?.times(multiplier)
        val resDouble: Double = resistance.toDoubleOrNull()?.times(multiplier) ?: return
        if (resLong == null) {
            resistor.band4 = decimalInputMultiplier(resistor, resDouble)
        } else {
            resistor.band4 = numericalInputMultiplier(resistor, resLong)
        }

        // remove decimal and check leading zeros
        val numberBands = arrayOf(0, 0, 0)
        val isThreeFourBand = resistor.isThreeFourBand()
        val formattedResistance = checkLeadingZeros(isThreeFourBand, String.format(LOCALE, "%.2f", resDouble))
        formattedResistance.forEachIndexed { index, digit ->
            // if a invalid character makes its way through, set to -1 for a blank band
            if (index < 3) numberBands[index] = digit.digitToIntOrNull() ?: -1
        }

        resistor.band1 = ColorFinder.numberToText(numberBands[0])
        resistor.band2 = ColorFinder.numberToText(numberBands[1])
        resistor.band3 = if (!isThreeFourBand) {
            ColorFinder.numberToText(numberBands[2])
        } else ""
    }

    private fun decimalInputMultiplier(resistor: ResistorVtc, resistance: Double): String {
        val res = String.format(LOCALE, "%.2f", resistance)
        var index = res.indexOf(".")
        if (index == -1) index = res.length
        if (res.startsWith("0")) {
            index = 0
        }
        if (!resistor.isThreeFourBand() && !res.startsWith("0.")) index--
        return colorsMap[index] ?: C.RESISTOR_BEIGE
    }

    private fun numericalInputMultiplier(resistor: ResistorVtc, resistance: Long): String {
        var length = resistance.toString().length
        if (!resistor.isThreeFourBand()) length--
        return colorsMap[length] ?: C.RESISTOR_BEIGE
    }

    private fun checkLeadingZeros(isThreeFourBand: Boolean, value: String): String {
        val values = value.replace(".", "")
        val numbers = values.toCharArray()
        val validSize = numbers.size == 2 || numbers.size == 3 || numbers.size == 4
        if (isThreeFourBand && validSize && numbers[0] == '0') {
            return values.substring(1, values.length)
        }
        return values
    }
}
