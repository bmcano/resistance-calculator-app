package com.brandoncano.resistancecalculator.util

import com.brandoncano.resistancecalculator.util.resistor.FindEIA96Value
import org.junit.Assert.assertEquals
import org.junit.Test

class FindEIA96ValueTest {

    @Test
    fun `valid EIA96 code values`() {
        assertEquals(100.0, FindEIA96Value.execute("01"), 0.0)
        assertEquals(115.0, FindEIA96Value.execute("07"), 0.0)
        assertEquals(133.0, FindEIA96Value.execute("13"), 0.0)
        assertEquals(154.0, FindEIA96Value.execute("19"), 0.0)
        assertEquals(178.0, FindEIA96Value.execute("25"), 0.0)
        assertEquals(205.0, FindEIA96Value.execute("31"), 0.0)
        assertEquals(237.0, FindEIA96Value.execute("37"), 0.0)
        assertEquals(274.0, FindEIA96Value.execute("43"), 0.0)
        assertEquals(316.0, FindEIA96Value.execute("49"), 0.0)
        assertEquals(365.0, FindEIA96Value.execute("55"), 0.0)
        assertEquals(422.0, FindEIA96Value.execute("61"), 0.0)
        assertEquals(487.0, FindEIA96Value.execute("67"), 0.0)
        assertEquals(562.0, FindEIA96Value.execute("73"), 0.0)
        assertEquals(649.0, FindEIA96Value.execute("79"), 0.0)
        assertEquals(750.0, FindEIA96Value.execute("85"), 0.0)
        assertEquals(866.0, FindEIA96Value.execute("91"), 0.0)
        assertEquals(976.0, FindEIA96Value.execute("96"), 0.0)
    }

    @Test
    fun `invalid EIA96 code values`() {
        assertEquals(Double.NaN, FindEIA96Value.execute("00"), 0.0)
        assertEquals(Double.NaN, FindEIA96Value.execute("97"), 0.0)
        assertEquals(Double.NaN, FindEIA96Value.execute("100"), 0.0)
        assertEquals(Double.NaN, FindEIA96Value.execute(""), 0.0)
        assertEquals(Double.NaN, FindEIA96Value.execute("some string"), 0.0)
    }
}