package com.brandoncano.resistancecalculator.data

import com.brandoncano.resistancecalculator.constants.Colors
import com.brandoncano.resistancecalculator.constants.Symbols

/**
 * Job: Holds the list of items for each dropdown
 */
object DropdownLists {

    val NUMBER_LIST = listOf(
        DropdownItem(Colors.BLACK, "0"),
        DropdownItem(Colors.BROWN, "1"),
        DropdownItem(Colors.RED, "2"),
        DropdownItem(Colors.ORANGE, "3"),
        DropdownItem(Colors.YELLOW, "4"),
        DropdownItem(Colors.GREEN, "5"),
        DropdownItem(Colors.BLUE, "6"),
        DropdownItem(Colors.VIOLET, "7"),
        DropdownItem(Colors.GRAY, "8"),
        DropdownItem(Colors.WHITE, "9"),
    )

    val NUMBER_LIST_NO_BLACK = NUMBER_LIST.drop(1)

    val MULTIPLIER_LIST = listOf(
        DropdownItem(Colors.BLACK, "${Symbols.X}1"),
        DropdownItem(Colors.BROWN, "${Symbols.X}10"),
        DropdownItem(Colors.RED, "${Symbols.X}100"),
        DropdownItem(Colors.ORANGE, "${Symbols.X}1k"),
        DropdownItem(Colors.YELLOW, "${Symbols.X}10k"),
        DropdownItem(Colors.GREEN, "${Symbols.X}100k"),
        DropdownItem(Colors.BLUE, "${Symbols.X}1M"),
        DropdownItem(Colors.VIOLET, "${Symbols.X}10M"),
        DropdownItem(Colors.GRAY, "${Symbols.X}100M"),
        DropdownItem(Colors.WHITE, "${Symbols.X}1G"),
        DropdownItem(Colors.GOLD, "${Symbols.X}0.1"),
        DropdownItem(Colors.SILVER, "${Symbols.X}0.01"),
    )

    val TOLERANCE_LIST = listOf(
        DropdownItem(Colors.BROWN, "${Symbols.PM}1%"),
        DropdownItem(Colors.RED, "${Symbols.PM}2%"),
        DropdownItem(Colors.GREEN, "${Symbols.PM}0.5%"),
        DropdownItem(Colors.BLUE, "${Symbols.PM}0.25%"),
        DropdownItem(Colors.VIOLET, "${Symbols.PM}0.1%"),
        DropdownItem(Colors.GRAY, "${Symbols.PM}0.05%"),
        DropdownItem(Colors.GOLD, "${Symbols.PM}5%"),
        DropdownItem(Colors.SILVER, "${Symbols.PM}10%"),
    )

    val PPM_LIST = listOf(
        DropdownItem(Colors.BLACK, "250 ${Symbols.PPM}"),
        DropdownItem(Colors.BROWN, "100 ${Symbols.PPM}"),
        DropdownItem(Colors.RED, "50 ${Symbols.PPM}"),
        DropdownItem(Colors.ORANGE, "15 ${Symbols.PPM}"),
        DropdownItem(Colors.YELLOW, "25 ${Symbols.PPM}"),
        DropdownItem(Colors.GREEN, "20 ${Symbols.PPM}"),
        DropdownItem(Colors.BLUE, "10 ${Symbols.PPM}"),
        DropdownItem(Colors.VIOLET, "5 ${Symbols.PPM}"),
        DropdownItem(Colors.GRAY, "1 ${Symbols.PPM}"),
    )

    val UNITS_LIST = listOf(Symbols.OHMS, Symbols.KOHMS, Symbols.MOHMS, Symbols.GOHMS)
    val RESISTOR_COUNT_LIST = listOf("2", "3", "4", "5", "6", "7", "8")
}
