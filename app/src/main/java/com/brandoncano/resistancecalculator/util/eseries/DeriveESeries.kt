package com.brandoncano.resistancecalculator.util.eseries

import com.brandoncano.resistancecalculator.constants.ESeries

/**
 * Job: Derive the correct E-Series from a mapping of tolerance the number of bands
 */
object DeriveESeries {

    private val toleranceToESeries = mapOf(
        Pair(20.0, 3) to ESeries.E6,
        Pair(10.0, 4) to ESeries.E12,
        Pair(5.0, 4) to ESeries.E24,
        Pair(1.0, 4) to ESeries.E24,
        Pair(2.0, 5) to ESeries.E48,
        Pair(1.0, 5) to ESeries.E96,
        Pair(0.5, 5) to ESeries.E192,
        Pair(0.25, 5) to ESeries.E192,
        Pair(0.1, 5) to ESeries.E192,
        Pair(0.05, 5) to ESeries.E192,
        Pair(2.0, 6) to ESeries.E48,
        Pair(1.0, 6) to ESeries.E96,
        Pair(0.5, 6) to ESeries.E192,
        Pair(0.25, 6) to ESeries.E192,
        Pair(0.1, 6) to ESeries.E192,
        Pair(0.05, 6) to ESeries.E192,
    )

    fun execute(tolerancePercentage: Double, numBands: Int): List<Int>? {
        val pair = Pair(tolerancePercentage, numBands)
        return toleranceToESeries[pair]
    }
}
