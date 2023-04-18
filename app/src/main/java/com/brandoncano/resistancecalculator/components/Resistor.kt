package com.brandoncano.resistancecalculator.components

import com.brandoncano.resistancecalculator.constants.Colors.BLACK
import com.brandoncano.resistancecalculator.util.ColorFinder

/**
 * Job: Holds the colors for each band and performs certain task with the info.
 *
 * Notes:
 *   The data values in the parameters are the 6 bands of the resistor.
 *   The number of bands will determine which resistor is selected, the public attributes not in
 *   the parameters are specifically set for the value-to-color section.
 */
data class Resistor(
    // constructor declares the colors of bands (CtV)
    var sigFigBandOne:   String = "",
    var sigFigBandTwo:   String = "",
    var sigFigBandThree: String = "",
    var multiplierBand:  String = "",
    var toleranceBand:   String = "",
    var ppmBand:         String = ""
) {
    // specific values of the resistor (VtC)
    var resistance = ""
    var units = ""
    var toleranceValue = ""
    var ppmValue = ""

    private var numberOfBands = 4

    fun toColorBandString(isVtC: Boolean = false): String {
        if (isVtC) {
            toleranceBand = ColorFinder.idToColorText(ColorFinder.textToColoredDrawable(toleranceValue))
            ppmBand = ColorFinder.idToColorText(ColorFinder.textToColoredDrawable(ppmValue))
        }

        return when (numberOfBands) {
            4 -> "[ $sigFigBandOne, $sigFigBandTwo, $multiplierBand, $toleranceBand ]"
            5 -> "[ $sigFigBandOne, $sigFigBandTwo, $sigFigBandThree, $multiplierBand, $toleranceBand ]"
            6 -> "[ $sigFigBandOne, $sigFigBandTwo, $sigFigBandThree, $multiplierBand, $toleranceBand, $ppmBand ]"
            else -> ""
        }
    }

    fun getResistanceText(): String {
        return when (numberOfBands) {
            4, 5 -> "$resistance $units $toleranceValue"
            6 -> "$resistance $units $toleranceValue\n$ppmValue".trimEnd('\n')
            else -> ""
        }
    }

    fun getNumberOfBands(): Int = numberOfBands

    fun setNumberOfBands(number: Int) {
        numberOfBands = number.coerceIn(4..6)
    }

    fun isEmpty(): Boolean {
        val isMissingBands = sigFigBandOne.isEmpty() || sigFigBandTwo.isEmpty() || multiplierBand.isEmpty() || toleranceBand.isEmpty()
        return (numberOfBands == 4 && isMissingBands) || (numberOfBands != 4 && (isMissingBands || sigFigBandThree.isEmpty()))
    }

    fun allDigitsZero(): Boolean {
        val fourBand = numberOfBands == 4 && sigFigBandOne == BLACK && sigFigBandTwo == BLACK
        val fiveBand = sigFigBandOne == BLACK && sigFigBandTwo == BLACK && sigFigBandThree == BLACK
        return fourBand || fiveBand
    }
}
