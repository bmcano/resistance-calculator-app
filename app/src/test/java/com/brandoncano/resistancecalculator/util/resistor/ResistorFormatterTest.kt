package com.brandoncano.resistancecalculator.util.resistor

import com.brandoncano.resistancecalculator.to.ResistorVtc
import com.brandoncano.resistancecalculator.constants.Colors as C
import com.brandoncano.resistancecalculator.constants.Symbols as S
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Note: This will only test valid number inputs, since only valid inputs are allowed through this.
 */
class ResistorFormatterTest {

    @Test
    fun `non number resistance values`() {
        val resistor = ResistorVtc()
        ResistorFormatter.generateResistor(resistor)
        assertEquals("", resistor.band4)

        resistor.resistance = "NotValid"
        ResistorFormatter.generateResistor(resistor)
        assertEquals("", resistor.band4)
    }

    @Test
    fun `numerical input for multiplier band of four band resistors`() {
        val resistances = listOf(
            "1", "10", "100", "1000", "10000",
            "1", "10", "100", "1000", "10000",
            "1", "10", "100", "1000", "10000",
            "1", "10",
            "23", "230", "2300", "23000",
            "23", "230", "2300", "23000",
            "23", "230", "2300", "23000",
            "23", "230" // blank, since invalid
        )

        val units = listOf(
            S.OHMS, S.OHMS, S.OHMS, S.OHMS, S.OHMS,
            S.KOHMS, S.KOHMS, S.KOHMS, S.KOHMS, S.KOHMS,
            S.MOHMS, S.MOHMS, S.MOHMS, S.MOHMS, S.MOHMS,
            S.GOHMS, S.GOHMS,
            S.OHMS, S.OHMS, S.OHMS, S.OHMS,
            S.KOHMS, S.KOHMS, S.KOHMS, S.KOHMS,
            S.MOHMS, S.MOHMS, S.MOHMS, S.MOHMS,
            S.GOHMS, S.GOHMS
        )

        val answers = listOf(
            C.GOLD, C.BLACK, C.BROWN, C.RED, C.ORANGE,
            C.RED, C.ORANGE, C.YELLOW, C.GREEN, C.BLUE,
            C.GREEN, C.BLUE, C.VIOLET, C.GRAY, C.WHITE,
            C.GRAY, C.WHITE,
            C.BLACK, C.BROWN, C.RED, C.ORANGE,
            C.ORANGE, C.YELLOW, C.GREEN, C.BLUE,
            C.BLUE, C.VIOLET, C.GRAY, C.WHITE,
            C.WHITE, C.RESISTOR_BEIGE
        )

        val resistor = ResistorVtc()
        resistor.band5 = "${S.PM}5%"
        resistor.navBarSelection = 1

        // test multiplier
        for (i in answers.indices) {
            resistor.units = units[i]
            resistor.resistance = resistances[i]
            ResistorFormatter.generateResistor(resistor)
            assertEquals(answers[i], resistor.band4)
        }
    }

