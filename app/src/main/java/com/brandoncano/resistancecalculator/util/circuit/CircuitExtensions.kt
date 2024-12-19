package com.brandoncano.resistancecalculator.util.circuit

import java.util.Locale

fun Double.formatResistance(): String {
    val rounded = String.format(Locale.US, "%.2f", this)
    return if (rounded.endsWith(".00")) {
        rounded.dropLast(3)
    } else if (rounded.endsWith(".0")) {
        rounded.dropLast(2)
    } else {
        rounded
    }
}
