package com.brandoncano.resistancecalculator.util.eseries

import com.brandoncano.resistancecalculator.data.ESeries
import org.junit.Assert.assertEquals
import org.junit.Test

class DeriveESeriesStringTest {

    @Test
    fun `test execute with E12`() {
        val result = DeriveESeriesString.execute(ESeries.E12)
        assertEquals("E-12", result)
    }

    @Test
    fun `test execute with E24`() {
        val result = DeriveESeriesString.execute(ESeries.E24)
        assertEquals("E-24", result)
    }

    @Test
    fun `test execute with E48`() {
        val result = DeriveESeriesString.execute(ESeries.E48)
        assertEquals("E-48", result)
    }

    @Test
    fun `test execute with E96`() {
        val result = DeriveESeriesString.execute(ESeries.E96)
        assertEquals("E-96", result)
    }

    @Test
    fun `test execute with E192`() {
        val result = DeriveESeriesString.execute(ESeries.E192)
        assertEquals("E-192", result)
    }

    @Test
    fun `test execute with other E-Series returns E-6`() {
        val result = DeriveESeriesString.execute(listOf(10, 20, 30))
        assertEquals("E-6", result)
    }
}
