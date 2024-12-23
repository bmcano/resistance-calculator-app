package com.brandoncano.resistancecalculator.util.eseries

import com.brandoncano.resistancecalculator.data.ESeries
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Test

class DeriveESeriesTest {

    @Test
    fun `test execute with valid tolerance and 3 bands`() {
        val result = DeriveESeries.execute(20.0, 3)
        assertNotNull(result)
        assertEquals(ESeries.E6, result)
    }

    @Test
    fun `test execute with valid tolerance and 4 bands`() {
        var result = DeriveESeries.execute(10.0, 4)
        assertNotNull(result)
        assertEquals(ESeries.E12, result)

        result = DeriveESeries.execute(5.0, 4)
        assertNotNull(result)
        assertEquals(ESeries.E24, result)

        result = DeriveESeries.execute(1.0, 4)
        assertNotNull(result)
        assertEquals(ESeries.E24, result)
    }

    @Test
    fun `test execute with valid tolerance and 5 bands`() {
        var result = DeriveESeries.execute(2.0, 5)
        assertNotNull(result)
        assertEquals(ESeries.E48, result)

        result = DeriveESeries.execute(1.0, 5)
        assertNotNull(result)
        assertEquals(ESeries.E96, result)

        result = DeriveESeries.execute(0.5, 5)
        assertNotNull(result)
        assertEquals(ESeries.E192, result)
    }

    @Test
    fun `test execute with valid tolerance and 6 bands`() {
        var result = DeriveESeries.execute(2.0, 6)
        assertNotNull(result)
        assertEquals(ESeries.E48, result)

        result = DeriveESeries.execute(1.0, 6)
        assertNotNull(result)
        assertEquals(ESeries.E96, result)

        result = DeriveESeries.execute(0.5, 6)
        assertNotNull(result)
        assertEquals(ESeries.E192, result)
    }

    @Test
    fun `test execute with unsupported tolerance and band combination`() {
        val result = DeriveESeries.execute(50.0, 4)
        assertNull(result)
    }

    @Test
    fun `test execute with negative tolerance and invalid band count`() {
        val result = DeriveESeries.execute(-1.0, 7)
        assertNull(result)
    }
}
