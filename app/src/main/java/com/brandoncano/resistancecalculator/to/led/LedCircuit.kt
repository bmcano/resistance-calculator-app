package com.brandoncano.resistancecalculator.to.led

import com.brandoncano.resistancecalculator.constants.Symbols
import com.brandoncano.resistancecalculator.data.LedType
import java.io.Serializable

data class LedCircuit(
    val navBarSelection: Int = 0,
    val sourceVoltage: String = "",
    val led: LedType = LedType.CUSTOM,

    val ledForwardVoltage: String = "",
    val ledCurrent: String = "",
    val resistorTolerance: String = Symbols.E24,
    val numLedPerBranch: Int = 1, // series & parallel
    val numBranches: Int = 1, // parallel only
) : Serializable
