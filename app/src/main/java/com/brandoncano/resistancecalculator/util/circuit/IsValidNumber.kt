package com.brandoncano.resistancecalculator.util.circuit

/**
 * Job: Checks to see if the value converts from a string to a double as long as string is not empty
 */
object IsValidNumber {

    fun execute(number: String): Boolean {
        if (number.isEmpty()) return true
        return number.toDoubleOrNull() != null
    }
}
