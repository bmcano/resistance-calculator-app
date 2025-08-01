package com.brandoncano.inductancecalculator.to

import com.brandoncano.inductancecalculator.util.formatInductance
import com.brandoncano.inductancecalculator.util.formatTolerance
import java.io.Serializable

data class SmdInductor(
    var code: String = "",
    var tolerance: String = "",
) : Serializable {
    fun isEmpty(): Boolean {
        return code.length < 3
    }

    override fun toString(): String {
        val inductance = this.formatInductance()
        return "$code $tolerance\n$inductance ${this.formatTolerance()}"
    }
}