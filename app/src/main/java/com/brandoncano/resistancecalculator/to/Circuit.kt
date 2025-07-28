package com.brandoncano.resistancecalculator.to

import com.brandoncano.resistancecalculator.constants.Symbols
import java.io.Serializable

data class Circuit(
    val resistorInputs: MutableList<String> = MutableList(8) { "" },
    val isSameValues: Boolean = false,
    val resistorCount: Int = 2,
    val units: String = Symbols.OHMS,
    val totalResistance: String = "0",
) : Serializable {

    fun getInputs(): List<String> {
        return resistorInputs.take(resistorCount)
    }
}
