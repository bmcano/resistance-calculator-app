package com.brandoncano.resistancecalculator.util.eseries

import com.brandoncano.resistancecalculator.data.ESeriesCardContent
import com.brandoncano.resistancecalculator.util.MultiplierFromUnits
import kotlin.math.abs

/**
 * Job: Calculate the closest standard value in the E-Series based on the list, units, and value.
 */
object CalculateClosestStandardValue {

    fun execute(resistanceValue: Double, units: String, eSeriesList: List<Int>): Pair<ESeriesCardContent, Double> {
        val standardValues = GenerateStandardValues.execute(eSeriesList)
        val closestValueOhms = FindClosestStandardValue.execute(resistanceValue, standardValues)
        val closestValue = if (resistanceValue > 0.0) {
            closestValueOhms / MultiplierFromUnits.execute(units)
        } else {
            0.1
        }

        val difference = abs(resistanceValue - closestValueOhms)
        val eSeriesName = DeriveESeriesString.execute(eSeriesList)
        val content = if (difference == 0.0) {
            ESeriesCardContent.ValidResistance(eSeriesName)
        } else {
            ESeriesCardContent.InvalidResistance("$closestValue $units")
        }

        return Pair(content, closestValue)
    }
}
