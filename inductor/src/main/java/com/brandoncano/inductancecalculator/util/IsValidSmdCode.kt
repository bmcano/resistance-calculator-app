package com.brandoncano.inductancecalculator.util

/**
 * Job: Checks for a valid SMD inductor code depending on the mode.
 */
object IsValidSmdCode {

    fun execute(code: String): Boolean {
        if (code.length < 3 ) {
            return true
        }
        val regex = Regex("^[0-9R][0-9NR][0-4N]$")
        val isValidNCount = code.count { it == 'N' } < 2
        val isValidRCount = code.count { it == 'R' } < 2
        if (isValidNCount && isValidRCount) {
            return regex.matches(code)
        }
        return false
    }
}
