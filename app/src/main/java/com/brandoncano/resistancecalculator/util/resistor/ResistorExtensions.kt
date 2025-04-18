package com.brandoncano.resistancecalculator.util.resistor

import com.brandoncano.resistancecalculator.constants.Colors
import com.brandoncano.resistancecalculator.to.ResistorCtv
import com.brandoncano.resistancecalculator.model.smd.SmdResistor
import com.brandoncano.resistancecalculator.to.ResistorVtc
import com.brandoncano.resistancecalculator.util.ColorFinder

// color to value
fun ResistorCtv.bandOneForDisplay(): String {
    return this.band1.ifEmpty { deriveResistorColor() }
}

fun ResistorCtv.bandTwoForDisplay(): String {
    return this.band2.ifEmpty { deriveResistorColor() }
}

fun ResistorCtv.bandThreeForDisplay(): String {
    return if (this.isFiveSixBand() && band3.isNotEmpty()) this.band3 else deriveResistorColor()
}

fun ResistorCtv.bandFourForDisplay(): String {
    return this.band4.ifEmpty { deriveResistorColor() }
}

fun ResistorCtv.bandFiveForDisplay(): String {
    return if (!this.isThreeBand() && band5.isNotEmpty()) this.band5 else deriveResistorColor()
}

fun ResistorCtv.bandSixForDisplay(): String {
    return if (this.isSixBand() && band6.isNotEmpty()) this.band6 else deriveResistorColor()
}

fun ResistorCtv.deriveResistorColor(): String {
    val isFiveBand = this.isFiveSixBand() && !this.isSixBand()
    return if (isFiveBand) Colors.RESISTOR_BLUE else Colors.RESISTOR_BEIGE
}

fun ResistorCtv.formatResistance(): String {
    return ResistanceFormatter.calculate(this)
}

fun ResistorCtv.shareableText(): String {
    return "$this\n${this.formatResistance()}"
}

// value to color
fun ResistorVtc.bandOneForDisplay(): String {
    return this.band1.ifEmpty { deriveResistorColor() }
}

fun ResistorVtc.bandTwoForDisplay(): String {
    return this.band2.ifEmpty { deriveResistorColor() }
}

fun ResistorVtc.bandThreeForDisplay(): String {
    return if (this.isFiveSixBand() && band3.isNotEmpty()) this.band3 else deriveResistorColor()
}

fun ResistorVtc.bandFourForDisplay(): String {
    return this.band4.ifEmpty { deriveResistorColor() }
}

fun ResistorVtc.bandFiveForDisplay(): String {
    return if (!this.isThreeBand() && band5.isNotEmpty()) this.band5 else deriveResistorColor()
}

fun ResistorVtc.bandSixForDisplay(): String {
    return if (this.isSixBand() && band6.isNotEmpty()) this.band6 else deriveResistorColor()
}

fun ResistorVtc.deriveResistorColor(): String {
    val isFiveBand = this.isFiveSixBand() && !this.isSixBand()
    return if (isFiveBand) Colors.RESISTOR_BLUE else Colors.RESISTOR_BEIGE
}

fun ResistorVtc.formatResistor() {
    ResistorFormatter.generateResistor(this)
}

fun ResistorVtc.isInputInvalid(): Boolean {
    return !IsValidResistance.execute(this, this.resistance)
}

fun ResistorVtc.shareableText(): String {
    return "$this\n${this.getResistorValue()}"
}

fun String.adjustValueForSharing(): String {
    val color = ColorFinder.textToColor(this)
    return ColorFinder.colorToColorText(color)
}

// smd
fun SmdResistor.isSmdInputInvalid(): Boolean {
    return !IsValidSmdCode.execute(this.code, this.getSmdMode())
}

fun SmdResistor.formatResistance(): String {
    return ResistanceSmdFormatter.execute(this)
}