    @Test
    fun `numerical input for multiplier band of five band resistors`() {
        val resistances = listOf(
            "1", "10", "100", "1000", "10000",
            "1", "10", "100", "1000", "10000",
            "1", "10", "100", "1000", "10000",
            "1", "10", "100",
            "23", "230", "2300", "23000",
            "23", "230", "2300", "23000",
            "23", "230", "2300", "23000",
            "23", "230",
            "456", "4560", "45600",
            "456", "4560", "45600",
            "456", "4560", "45600",
            "456", "4560" // blank, since invalid
        )

        val units = listOf(
            S.OHMS, S.OHMS, S.OHMS, S.OHMS, S.OHMS,
            S.KOHMS, S.KOHMS, S.KOHMS, S.KOHMS, S.KOHMS,
            S.MOHMS, S.MOHMS, S.MOHMS, S.MOHMS, S.MOHMS,
            S.GOHMS, S.GOHMS, S.GOHMS,
            S.OHMS, S.OHMS, S.OHMS, S.OHMS,
            S.KOHMS, S.KOHMS, S.KOHMS, S.KOHMS,
            S.MOHMS, S.MOHMS, S.MOHMS, S.MOHMS,
            S.GOHMS, S.GOHMS,
            S.OHMS, S.OHMS, S.OHMS,
            S.KOHMS, S.KOHMS, S.KOHMS,
            S.MOHMS, S.MOHMS, S.MOHMS,
            S.GOHMS, S.GOHMS
        )

        val answers = listOf(
            C.SILVER, C.GOLD, C.BLACK, C.BROWN, C.RED,
            C.BROWN, C.RED, C.ORANGE, C.YELLOW, C.GREEN,
            C.YELLOW, C.GREEN, C.BLUE, C.VIOLET, C.GRAY,
            C.VIOLET, C.GRAY, C.WHITE,
            C.GOLD, C.BLACK, C.BROWN, C.RED,
            C.RED, C.ORANGE, C.YELLOW, C.GREEN,
            C.GREEN, C.BLUE, C.VIOLET, C.GRAY,
            C.GRAY, C.WHITE,
            C.BLACK, C.BROWN, C.RED,
            C.ORANGE, C.YELLOW, C.GREEN,
            C.BLUE, C.VIOLET, C.GRAY,
            C.WHITE, C.RESISTOR_BEIGE
        )

        val resistor = ResistorVtc()
        resistor.band5 = "${S.PM}5%"
        resistor.navBarSelection = 2

        // test multiplier
        for (i in answers.indices) {
            resistor.units = units[i]
            resistor.resistance = resistances[i]
            ResistorFormatter.generateResistor(resistor)
            assertEquals(answers[i], resistor.band4)
        }
    }

    @Test
    fun `decimal inputs for multiplier band of four band resistors`() {
        val resistances = listOf(
            "1.0", "10.0", "1.0", "10.0", "1.0", "10.0", "1.0", "10.0",
            "23.0", "23.0", "23.0", "23.0", "23.", "23.", "23.", "23.",
            "4.5", "4.5", "4.5", "4.5",
        )

        val units = listOf(
            S.OHMS, S.OHMS, S.KOHMS, S.KOHMS, S.MOHMS, S.MOHMS, S.GOHMS, S.GOHMS,
            S.OHMS, S.KOHMS, S.MOHMS, S.GOHMS, S.OHMS, S.KOHMS, S.MOHMS, S.GOHMS,
            S.OHMS, S.KOHMS, S.MOHMS, S.GOHMS,
        )

        val answers = listOf(
            C.GOLD, C.BLACK, C.RED, C.ORANGE, C.GREEN, C.BLUE, C.GRAY, C.WHITE,
            C.BLACK, C.ORANGE, C.BLUE, C.WHITE, C.BLACK, C.ORANGE, C.BLUE, C.WHITE,
            C.GOLD, C.RED, C.GREEN, C.GRAY,
        )

        val resistor = ResistorVtc()
        resistor.band5 = "${S.PM}5%"
        resistor.navBarSelection = 1

        // test multiplier
        for (i in answers.indices) {
            resistor.units = units[i]
            resistor.resistance = resistances[i]
            ResistorFormatter.generateResistor(resistor)
            assertEquals(answers[i], resistor.band4)
        }
    }

