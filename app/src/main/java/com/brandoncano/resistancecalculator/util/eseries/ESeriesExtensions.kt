package com.brandoncano.resistancecalculator.util.eseries

import java.math.BigDecimal
import java.math.RoundingMode
import kotlin.math.abs
import kotlin.math.floor
import kotlin.math.log10
import kotlin.math.max

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
