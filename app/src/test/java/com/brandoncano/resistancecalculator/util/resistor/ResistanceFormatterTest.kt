package com.brandoncano.resistancecalculator.util.resistor

import android.content.Context
import com.brandoncano.resistancecalculator.data.SharedPreferences
import com.brandoncano.resistancecalculator.to.ResistorCtv
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Test
import com.brandoncano.resistancecalculator.constants.Colors as C
import com.brandoncano.resistancecalculator.constants.Symbols as S

class ResistanceFormatterTest {

    @Test
    fun `empty resistor`() {
        assertEquals("Select colors", ResistanceFormatter.calculate(ResistorCtv()))
    }

    @Test
    fun `four band resistors resistance text testing`() {
        val resistors = listOf(
            ResistorCtv(),
            ResistorCtv(),
            ResistorCtv(),
            ResistorCtv(),
            ResistorCtv(),
            ResistorCtv(),
            ResistorCtv(),
        )

        val bands = listOf(
            listOf(C.BLUE, C.GRAY, C.BLACK, C.GOLD),
            listOf(C.YELLOW, C.VIOLET, C.BROWN, C.GOLD),
            listOf(C.ORANGE, C.ORANGE, C.BROWN, C.GOLD),
            listOf(C.BROWN, C.BLACK, C.BROWN, C.GOLD),
            listOf(C.BROWN, C.RED, C.RED, C.GOLD),
            listOf(C.BROWN, C.BLACK, C.RED, C.GOLD),
            listOf(C.WHITE, C.BLACK, C.RED, C.GOLD),
        )

        resistors.forEachIndexed { index, resistorCtV ->
            resistorCtV.band1 = bands[index][0]
            resistorCtV.band2 = bands[index][1]
            resistorCtV.band4 = bands[index][2]
            resistorCtV.band5 = bands[index][3]
        }

        val answers = listOf(
            "68 ${S.OHMS} ${S.PM}5%", "470 ${S.OHMS} ${S.PM}5%", "330 ${S.OHMS} ${S.PM}5%",
            "100 ${S.OHMS} ${S.PM}5%", "1.2 ${S.KOHMS} ${S.PM}5%", "1.0 ${S.KOHMS} ${S.PM}5%",
            "9.0 ${S.KOHMS} ${S.PM}5%",
        )

        for (i in answers.indices) {
            assertEquals(answers[i], ResistanceFormatter.calculate(resistors[i]))
        }
    }

    @Test
    fun `five band resistors resistance text testing`() {
        val context: Context = mockk<Context>()
        val resistors = listOf(
            // general
            ResistorCtv(),
            ResistorCtv(),
            ResistorCtv(),
            ResistorCtv(),
        )

        val bands = listOf(
            listOf(C.GREEN, C.BROWN, C.BLACK, C.GOLD, C.BROWN),
            listOf(C.BROWN, C.GREEN, C.RED, C.BLUE, C.GOLD),
            listOf(C.ORANGE, C.BLACK, C.BLACK, C.BLUE, C.GOLD),
            listOf(C.VIOLET, C.GRAY, C.WHITE, C.BLUE, C.GOLD),
        )

        resistors.forEachIndexed { index, resistorCtV ->
            resistorCtV.band1 = bands[index][0]
            resistorCtV.band2 = bands[index][1]
            resistorCtV.band3 = bands[index][2]
            resistorCtV.band4 = bands[index][3]
            resistorCtV.band5 = bands[index][4]
        }

        val answers = listOf(
            "51.0 ${S.OHMS} ${S.PM}1%", "152 ${S.MOHMS} ${S.PM}5%", "300 ${S.MOHMS} ${S.PM}5%",
            "789 ${S.MOHMS} ${S.PM}5%",
        )

        every { SharedPreferences.NAVBAR_SELECTION_CTV.saveData(context, "5") } answers { }

        for (i in answers.indices) {
            resistors[i].navBarSelection = 5
            assertEquals(answers[i], ResistanceFormatter.calculate(resistors[i]))
        }
    }

    @Test
    fun `six band resistors ppm bands`() {
        val context: Context = mockk<Context>()
        val ppmBands = listOf(
            C.BLACK, C.BROWN, C.RED, C.ORANGE, C.YELLOW, C.GREEN, C.BLUE, C.VIOLET, C.GRAY
        )

        val answers = listOf(
            "12.5 ${S.KOHMS} ${S.PM}5%, 250 ${S.PPM}",
            "12.5 ${S.KOHMS} ${S.PM}5%, 100 ${S.PPM}",
            "12.5 ${S.KOHMS} ${S.PM}5%, 50 ${S.PPM}",
            "12.5 ${S.KOHMS} ${S.PM}5%, 15 ${S.PPM}",
            "12.5 ${S.KOHMS} ${S.PM}5%, 25 ${S.PPM}",
            "12.5 ${S.KOHMS} ${S.PM}5%, 20 ${S.PPM}",
            "12.5 ${S.KOHMS} ${S.PM}5%, 10 ${S.PPM}",
            "12.5 ${S.KOHMS} ${S.PM}5%, 5 ${S.PPM}",
            "12.5 ${S.KOHMS} ${S.PM}5%, 1 ${S.PPM}",
        )

        every { SharedPreferences.NAVBAR_SELECTION_CTV.saveData(context, "6") } answers { }

        val resistor = ResistorCtv(C.BROWN, C.RED, C.GREEN, C.RED, C.GOLD, C.BLACK, 3)
        for (i in answers.indices) {
            resistor.band6 = ppmBands[i]
            assertEquals(answers[i], ResistanceFormatter.calculate(resistor))
        }
    }

    @Test
    fun `code coverage`() {
        val resistor = ResistorCtv(C.GOLD, C.GOLD, "", C.GOLD, C.GOLD)
        val answer = "0 ${S.OHMS} ${S.PM}5%"
        assertEquals(answer, ResistanceFormatter.calculate(resistor))
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }
}