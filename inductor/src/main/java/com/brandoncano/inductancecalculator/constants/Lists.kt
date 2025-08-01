package com.brandoncano.inductancecalculator.constants

import com.brandoncano.inductancecalculator.R
import com.brandoncano.inductancecalculator.data.DropdownItemPO
import com.brandoncano.inductancecalculator.data.InductorColorCodeItemPO

/**
 * Job: Holds the list of items for each dropdown
 */
object Lists {

    val UNITS = listOf(Symbols.UH, Symbols.MH)

    val INDUCTOR_SIG_FIGS = listOf(
        DropdownItemPO(Colors.BLACK, "0"),
        DropdownItemPO(Colors.BROWN, "1"),
        DropdownItemPO(Colors.RED, "2"),
        DropdownItemPO(Colors.ORANGE, "3"),
        DropdownItemPO(Colors.YELLOW, "4"),
        DropdownItemPO(Colors.GREEN, "5"),
        DropdownItemPO(Colors.BLUE, "6"),
        DropdownItemPO(Colors.VIOLET, "7"),
        DropdownItemPO(Colors.GRAY, "8"),
        DropdownItemPO(Colors.WHITE, "9"),
    )

    val INDUCTOR_SIG_FIGS_NO_BLACK = INDUCTOR_SIG_FIGS.drop(1)

    val INDUCTOR_MULTIPLIERS = listOf(
        DropdownItemPO(Colors.BLACK, "x 1"),
        DropdownItemPO(Colors.BROWN, "x 10"),
        DropdownItemPO(Colors.RED, "x 100"),
        DropdownItemPO(Colors.ORANGE, "x 1000"),
        DropdownItemPO(Colors.YELLOW, "x 10000"),
        DropdownItemPO(Colors.GOLD, "x 0.1"),
        DropdownItemPO(Colors.SILVER, "x 0.01"),
    )

    val INDUCTOR_TOLERANCES = listOf(
        DropdownItemPO(Colors.BLACK, "${Symbols.PM}20%"),
        DropdownItemPO(Colors.BROWN, "${Symbols.PM}1%"),
        DropdownItemPO(Colors.RED, "${Symbols.PM}2%"),
        DropdownItemPO(Colors.ORANGE, "${Symbols.PM}3%"),
        DropdownItemPO(Colors.YELLOW, "${Symbols.PM}4%"),
        DropdownItemPO(Colors.GOLD, "${Symbols.PM}5%"),
        DropdownItemPO(Colors.SILVER, "${Symbols.PM}10%"),
    )

    val INDUCTOR_COLOR_CODES = listOf(
        InductorColorCodeItemPO(R.string.color_black, "0", "${Symbols.X}1", "${Symbols.PM}20%"),
        InductorColorCodeItemPO(R.string.color_brown, "1", "${Symbols.X}10", "${Symbols.PM}1%"),
        InductorColorCodeItemPO(R.string.color_red, "2", "${Symbols.X}100", "${Symbols.PM}2%"),
        InductorColorCodeItemPO(R.string.color_orange, "3", "${Symbols.X}1k", "${Symbols.PM}3%"),
        InductorColorCodeItemPO(R.string.color_yellow, "4", "${Symbols.X}10k", "${Symbols.PM}4%"),
        InductorColorCodeItemPO(R.string.color_green, "5", "-", "-"),
        InductorColorCodeItemPO(R.string.color_blue, "6", "-", "-"),
        InductorColorCodeItemPO(R.string.color_violet, "7", "-", "-"),
        InductorColorCodeItemPO(R.string.color_gray, "8", "-", "-"),
        InductorColorCodeItemPO(R.string.color_white, "9", "-", "-"),
        InductorColorCodeItemPO(R.string.color_gold, "-", "${Symbols.X}0.1", "${Symbols.PM}5%"),
        InductorColorCodeItemPO(R.string.color_silver, "-", "${Symbols.X}0.01", "${Symbols.PM}10%"),
    )
}
