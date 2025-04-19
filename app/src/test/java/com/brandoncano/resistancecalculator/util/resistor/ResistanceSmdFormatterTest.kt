package com.brandoncano.resistancecalculator.util.resistor

import com.brandoncano.resistancecalculator.constants.Symbols
import com.brandoncano.resistancecalculator.to.SmdResistor
import org.junit.Assert.assertEquals
import org.junit.Test

class ResistanceSmdFormatterTest {

    @Test
    fun `3 digit conversions`() {
        val resistor = SmdResistor("", Symbols.OHMS, 0)
        assertEquals("Enter code", ResistanceSmdFormatter.execute(resistor))
        resistor.code = "120"
        assertEquals("12 ${Symbols.OHMS}", ResistanceSmdFormatter.execute(resistor))
        resistor.code = "1R2"
        assertEquals("1.2 ${Symbols.OHMS}", ResistanceSmdFormatter.execute(resistor))
        resistor.code = "1RR"
        assertEquals("${Double.NaN}", ResistanceSmdFormatter.execute(resistor))
        resistor.code = "1AA"
        assertEquals("${Double.NaN}", ResistanceSmdFormatter.execute(resistor))
        resistor.code = "123"
        resistor.units = Symbols.MOHMS
        assertEquals("0.012 ${Symbols.MOHMS}", ResistanceSmdFormatter.execute(resistor))
        resistor.code = "123"
        resistor.units = ""
        assertEquals("12000 ${Symbols.OHMS}", ResistanceSmdFormatter.execute(resistor))
    }

    @Test
    fun `4 digit conversions`() {
        val resistor = SmdResistor("", Symbols.OHMS, 1)
        assertEquals("Enter code", ResistanceSmdFormatter.execute(resistor))
        resistor.code = "1231"
        assertEquals("1230 ${Symbols.OHMS}", ResistanceSmdFormatter.execute(resistor))
        resistor.code = "1R22"
        assertEquals("1.22 ${Symbols.OHMS}", ResistanceSmdFormatter.execute(resistor))
        resistor.code = "12R2"
        assertEquals("12.2 ${Symbols.OHMS}", ResistanceSmdFormatter.execute(resistor))
        resistor.code = "1RA2"
        assertEquals("${Double.NaN}", ResistanceSmdFormatter.execute(resistor))
        resistor.code = "1AR2"
        assertEquals("${Double.NaN}", ResistanceSmdFormatter.execute(resistor))
        resistor.code = "1AAA"
        assertEquals("${Double.NaN}", ResistanceSmdFormatter.execute(resistor))
        resistor.code = "1234"
        resistor.units = Symbols.MOHMS
        assertEquals("1.23 ${Symbols.MOHMS}", ResistanceSmdFormatter.execute(resistor))
        resistor.code = "16R8"
        assertEquals("1.68E-5 ${Symbols.MOHMS}", ResistanceSmdFormatter.execute(resistor))
    }

    @Test
    fun `EIA96 conversions`() {
        val resistor = SmdResistor("", Symbols.OHMS, 2)
        assertEquals("Enter code", ResistanceSmdFormatter.execute(resistor))
        resistor.code = "12A"
        assertEquals("130 ${Symbols.OHMS}", ResistanceSmdFormatter.execute(resistor))
        resistor.code = "1XM"
        assertEquals("${Double.NaN}", ResistanceSmdFormatter.execute(resistor))
        resistor.code = "16Z"
        assertEquals("0.143 ${Symbols.OHMS}", ResistanceSmdFormatter.execute(resistor))
    }
}