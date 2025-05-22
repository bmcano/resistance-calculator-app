package com.brandoncano.resistancecalculator.to.led

data class LedResultIndividualComponents(
    val effectiveCurrent: String,
    val resistorDissipation: String,
    val ledDissipation: String,
)
