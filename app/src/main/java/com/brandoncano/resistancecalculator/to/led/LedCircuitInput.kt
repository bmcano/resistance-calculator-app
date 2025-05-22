package com.brandoncano.resistancecalculator.to.led

data class LedCircuitInput(
    val sourceVoltage: String,
    val led: String, // enum LedType
    val ledForwardVoltage: String,
    val ledCurrent: String,
    val resistorTolerance: String,
    val numLedPerBranch: Int?,
    val numBranches: Int?,
)
