package com.brandoncano.resistancecalculator.util.resistor

import com.brandoncano.resistancecalculator.data.SmdMode
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class IsValidSmdCodeTest {

    @Test
    fun `valid 3 digit EIA regex test`() {
        assertTrue(IsValidSmdCode.execute("063", SmdMode.ThreeDigit))
        assertTrue(IsValidSmdCode.execute("174", SmdMode.ThreeDigit))
        assertTrue(IsValidSmdCode.execute("285", SmdMode.ThreeDigit))
        assertTrue(IsValidSmdCode.execute("396", SmdMode.ThreeDigit))
        assertTrue(IsValidSmdCode.execute("407", SmdMode.ThreeDigit))
        assertTrue(IsValidSmdCode.execute("518", SmdMode.ThreeDigit))
        assertTrue(IsValidSmdCode.execute("629", SmdMode.ThreeDigit))
        assertTrue(IsValidSmdCode.execute("730", SmdMode.ThreeDigit))
        assertTrue(IsValidSmdCode.execute("841", SmdMode.ThreeDigit))
        assertTrue(IsValidSmdCode.execute("952", SmdMode.ThreeDigit))

        assertTrue(IsValidSmdCode.execute("0R4", SmdMode.ThreeDigit))
        assertTrue(IsValidSmdCode.execute("1R5", SmdMode.ThreeDigit))
        assertTrue(IsValidSmdCode.execute("2R6", SmdMode.ThreeDigit))
        assertTrue(IsValidSmdCode.execute("3R7", SmdMode.ThreeDigit))
    }

    @Test
    fun `3 digit EIA less than full amount test`() {
        assertTrue(IsValidSmdCode.execute("1R", SmdMode.ThreeDigit))
        assertTrue(IsValidSmdCode.execute("1", SmdMode.ThreeDigit))
        assertTrue(IsValidSmdCode.execute("", SmdMode.ThreeDigit))
    }

    @Test
    fun `invalid 3 digit EIA regex test`() {
        assertFalse(IsValidSmdCode.execute("0RR", SmdMode.ThreeDigit))
        assertFalse(IsValidSmdCode.execute("2R64", SmdMode.ThreeDigit))
        assertFalse(IsValidSmdCode.execute("random_string", SmdMode.ThreeDigit))
    }

    @Test
    fun `valid 4 digit EIA regex test`() {
        assertTrue(IsValidSmdCode.execute("0632", SmdMode.FourDigit))
        assertTrue(IsValidSmdCode.execute("1743", SmdMode.FourDigit))
        assertTrue(IsValidSmdCode.execute("2854", SmdMode.FourDigit))
        assertTrue(IsValidSmdCode.execute("3965", SmdMode.FourDigit))
        assertTrue(IsValidSmdCode.execute("4076", SmdMode.FourDigit))
        assertTrue(IsValidSmdCode.execute("5187", SmdMode.FourDigit))
        assertTrue(IsValidSmdCode.execute("6298", SmdMode.FourDigit))
        assertTrue(IsValidSmdCode.execute("7309", SmdMode.FourDigit))
        assertTrue(IsValidSmdCode.execute("8410", SmdMode.FourDigit))
        assertTrue(IsValidSmdCode.execute("9521", SmdMode.FourDigit))

        assertTrue(IsValidSmdCode.execute("0R34", SmdMode.FourDigit))
        assertTrue(IsValidSmdCode.execute("12R5", SmdMode.FourDigit))
        assertTrue(IsValidSmdCode.execute("27R6", SmdMode.FourDigit))
        assertTrue(IsValidSmdCode.execute("3R77", SmdMode.FourDigit))
    }

    @Test
    fun `4 digit EIA less than full amount test`() {
        assertTrue(IsValidSmdCode.execute("1R2", SmdMode.FourDigit))
        assertTrue(IsValidSmdCode.execute("1R", SmdMode.FourDigit))
        assertTrue(IsValidSmdCode.execute("1", SmdMode.FourDigit))
        assertTrue(IsValidSmdCode.execute("", SmdMode.FourDigit))
    }

    @Test
    fun `invalid 4 digit EIA regex test`() {
        assertFalse(IsValidSmdCode.execute("1RR3", SmdMode.FourDigit))
        assertFalse(IsValidSmdCode.execute("2RRR", SmdMode.FourDigit))
        assertFalse(IsValidSmdCode.execute("random_string", SmdMode.FourDigit))
    }

    @Test
    fun `valid EIA-96 regex test`() {
        assertTrue(IsValidSmdCode.execute("06A", SmdMode.EIA96))
        assertTrue(IsValidSmdCode.execute("17B", SmdMode.EIA96))
        assertTrue(IsValidSmdCode.execute("28C", SmdMode.EIA96))
        assertTrue(IsValidSmdCode.execute("39D", SmdMode.EIA96))
        assertTrue(IsValidSmdCode.execute("40E", SmdMode.EIA96))
        assertTrue(IsValidSmdCode.execute("51F", SmdMode.EIA96))
        assertTrue(IsValidSmdCode.execute("73H", SmdMode.EIA96))
        assertTrue(IsValidSmdCode.execute("84R", SmdMode.EIA96))
        assertTrue(IsValidSmdCode.execute("95S", SmdMode.EIA96))
        assertTrue(IsValidSmdCode.execute("06X", SmdMode.EIA96))
        assertTrue(IsValidSmdCode.execute("17Y", SmdMode.EIA96))
        assertTrue(IsValidSmdCode.execute("28Z", SmdMode.EIA96))
    }

    @Test
    fun `EIA-96 less than full code regex test`() {
        assertTrue(IsValidSmdCode.execute("06", SmdMode.EIA96))
        assertTrue(IsValidSmdCode.execute("1", SmdMode.EIA96))
        assertTrue(IsValidSmdCode.execute("", SmdMode.EIA96))
    }

    @Test
    fun `invalid EIA-96 regex test`() {
        assertFalse(IsValidSmdCode.execute("12G", SmdMode.EIA96))
        assertFalse(IsValidSmdCode.execute("12I", SmdMode.EIA96))
        assertFalse(IsValidSmdCode.execute("12J", SmdMode.EIA96))
        assertFalse(IsValidSmdCode.execute("12K", SmdMode.EIA96))
        assertFalse(IsValidSmdCode.execute("12L", SmdMode.EIA96))
        assertFalse(IsValidSmdCode.execute("12M", SmdMode.EIA96))
        assertFalse(IsValidSmdCode.execute("12N", SmdMode.EIA96))
        assertFalse(IsValidSmdCode.execute("12O", SmdMode.EIA96))
        assertFalse(IsValidSmdCode.execute("12P", SmdMode.EIA96))
        assertFalse(IsValidSmdCode.execute("12Q", SmdMode.EIA96))
        assertFalse(IsValidSmdCode.execute("12T", SmdMode.EIA96))
        assertFalse(IsValidSmdCode.execute("12U", SmdMode.EIA96))
        assertFalse(IsValidSmdCode.execute("12V", SmdMode.EIA96))
        assertFalse(IsValidSmdCode.execute("12W", SmdMode.EIA96))
        assertFalse(IsValidSmdCode.execute("1RR3", SmdMode.EIA96))
        assertFalse(IsValidSmdCode.execute("2RRR", SmdMode.EIA96))
        assertFalse(IsValidSmdCode.execute("random_string", SmdMode.EIA96))
    }
}