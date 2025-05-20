package com.brandoncano.resistancecalculator.util.resistor

import com.brandoncano.resistancecalculator.R
import com.brandoncano.resistancecalculator.constants.Colors
import com.brandoncano.resistancecalculator.data.ResistorImageColorPair
import com.brandoncano.resistancecalculator.to.ResistorCtv
import com.brandoncano.resistancecalculator.to.ResistorVtc

/**
 * Job: Builds the resistor image and defines the colors based on the Resistor object.
 */
object ResistorImageBuilder {

    fun execute(resistor: ResistorCtv): List<ResistorImageColorPair> {
        val resistorColor = resistor.deriveResistorColor()
        return listOf(
            R.drawable.img_resistor_wire to Colors.RESISTOR_WIRE,
            R.drawable.img_resistor_end_left to resistorColor,
            R.drawable.img_resistor_band_96 to resistor.bandOneForDisplay(),
            R.drawable.img_resistor_curve_left to resistorColor,
            R.drawable.img_resistor_band_64 to resistor.bandTwoForDisplay(),
            R.drawable.img_resistor_band_64 to resistorColor,
            R.drawable.img_resistor_band_64 to resistor.bandThreeForDisplay(),
            R.drawable.img_resistor_band_64 to resistorColor,
            R.drawable.img_resistor_band_64 to resistor.bandFourForDisplay(),
            R.drawable.img_resistor_band_64_wide to resistorColor,
            R.drawable.img_resistor_band_64_wide to resistor.bandFiveForDisplay(),
            R.drawable.img_resistor_curve_right to resistorColor,
            R.drawable.img_resistor_band_96 to resistor.bandSixForDisplay(),
            R.drawable.img_resistor_end_right to resistorColor,
            R.drawable.img_resistor_wire to Colors.RESISTOR_WIRE
        ).map { (res, color) -> ResistorImageColorPair(res, color) }
    }

    fun execute(resistor: ResistorVtc): List<ResistorImageColorPair> {
        val resistorColor = resistor.deriveResistorColor()
        return listOf(
            R.drawable.img_resistor_wire to Colors.RESISTOR_WIRE,
            R.drawable.img_resistor_end_left to resistorColor,
            R.drawable.img_resistor_band_96 to resistor.bandOneForDisplay(),
            R.drawable.img_resistor_curve_left to resistorColor,
            R.drawable.img_resistor_band_64 to resistor.bandTwoForDisplay(),
            R.drawable.img_resistor_band_64 to resistorColor,
            R.drawable.img_resistor_band_64 to resistor.bandThreeForDisplay(),
            R.drawable.img_resistor_band_64 to resistorColor,
            R.drawable.img_resistor_band_64 to resistor.bandFourForDisplay(),
            R.drawable.img_resistor_band_64_wide to resistorColor,
            R.drawable.img_resistor_band_64_wide to resistor.bandFiveForDisplay(),
            R.drawable.img_resistor_curve_right to resistorColor,
            R.drawable.img_resistor_band_96 to resistor.bandSixForDisplay(),
            R.drawable.img_resistor_end_right to resistorColor,
            R.drawable.img_resistor_wire to Colors.RESISTOR_WIRE
        ).map { (res, color) -> ResistorImageColorPair(res, color) }
    }
}