    @Test
    fun `decimal inputs for multiplier band of five band resistors`() {
        val resistances = listOf(
            "1.0", "10.0", "100.0", "1.0", "10.0", "100.0", "1.0", "10.0", "100.0", "1.0", "10.0", "100.0",
            "23.0", "230.0", "23.0", "230.0", "23.0", "230.0", "23.0", "230.0",
            "456.0", "456.0", "456.0", "456.0", "456.", "456.", "456.", "456.",
            "4.5", "4.5", "4.50", "4.50", "4.56", "4.56", "4.56", "4.56",
            "78.9", "78.9", "78.9", "78.9", "70.9", "70.9", "70.9", "70.9", "1.00"
        )

        val units = listOf(
            S.OHMS, S.OHMS, S.OHMS, S.KOHMS, S.KOHMS, S.KOHMS, S.MOHMS, S.MOHMS, S.MOHMS, S.GOHMS, S.GOHMS, S.GOHMS,
            S.OHMS, S.OHMS, S.KOHMS, S.KOHMS, S.MOHMS, S.MOHMS, S.GOHMS, S.GOHMS,
            S.OHMS, S.KOHMS, S.MOHMS, S.GOHMS, S.OHMS, S.KOHMS, S.MOHMS, S.GOHMS,
            S.OHMS, S.KOHMS, S.MOHMS, S.GOHMS, S.OHMS, S.KOHMS, S.MOHMS, S.GOHMS,
            S.OHMS, S.KOHMS, S.MOHMS, S.GOHMS, S.OHMS, S.KOHMS, S.MOHMS, S.GOHMS, S.OHMS
        )

        val answers = listOf(
            C.SILVER, C.GOLD, C.BLACK, C.BROWN, C.RED, C.ORANGE, C.YELLOW, C.GREEN, C.BLUE, C.VIOLET, C.GRAY, C.WHITE,
            C.GOLD, C.BLACK, C.RED, C.ORANGE, C.GREEN, C.BLUE, C.GRAY, C.WHITE,
            C.BLACK, C.ORANGE, C.BLUE, C.WHITE, C.BLACK, C.ORANGE, C.BLUE, C.WHITE,
            C.SILVER, C.BROWN, C.YELLOW, C.VIOLET, C.SILVER, C.BROWN, C.YELLOW, C.VIOLET,
            C.GOLD, C.RED, C.GREEN, C.GRAY, C.GOLD, C.RED, C.GREEN, C.GRAY, C.SILVER
        )

        val resistor = ResistorVtc()
        resistor.band5 = "${S.PM}5%"
        resistor.navBarSelection = 2

        // test multiplier
        for (i in answers.indices) {
            resistor.units = units[i]
            resistor.resistance = resistances[i]
            ResistorFormatter.generateResistor(resistor)
            assertEquals(answers[i], resistor.band4)
        }
    }

    @Test
    fun `leading zeros as inputs for four band resistors`() {
        val resistances = listOf(
            "0.1", "0.10", "0.12", "0.1", "0.10", "0.12", "0.1", "0.10", "0.12", "0.1", "0.10", "0.12",
            "0.01", "0.01", "0.01", "0.01"
        )

        var units = listOf(
            S.OHMS, S.OHMS, S.OHMS, S.KOHMS, S.KOHMS, S.KOHMS, S.MOHMS, S.MOHMS, S.MOHMS, S.GOHMS, S.GOHMS, S.GOHMS,
            S.OHMS, S.KOHMS, S.MOHMS, S.GOHMS
        )

        val answers = listOf(
            C.SILVER, C.SILVER, C.SILVER, C.BROWN, C.BROWN, C.BROWN, C.YELLOW, C.YELLOW, C.YELLOW, C.VIOLET, C.VIOLET, C.VIOLET,
            C.SILVER, C.BLACK, C.ORANGE, C.BLUE
        )

        val resistor = ResistorVtc()
        resistor.band5 = "${S.PM}5%"
        resistor.navBarSelection = 1

        // test multiplier
        for (i in answers.indices) {
            resistor.units = units[i]
            resistor.resistance = resistances[i]
            ResistorFormatter.generateResistor(resistor)
            assertEquals(answers[i], resistor.band4)
        }

        val resistor1 = ResistorVtc("0.01", S.OHMS, C.GOLD, "").apply {
            this.band1 = C.BLACK
            this.band2 = C.BROWN
            this.band4 = C.SILVER
        }
        val resistor2 = ResistorVtc("0.01", S.KOHMS, C.GOLD, "").apply {
            this.band1 = C.BROWN
            this.band2 = C.BLACK
            this.band4 = C.BLACK
        }
        val resistor3 = ResistorVtc("0.01", S.MOHMS, C.GOLD, "").apply {
            this.band1 = C.BROWN
            this.band2 = C.BLACK
            this.band4 = C.ORANGE
        }
        val resistor4 = ResistorVtc("0.01", S.GOHMS, C.GOLD, "").apply {
            this.band1 = C.BROWN
            this.band2 = C.BLACK
            this.band4 = C.BLUE
        }

        // test resistor
        val resistors = listOf(
            resistor1, // 0.01 OHMS
            resistor2, // 0.01 KOHMS -> 10 OHMS
            resistor3, // 0.01 MOHMS -> 10 KOHMS
            resistor4, // 0.01 GOHMS -> 10 MOHMS
        )

        resistor.resistance = "0.01"
        resistor.band5 = C.GOLD
        units = listOf(S.OHMS, S.KOHMS, S.MOHMS, S.GOHMS)
        for (i in resistors.indices) {
            resistor.units = units[i]
            ResistorFormatter.generateResistor(resistor)
            assertEquals(resistors[i], resistor)
        }
    }

