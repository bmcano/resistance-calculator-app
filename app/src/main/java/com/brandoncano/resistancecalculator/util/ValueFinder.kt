package com.brandoncano.resistancecalculator.util

import com.brandoncano.resistancecalculator.constants.Symbols as S
import com.brandoncano.resistancecalculator.constants.Colors as C

object ValueFinder {

    fun getMultiplier(color: String): Double {
        return when (color) {
            C.BLACK  -> 1.0
            C.BROWN  -> 10.0
            C.RED    -> 100.0
            C.ORANGE -> 1000.0
            C.YELLOW -> 10000.0
            C.GREEN  -> 100000.0
            C.BLUE   -> 1000000.0
            C.VIOLET -> 10000000.0
            C.GRAY   -> 100000000.0
            C.WHITE  -> 1000000000.0
            C.GOLD   -> 0.1
            C.SILVER -> 0.01
            else -> 1.0
        }
    }

    fun getSigFig(color: String) = getValue(color).first

    fun getTolerance(color: String, isThreeBand: Boolean): String {
        val param = if (isThreeBand) C.RESISTOR_BEIGE else color
        val tolerance = getValue(param).second
        return if (tolerance.isNotEmpty()) "${S.PM}$tolerance" else ""
    }

    fun getPPM(color: String, isSixBand: Boolean): String {
        val ppm = getValue(color).third
        return if (isSixBand && ppm.isNotEmpty()) "$ppm${S.NBSP}${S.PPM}" else ""
    }

    // sigfig | tolerance | ppm -> if val="" then color does not have an associated value
    private fun getValue(color: String): Triple<String, String, String> {
        return when (color) {
            C.BLACK  -> Triple("0", "",      "250")
            C.BROWN  -> Triple("1", "1%",    "100")
            C.RED    -> Triple("2", "2%",    "50")
            C.ORANGE -> Triple("3", "",      "15")
            C.YELLOW -> Triple("4", "",      "25")
            C.GREEN  -> Triple("5", "0.5%",  "20")
            C.BLUE   -> Triple("6", "0.25%", "10")
            C.VIOLET -> Triple("7", "0.1%",  "5")
            C.GRAY   -> Triple("8", "0.05%", "1")
            C.WHITE  -> Triple("9", "",      "")
            C.GOLD   -> Triple("",  "5%",    "")
            C.SILVER -> Triple("",  "10%",   "")
            C.RESISTOR_BEIGE  -> Triple("",  "20%",   "")
            else -> Triple("", "", "")
        }
    }
}