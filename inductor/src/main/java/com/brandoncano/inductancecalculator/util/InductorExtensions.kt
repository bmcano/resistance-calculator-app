package com.brandoncano.inductancecalculator.util

import com.brandoncano.inductancecalculator.data.SmdTolerance
import com.brandoncano.inductancecalculator.to.InductorCtv
import com.brandoncano.inductancecalculator.to.SmdInductor
import com.brandoncano.inductancecalculator.to.InductorVtc

fun InductorCtv.formatInductance(): String {
    return InductanceFormatter.execute(this)
}

fun InductorCtv.shareableText(): String {
    return "$this\n${this.formatInductance()}"
}

fun InductorVtc.isInvalidInput(): Boolean {
    return !IsValidInductance.execute(this.inductance, this.units)
}

fun InductorVtc.formatInductor() {
    InductorFormatter.execute(this)
}

fun InductorVtc.shareableText(): String {
    return "$this\n${this.getInductanceValue()}"
}

fun String.adjustValueForSharing(): String {
    val color = ColorFinder.textToColor(this)
    return ColorFinder.colorToColorText(color)
}

fun SmdInductor.isSmdInputInvalid(): Boolean {
    return !IsValidSmdCode.execute(this.code)
}

fun SmdInductor.formatInductance(): String {
    return InductanceSmdFormatter.execute(this)
}

fun SmdInductor.formatTolerance(): String {
    return SmdTolerance.getTolerance(this.tolerance)
}
