package com.brandoncano.resistancecalculator.util.eseries

import org.junit.Assert.assertEquals
import org.junit.Test

class FindClosestStandardValueTest {

    @Test
    fun `test execute with exact match in standard values`() {
        val standardValues = listOf(10.0, 22.0, 47.0, 100.0)
        val result = FindClosestStandardValue.execute(22.0, standardValues)
        assertEquals(22.0, result, 0.0)
    }

    @Test
    fun `test execute with closest value less than resistance value`() {
        val standardValues = listOf(10.0, 22.0, 47.0, 100.0)
        val result = FindClosestStandardValue.execute(30.0, standardValues)
        assertEquals(22.0, result, 0.0)
    }

    @Test
    fun `test execute with closest value greater than resistance value`() {
        val standardValues = listOf(10.0, 22.0, 47.0, 100.0)
        val result = FindClosestStandardValue.execute(40.0, standardValues)
        assertEquals(47.0, result, 0.0)
    }

    @Test
    fun `test execute with resistance value smaller than all standard values`() {
        val standardValues = listOf(22.0, 47.0, 100.0)
        val result = FindClosestStandardValue.execute(15.0, standardValues)
        assertEquals(22.0, result, 0.0)
    }

    @Test
    fun `test execute with resistance value larger than all standard values`() {
        val standardValues = listOf(22.0, 47.0, 100.0)
        val result = FindClosestStandardValue.execute(120.0, standardValues)
        assertEquals(100.0, result, 0.0)
    }

    @Test
    fun `test execute with negative resistance value`() {
        val standardValues = listOf(10.0, 22.0, 47.0, 100.0)
        val result = FindClosestStandardValue.execute(-5.0, standardValues)
        assertEquals(10.0, result, 0.0)
    }

    @Test(expected = IndexOutOfBoundsException::class)
    fun `test execute with empty standard values`() {
        FindClosestStandardValue.execute(50.0, emptyList())
    }
}
