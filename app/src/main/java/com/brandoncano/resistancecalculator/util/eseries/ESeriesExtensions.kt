package com.brandoncano.resistancecalculator.util.eseries

import java.math.BigDecimal
import java.math.RoundingMode

fun String.tolerancePercentage(): Double? {
    if (this.length < 3) return null
    return this
        .substring(1, this.length - 1)
        .toDoubleOrNull()
}

fun Double.roundToTwoDecimalPlaces(): Double {
    return BigDecimal(this).setScale(2, RoundingMode.HALF_UP).toDouble()
}

fun Double.formatResistanceString(sigFigs: Int): String {
    return FormatResistanceString.execute(this, sigFigs)
}
