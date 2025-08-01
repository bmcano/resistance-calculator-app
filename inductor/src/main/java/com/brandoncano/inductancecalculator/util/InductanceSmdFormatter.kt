package com.brandoncano.inductancecalculator.util

import com.brandoncano.inductancecalculator.constants.Symbols
import com.brandoncano.inductancecalculator.to.SmdInductor

/**
 * Job: Format the inductance text depending on the mode and code
 */
object InductanceSmdFormatter {

    fun execute(inductor: SmdInductor): String {
        if (inductor.isEmpty()) return "Enter code" // we need this for sharing as text
        val code = inductor.code
        return threeDigit(code)
    }

    private fun threeDigit(code: String): String {
        val first = code[0]
        val second = code[1]
        val third = code[2]
        return when {
            first == 'R' -> "0.$second$third ${Symbols.UH}"
            second == 'R' -> "$first.$third ${Symbols.UH}"
            second == 'N' -> "$first.$third ${Symbols.NH}"
            third == 'N' -> "$first$second ${Symbols.NH}"
            third == '4' -> "$first$second${"0"} ${Symbols.MH}"
            third == '3' -> "$first$second ${Symbols.MH}"
            third == '2' -> "$first.$second ${Symbols.MH}"
            third == '1' -> "$first$second ${"0"}${Symbols.UH}"
            else -> "$first$second ${Symbols.UH}"
        }
    }
}
