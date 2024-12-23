package com.brandoncano.resistancecalculator.util.eseries

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Test

class ParseResistanceValueTest {

    @Test
    fun `test execute with valid value and ohms`() {
        val result = ParseResistanceValue.execute("100", "Ω")
        assertNotNull(result)
        if (result != null) {
            assertEquals(100.0, result, 0.0)
        }
    }

    @Test
    fun `test execute with valid value and kiloohms`() {
        val result = ParseResistanceValue.execute("47", "kΩ")
        assertNotNull(result)
        if (result != null) {
            assertEquals(47000.0, result, 0.0)
        }
    }

    @Test
    fun `test execute with valid value and megaohms`() {
        val result = ParseResistanceValue.execute("2.2", "MΩ")
        assertNotNull(result)
        if (result != null) {
            assertEquals(2200000.0, result, 0.0)
        }
    }

    @Test
    fun `test execute with valid value and gigaohms`() {
        val result = ParseResistanceValue.execute("1", "GΩ")
        assertNotNull(result)
        if (result != null) {
            assertEquals(1000000000.0, result, 0.0)
        }
    }

    @Test
    fun `test execute with invalid value returns null`() {
        val result = ParseResistanceValue.execute("abc", "Ω")
        assertNull(result)
    }

    @Test
    fun `test execute with unsupported units returns correct value without multiplier`() {
        // Assuming MultiplierFromUnits.execute() returns 1.0 for unsupported units
        val result = ParseResistanceValue.execute("100", "unsupported")
        assertNotNull(result)
        if (result != null) {
            assertEquals(100.0, result, 0.0)
        }
    }

    @Test
    fun `test execute with negative value`() {
        val result = ParseResistanceValue.execute("-47", "kΩ")
        assertNotNull(result)
        if (result != null) {
            assertEquals(-47000.0, result, 0.0)
        }
    }

    @Test
    fun `test execute with empty value returns null`() {
        val result = ParseResistanceValue.execute("", "Ω")
        assertNull(result)
    }

    @Test
    fun `test execute with null value returns null`() {
        val result = ParseResistanceValue.execute("null", "Ω")
        assertNull(result)
    }
}
