package com.brandoncano.resistancecalculator.util.eseries

import com.brandoncano.resistancecalculator.constants.ESeries.E12
import com.brandoncano.resistancecalculator.constants.ESeries.E192
import com.brandoncano.resistancecalculator.constants.ESeries.E24
import com.brandoncano.resistancecalculator.constants.ESeries.E48
import com.brandoncano.resistancecalculator.constants.ESeries.E96

/**
 * Job: Get string representation for the E-Series list
 */
object DeriveESeriesString {

    fun execute(eSeries: List<Int>): String {
        return when (eSeries) {
            E12 -> "E-12"
            E24 -> "E-24"
            E48 -> "E-48"
            E96 -> "E-96"
            E192 -> "E-192"
            else -> "E-6"
        }
    }
}
