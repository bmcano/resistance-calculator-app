package com.brandoncano.resistancecalculator.util.circuit

/**
 * Job: Calculate the total resistance (Req) of resistor values in series.
 */
object TotalResistanceSeries {

    fun execute(sameValues: Boolean, resistorCount: Int, resistorValues: List<String>): String {
        if (sameValues) {
            val value = resistorValues.firstOrNull() ?: "0"
            val req = resistorCount * (value.toDoubleOrNull() ?: 0.0)
            return req.formatResistance()
        }

        return resistorValues
            .sumOf { it.toDoubleOrNull() ?: 0.0 }
            .formatResistance()
    }
}
