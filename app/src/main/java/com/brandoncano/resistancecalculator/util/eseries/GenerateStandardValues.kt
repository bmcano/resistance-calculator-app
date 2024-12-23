package com.brandoncano.resistancecalculator.util.eseries

import kotlin.math.pow

/**
 * Job: For each decade, compute the list of possible values in the E-series.
 */
object GenerateStandardValues {

    fun execute(eSeriesList: List<Int>): List<Double> {
        val standardValues = mutableListOf<Double>()
        val exponents = -2..9 // from 0.01Ω to 1GΩ
        for (exponent in exponents) {
            val multiplier = 10.0.pow(exponent)
            for (eValue in eSeriesList) {
                val standardValue = eValue * multiplier
                standardValues.add(standardValue.roundToTwoDecimalPlaces())
            }
        }
        return standardValues
    }
}
