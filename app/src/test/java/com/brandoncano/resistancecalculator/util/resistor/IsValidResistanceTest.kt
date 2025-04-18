package com.brandoncano.resistancecalculator.util.resistor

import com.brandoncano.resistancecalculator.to.ResistorVtc
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import com.brandoncano.resistancecalculator.constants.Symbols as S

class IsValidResistanceTest {

    @Test
    fun validButIncompleteInputs() {
        val resistor = ResistorVtc()
        assertTrue(IsValidResistance.execute(resistor, ""))
        assertTrue(IsValidResistance.execute(resistor, "0"))
        assertTrue(IsValidResistance.execute(resistor, "0."))
    }

    @Test
    fun invalidInputs() {
        val resistor = ResistorVtc()
        // four band
        resistor.units = S.OHMS
        assertFalse(IsValidResistance.execute(resistor, "InValid"))
        assertFalse(IsValidResistance.execute(resistor, " "))
        assertFalse(IsValidResistance.execute(resistor, "12 "))
        assertFalse(IsValidResistance.execute(resistor, "."))
        assertFalse(IsValidResistance.execute(resistor, "133"))
        assertFalse(IsValidResistance.execute(resistor, "011"))
        assertFalse(IsValidResistance.execute(resistor, "001"))
        assertFalse(IsValidResistance.execute(resistor, ".0"))
        assertFalse(IsValidResistance.execute(resistor, ".1"))
        assertFalse(IsValidResistance.execute(resistor, ".12"))
        assertFalse(IsValidResistance.execute(resistor, ".123"))
        assertFalse(IsValidResistance.execute(resistor, "0.0"))
        assertFalse(IsValidResistance.execute(resistor, "0.006"))
        assertFalse(IsValidResistance.execute(resistor, "0.05"))
        assertFalse(IsValidResistance.execute(resistor, "0.056"))
        assertFalse(IsValidResistance.execute(resistor, "12.0"))
        assertFalse(IsValidResistance.execute(resistor, "1.02"))
        assertFalse(IsValidResistance.execute(resistor, "1.023"))
        assertFalse(IsValidResistance.execute(resistor, "66.0"))
        assertFalse(IsValidResistance.execute(resistor, "660.0"))
        resistor.units = S.GOHMS
        assertFalse(IsValidResistance.execute(resistor, "130"))
        assertFalse(IsValidResistance.execute(resistor, "13.0"))

        // five/six band
        resistor.units = S.OHMS
        resistor.navBarSelection = 2
        assertFalse(IsValidResistance.execute(resistor, "1234"))
        assertFalse(IsValidResistance.execute(resistor, "0123"))
        assertFalse(IsValidResistance.execute(resistor, "0012"))
        assertFalse(IsValidResistance.execute(resistor, "012"))
        assertFalse(IsValidResistance.execute(resistor, "001"))
        assertFalse(IsValidResistance.execute(resistor, ".01"))
        assertFalse(IsValidResistance.execute(resistor, "0.123"))
        assertFalse(IsValidResistance.execute(resistor, "0.0123"))
        assertFalse(IsValidResistance.execute(resistor, "1.023"))
        assertFalse(IsValidResistance.execute(resistor, "1.203"))
        assertFalse(IsValidResistance.execute(resistor, "0.001"))
        assertFalse(IsValidResistance.execute(resistor, "663.0"))
        assertFalse(IsValidResistance.execute(resistor, "6630."))
        assertFalse(IsValidResistance.execute(resistor, "6630.0"))
        assertFalse(IsValidResistance.execute(resistor, "0.01"))
        resistor.units = S.GOHMS
        assertFalse(IsValidResistance.execute(resistor, "1230"))
        assertFalse(IsValidResistance.execute(resistor, "663.0"))
    }

    @Test
    fun validInputs() {
        val resistor = ResistorVtc()
        // four band
        resistor.units = S.OHMS
        assertTrue(IsValidResistance.execute(resistor, "0.10"))
        assertTrue(IsValidResistance.execute(resistor, "0.1"))
        assertTrue(IsValidResistance.execute(resistor, "0"))
        assertTrue(IsValidResistance.execute(resistor, "10"))
        assertTrue(IsValidResistance.execute(resistor, "2"))
        assertTrue(IsValidResistance.execute(resistor, "34"))
        assertTrue(IsValidResistance.execute(resistor, "0.5"))
        assertTrue(IsValidResistance.execute(resistor, "0.67"))
        assertTrue(IsValidResistance.execute(resistor, "8."))
        assertTrue(IsValidResistance.execute(resistor, "890"))
        assertTrue(IsValidResistance.execute(resistor, "99000000000"))
        resistor.units = S.GOHMS
        assertTrue(IsValidResistance.execute(resistor, "0.67"))
        assertTrue(IsValidResistance.execute(resistor, "0.6"))
        assertTrue(IsValidResistance.execute(resistor, "6.7"))
        assertTrue(IsValidResistance.execute(resistor, "99"))

        // five band
        resistor.units = S.OHMS
        resistor.navBarSelection = 2
        assertTrue(IsValidResistance.execute(resistor, "0"))
        assertTrue(IsValidResistance.execute(resistor, "0.62"))
        assertTrue(IsValidResistance.execute(resistor, "1"))
        assertTrue(IsValidResistance.execute(resistor, "1.0"))
        assertTrue(IsValidResistance.execute(resistor, "1.00"))
        assertTrue(IsValidResistance.execute(resistor, "6.7"))
        assertTrue(IsValidResistance.execute(resistor, "9.80"))
        assertTrue(IsValidResistance.execute(resistor, "6.23"))
        assertTrue(IsValidResistance.execute(resistor, "63.2"))
        assertTrue(IsValidResistance.execute(resistor, "123"))
        assertTrue(IsValidResistance.execute(resistor, "999000000000"))
        resistor.units = S.GOHMS
        assertTrue(IsValidResistance.execute(resistor, "0.123"))
        assertTrue(IsValidResistance.execute(resistor, "6.7"))
        assertTrue(IsValidResistance.execute(resistor, "6.70"))
        assertTrue(IsValidResistance.execute(resistor, "6.23"))
        assertTrue(IsValidResistance.execute(resistor, "63.2"))
        assertTrue(IsValidResistance.execute(resistor, "999"))
    }
}
