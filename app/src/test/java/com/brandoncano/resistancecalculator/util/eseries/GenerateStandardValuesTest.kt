package com.brandoncano.resistancecalculator.util.eseries

import com.brandoncano.resistancecalculator.data.ESeries
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class GenerateStandardValuesTest {

    @Test
    fun `test execute with E12 series`() {
        val eSeriesList = ESeries.E12
        val result = GenerateStandardValues.execute(eSeriesList)

        val expectedSize = eSeriesList.size * 12 // Because exponents range from -2 to 9, i.e., 12 decades
        assertEquals(expectedSize, result.size)

        // Check if first and last values are correctly computed
        assertEquals(0.1, result.first(), 0.001) // 10 * 10^-2 = 0.1Ω
        assertEquals(82000000000.0, result.last(), 0.001) // 82 * 10^9 = 82GΩ
    }

    @Test
    fun `test execute with E24 series`() {
        val eSeriesList = ESeries.E24
        val result = GenerateStandardValues.execute(eSeriesList)

        val expectedSize = eSeriesList.size * 12
        assertEquals(expectedSize, result.size)

        // Check specific values
        assertEquals(0.1, result[0], 0.001) // 10 * 10^-2 = 0.1Ω
        assertEquals(91000000000.0, result.last(), 0.001) // 91 * 10^9 = 91GΩ
    }

    @Test
    fun `test execute with empty list`() {
        val result = GenerateStandardValues.execute(emptyList())
        assertTrue(result.isEmpty())
    }

    @Test
    fun `test execute with single value in list`() {
        val eSeriesList = listOf(10)
        val result = GenerateStandardValues.execute(eSeriesList)

        assertEquals(12, result.size) // 12 decades for 1 value
        assertEquals(0.1, result.first(), 0.001) // 10 * 10^-2 = 0.1Ω
        assertEquals(10000000000.0, result.last(), 0.001) // 10 * 10^9 = 10GΩ
    }
}
