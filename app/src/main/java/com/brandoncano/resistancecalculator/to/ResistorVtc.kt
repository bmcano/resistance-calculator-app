package com.brandoncano.resistancecalculator.to

import com.brandoncano.resistancecalculator.constants.Symbols
import com.brandoncano.resistancecalculator.util.resistor.adjustValueForSharing
import java.io.Serializable

data class ResistorVtc(
    var resistance: String = "",
    var units: String = "",
    var band5: String = "",
    var band6: String = "",
    var navBarSelection: Int = 1,
    // needed for the resistor image
    var band1: String = "",
    var band2: String = "",
    var band3: String = "",
    var band4: String = "",
) : Serializable {
    fun isThreeBand() = navBarSelection == 0
    fun isThreeFourBand() = navBarSelection == 0 || navBarSelection == 1
    fun isFiveSixBand() = navBarSelection == 2 || navBarSelection == 3
    fun isSixBand() = navBarSelection == 3

    fun getResistorValue(): String {
        var text = "$resistance $units "
        text += if (isThreeBand()) "${Symbols.PM}20%" else band5
        if (isSixBand()) text += ", $band6".trimEnd(',', ' ')
        return text
    }

    fun isEmpty(): Boolean {
        return resistance.isEmpty() || units.isEmpty()
    }

    override fun toString(): String {
        val tolerance = band5.adjustValueForSharing()
        val ppm = band6.adjustValueForSharing()
        return when (navBarSelection) {
            1 -> "[ $band1, $band2, $band4, $tolerance ]"
            2 -> "[ $band1, $band2, $band3, $band4, $tolerance ]"
            3 -> "[ $band1, $band2, $band3, $band4, $tolerance, $ppm ]"
            else -> "[ $band1, $band2, $band4 ]"
        }
    }
}