package com.brandoncano.inductancecalculator.util

import com.brandoncano.inductancecalculator.R
import com.brandoncano.inductancecalculator.constants.Colors
import com.brandoncano.inductancecalculator.data.InductorImageColorPairPO
import com.brandoncano.inductancecalculator.to.InductorVtc
import com.brandoncano.inductancecalculator.to.InductorCtv
import kotlin.collections.map
import kotlin.to

/**
 * Job: Builds the inductor image and defines the colors based on the Inductor object.
 */
object InductorImageBuilder {

    fun execute(inductor: InductorCtv): List<InductorImageColorPairPO> {
        return listOf(
            R.drawable.img_inductor_wire to Colors.INDUCTOR_WIRE,
            R.drawable.img_inductor_end_left to Colors.INDUCTOR_GREEN,
            R.drawable.img_inductor_band_96 to inductor.band1,
            R.drawable.img_inductor_curve_left to Colors.INDUCTOR_GREEN,
            R.drawable.img_inductor_band_64 to inductor.band2,
            R.drawable.img_inductor_band_64_wide to Colors.INDUCTOR_GREEN,
            R.drawable.img_inductor_band_64 to inductor.band3,
            R.drawable.img_inductor_curve_right to Colors.INDUCTOR_GREEN,
            R.drawable.img_inductor_band_96 to inductor.band4,
            R.drawable.img_inductor_end_right to Colors.INDUCTOR_GREEN,
            R.drawable.img_inductor_wire to Colors.INDUCTOR_WIRE
        ).map { (res, color) -> InductorImageColorPairPO(res, color) }
    }

    fun execute(inductor: InductorVtc): List<InductorImageColorPairPO> {
        return listOf(
            R.drawable.img_inductor_wire to Colors.INDUCTOR_WIRE,
            R.drawable.img_inductor_end_left to Colors.INDUCTOR_GREEN,
            R.drawable.img_inductor_band_96 to inductor.band1,
            R.drawable.img_inductor_curve_left to Colors.INDUCTOR_GREEN,
            R.drawable.img_inductor_band_64 to inductor.band2,
            R.drawable.img_inductor_band_64_wide to Colors.INDUCTOR_GREEN,
            R.drawable.img_inductor_band_64 to inductor.band3,
            R.drawable.img_inductor_curve_right to Colors.INDUCTOR_GREEN,
            R.drawable.img_inductor_band_96 to inductor.tolerance,
            R.drawable.img_inductor_end_right to Colors.INDUCTOR_GREEN,
            R.drawable.img_inductor_wire to Colors.INDUCTOR_WIRE
        ).map { (res, color) -> InductorImageColorPairPO(res, color) }
    }
}
