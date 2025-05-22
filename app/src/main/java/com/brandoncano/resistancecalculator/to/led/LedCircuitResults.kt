package com.brandoncano.resistancecalculator.to.led

data class LedCircuitResults(
    val resistance: LedResultResistance,
    val individualComponents: LedResultIndividualComponents,
    // series/parallel only
    val seriesInfo: LedResultSeriesInfo?,
    val circuitInfo: LedResultCircuitInfo,
)
