package com.brandoncano.resistancecalculator.util.resistor

import com.brandoncano.resistancecalculator.constants.Colors
import com.brandoncano.resistancecalculator.constants.Symbols

object ValueFinder {

    fun getMultiplier(color: String): Double {
        return when (color) {
            Colors.BLACK  -> 1.0
            Colors.BROWN  -> 10.0
            Colors.RED    -> 100.0
            Colors.ORANGE -> 1000.0
            Colors.YELLOW -> 10000.0
            Colors.GREEN  -> 100000.0
            Colors.BLUE   -> 1000000.0
            Colors.VIOLET -> 10000000.0
            Colors.GRAY   -> 100000000.0
            Colors.WHITE  -> 1000000000.0
            Colors.GOLD   -> 0.1
            Colors.SILVER -> 0.01
            else -> 1.0
        }
    }

    fun getSigFig(color: String) = getValue(color).first

    fun getTolerance(color: String, isThreeBand: Boolean): String {
        val param = if (isThreeBand) Colors.RESISTOR_BEIGE else color
        val tolerance = getValue(param).second
        return if (tolerance.isNotEmpty()) "${Symbols.PM}$tolerance" else ""
    }

    fun getPPM(color: String, isSixBand: Boolean): String {
        val ppm = getValue(color).third
        return if (isSixBand && ppm.isNotEmpty()) "$ppm${Symbols.NBSP}${Symbols.PPM}" else ""
    }

    // sigfig | tolerance | ppm -> if val="" then color does not have an associated value
    private fun getValue(color: String): Triple<String, String, String> {
        return when (color) {
            Colors.BLACK  -> Triple("0", "",      "250")
            Colors.BROWN  -> Triple("1", "1%",    "100")
            Colors.RED    -> Triple("2", "2%",    "50")
            Colors.ORANGE -> Triple("3", "",      "15")
            Colors.YELLOW -> Triple("4", "",      "25")
            Colors.GREEN  -> Triple("5", "0.5%",  "20")
            Colors.BLUE   -> Triple("6", "0.25%", "10")
            Colors.VIOLET -> Triple("7", "0.1%",  "5")
            Colors.GRAY   -> Triple("8", "0.05%", "1")
            Colors.WHITE  -> Triple("9", "",      "")
            Colors.GOLD   -> Triple("",  "5%",    "")
            Colors.SILVER -> Triple("",  "10%",   "")
            Colors.RESISTOR_BEIGE  -> Triple("",  "20%",   "")
            else -> Triple("", "", "")
        }
    }
}