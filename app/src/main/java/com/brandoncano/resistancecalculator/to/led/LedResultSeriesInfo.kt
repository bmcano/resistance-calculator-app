package com.brandoncano.resistancecalculator.to.led

// Series/parallel only
data class LedResultSeriesInfo(
    val ledsDissipation: String,
    // parallel only
    val oneBranchDissipation: String?,
)
