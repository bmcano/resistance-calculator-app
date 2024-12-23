package com.brandoncano.resistancecalculator.util.eseries

import org.junit.Assert.assertEquals
import org.junit.Test

class RoundStandardValueTest {

    @Test
    fun `test execute with integer value`() {
        val result = RoundStandardValue.execute(100.0)
        assertEquals("100", result)
    }

    @Test
    fun `test execute with two decimal places`() {
        val result = RoundStandardValue.execute(100.25)
        assertEquals("100.25", result)
    }

    @Test
    fun `test execute with one decimal place`() {
        val result = RoundStandardValue.execute(47.5)
        assertEquals("47.5", result)
    }

    @Test
    fun `test execute with trailing zero decimal`() {
        val result = RoundStandardValue.execute(82.50)
        assertEquals("82.5", result)
    }

    @Test
    fun `test execute with no trailing decimals`() {
        val result = RoundStandardValue.execute(56.00)
        assertEquals("56", result)
    }

    @Test
    fun `test execute with trailing zeros after decimal`() {
        val result = RoundStandardValue.execute(33.000)
        assertEquals("33", result)
    }

    @Test
    fun `test execute with small fractional value`() {
        val result = RoundStandardValue.execute(0.02)
        assertEquals("0.02", result)
    }

    @Test
    fun `test execute with large value`() {
        val result = RoundStandardValue.execute(1000000.0)
        assertEquals("1000000", result)
    }
}
