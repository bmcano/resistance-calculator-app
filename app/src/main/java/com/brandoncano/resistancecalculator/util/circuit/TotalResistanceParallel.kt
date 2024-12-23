package com.brandoncano.resistancecalculator.util.circuit

/**
 * Job: Calculate the total resistance (Req) of resistor values in parallel.
 */
object TotalResistanceParallel {

    fun execute(sameValues: Boolean, resistorCount: Int, resistorValues: List<String>): String {
        if (sameValues) {
            val value = resistorValues.firstOrNull() ?: "0"
            val resistorValueDouble = value.toDoubleOrNull() ?: 0.0
            return if (resistorValueDouble == 0.0) {
                "0" // default for when all resistors are invalid or 0
            } else {
                (1.0 / (resistorCount / resistorValueDouble)).formatResistance()
            }
        }

        val reciprocalSum = resistorValues
            .mapNotNull { it.toDoubleOrNull()?.takeIf { value -> value != 0.0 } }
            .sumOf { 1.0 / it }
        return if (reciprocalSum == 0.0) {
            "0" // default for when all resistors are invalid or 0
        } else {
            (1.0 / reciprocalSum).formatResistance()
        }
    }
}
