package com.brandoncano.resistancecalculator.util.resistor

import com.brandoncano.resistancecalculator.constants.Symbols
import com.brandoncano.resistancecalculator.to.ResistorVtc
import com.brandoncano.resistancecalculator.util.MultiplierFromUnits

/**
 * Job: Checks if a user inputted resistance is valid.
 *
 * Note: Valid inputs are written next to each Regex, '{}' represent missing value (reads as 0).
 */
object IsValidResistance {

    private val validThreeFourBandPatterns = listOf(
        Regex("""^\d\.?$"""),       // x{} or x.
        Regex("""^\d\.\d$"""),      // x.y
        Regex("""^\d\d0*$"""),      // xy or xy{..}
        Regex("""^0\.\d\d?$""")     // 0.xy or 0.x{}
    )

    private val validFiveSixBandPatterns = listOf(
        Regex("""^\d\.?$"""),       // x{}{} or x.
        Regex("""^\d\d\.?$"""),     // xy{} or xy.
        Regex("""^\d\.\d\d?$"""),   // x.yz or x.y{}
        Regex("""^\d\d\.\d$"""),    // xy.z
        Regex("""^\d\d\d0*$"""),    // xyz or xyz{..}
        Regex("""^0\.\d\d\d?$""")   // 0.xyz or 0.xy{}
    )

    fun execute(resistor: ResistorVtc, input: String): Boolean {
        input.ifEmpty { return true }
        if (input.length == 1 && input.startsWith("0")) return true
        if (input.length == 2 && input.startsWith("0.")) return true
        if (input.length >= 2 && input.startsWith('0') && input[1] != '.') return false

        val isThreeFourBand = resistor.isThreeFourBand()
        val patterns = if (isThreeFourBand) validThreeFourBandPatterns else validFiveSixBandPatterns
        val isValidFormat = patterns.any { it.matches(input) }
        if (!isValidFormat) return false
        if (!isThreeFourBand && resistor.units == Symbols.OHMS && Regex("""^0\.\d\d\d$""").matches(input)) {
            return false
        }

        val minValue = 0.10
        val maxValue = if (isThreeFourBand) 99_000_000_000 else 999_000_000_000
        val resistance = input.toDoubleOrNull() ?: return false
        val multiplier = MultiplierFromUnits.execute(resistor.units)
        val resistanceInOhms = resistance * multiplier
        return (resistanceInOhms >= minValue) && (resistanceInOhms <= maxValue)
    }
}
