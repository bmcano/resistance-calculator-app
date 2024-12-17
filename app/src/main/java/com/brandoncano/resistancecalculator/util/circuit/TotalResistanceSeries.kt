package com.brandoncano.resistancecalculator.util.circuit

/**
 * Job: Calculate the total resistance (Req) of resistor values in series.
 */
object TotalResistanceSeries {

    fun execute(resistorValues: List<String>): String {
        return resistorValues
            .sumOf { it.toDoubleOrNull() ?: 0.0 }
            .toString()
    }

    fun execute(numberOfResistors: Int, resistorValue: String): String {
        val req = numberOfResistors * (resistorValue.toDoubleOrNull() ?: 0.0)
        return req.toString()
    }
}
