package com.brandoncano.resistancecalculator.to.led

data class LedResultCircuitInfo(
    // parallel only
    val totalResistorDissipation: String?,
    // parallel only
    val totalLedsDissipation: String?,
    val totalCurrent: String,
    val totalDissipation: String,
    val powerEfficiency: String,
)
