package com.brandoncano.resistancecalculator.to

import com.brandoncano.resistancecalculator.data.SmdMode
import com.brandoncano.resistancecalculator.util.resistor.formatResistance
import java.io.Serializable

data class SmdResistor(
    var code: String = "",
    var units: String = "",
    var navBarSelection: Int = 0,
) : Serializable {
    fun isEmpty(): Boolean {
        val length = code.length
        return length < 3 || (getSmdMode() is SmdMode.FourDigit && length < 4)
    }

    fun getSmdMode(): SmdMode {
        return when (navBarSelection) {
            0 -> SmdMode.ThreeDigit
            1 -> SmdMode.FourDigit
            2 -> SmdMode.EIA96
            else -> SmdMode.ThreeDigit
        }
    }

    override fun toString(): String {
        val resistance = this.formatResistance()
        return "Code: $code\nResistance: $resistance"
    }
}
