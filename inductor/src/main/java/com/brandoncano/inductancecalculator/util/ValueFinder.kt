package com.brandoncano.inductancecalculator.util

import com.brandoncano.inductancecalculator.constants.Symbols as S
import com.brandoncano.inductancecalculator.constants.Colors as C

/**
 * Job: gets a value that a color is associated with, or a default if not.
 */
object ValueFinder {

    fun getNumeral(color: String) = getValue(color).first

    fun getMultiplier(color: String) = getValue(color).second

    fun getTolerance(color: String): String {
        val tolerance = getValue(color).third
        return if (tolerance.isNotEmpty()) "${S.PM}$tolerance" else ""
    }

    // sigfig | multiplier | tolerance -> if val="" then color does not have an associated value
    private fun getValue(color: String): Triple<String, Double, String> {
        return when (color) {
            C.BLACK  -> Triple("0", 1.0, "20%")
            C.BROWN  -> Triple("1", 10.0, "1%")
            C.RED    -> Triple("2", 100.0, "2%")
            C.ORANGE -> Triple("3", 1000.0, "3%")
            C.YELLOW -> Triple("4", 10000.0, "4%")
            C.GREEN  -> Triple("5", 1.0, "")
            C.BLUE   -> Triple("6", 1.0, "")
            C.VIOLET -> Triple("7", 1.0, "")
            C.GRAY   -> Triple("8", 1.0, "")
            C.WHITE  -> Triple("9", 1.0, "")
            C.GOLD   -> Triple("",  0.1, "5%")
            C.SILVER -> Triple("",  0.01, "10%")
            else -> Triple("", 1.0, "")
        }
    }
}