    @Test
    fun `leading zeros as inputs for five band resistors`() {
        val resistances = listOf(
            "0.10", "0.12", "0.10", "0.12", "0.10", "0.12", "0.10", "0.12",
            "0.01", "0.01", "0.01", "0.01"
        )

        var units = listOf(
            S.OHMS, S.OHMS, S.KOHMS, S.KOHMS, S.MOHMS, S.MOHMS, S.GOHMS, S.GOHMS,
            S.OHMS, S.KOHMS, S.MOHMS, S.GOHMS,
        )

        val answers = listOf(
            C.SILVER, C.SILVER, C.BLACK, C.BLACK, C.ORANGE, C.ORANGE, C.BLUE, C.BLUE,
            C.SILVER, C.GOLD, C.RED, C.GREEN
        )

        val resistor = ResistorVtc()
        resistor.band5 = "${S.PM}5%"
        resistor.navBarSelection = 2

        // test multiplier
        for (i in answers.indices) {
            resistor.units = units[i]
            resistor.resistance = resistances[i]
            ResistorFormatter.generateResistor(resistor)
            assertEquals(answers[i], resistor.band4)
        }

        val resistor1 = ResistorVtc("0.01", S.OHMS, C.GOLD, "", 2).apply {
            this.band1 = C.BLACK
            this.band2 = C.BLACK
            this.band3 = C.BROWN
            this.band4 = C.SILVER
        }
        val resistor2 = ResistorVtc("0.01", S.KOHMS, C.GOLD, "", 2).apply {
            this.band1 = C.BROWN
            this.band2 = C.BLACK
            this.band3 = C.BLACK
            this.band4 = C.GOLD
        }
        val resistor3 = ResistorVtc("0.01", S.MOHMS, C.GOLD, "", 2).apply {
            this.band1 = C.BROWN
            this.band2 = C.BLACK
            this.band3 = C.BLACK
            this.band4 = C.RED
        }
        val resistor4 = ResistorVtc("0.01", S.GOHMS, C.GOLD, "", 2).apply {
            this.band1 = C.BROWN
            this.band2 = C.BLACK
            this.band3 = C.BLACK
            this.band4 = C.GREEN
        }

        // test resistor
        val resistors = listOf(
            resistor1, // 0.01 OHMS
            resistor2, // 0.01 KOHMS -> 10 OHMS
            resistor3, // 0.01 MOHMS -> 10 KOHMS
            resistor4, // 0.01 GOHMS -> 10 MOHMS
        )

        resistor.resistance = "0.01"
        resistor.band5 = C.GOLD
        units = listOf(S.OHMS, S.KOHMS, S.MOHMS, S.GOHMS)
        for (i in resistors.indices) {
            resistor.units = units[i]
            ResistorFormatter.generateResistor(resistor)
            assertEquals(resistors[i], resistor)
        }
    }

    @Test
    fun `not valid unit string`() {
        val resistor = ResistorVtc()
        resistor.resistance = "12"
        resistor.units = "notValid"
        ResistorFormatter.generateResistor(resistor)
        assertEquals(C.BLACK, resistor.band4)
    }
}
