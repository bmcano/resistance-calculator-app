package com.brandoncano.resistancecalculator.constants

import com.brandoncano.resistancecalculator.R
import com.brandoncano.resistancecalculator.data.CodeValueItemPO
import com.brandoncano.resistancecalculator.data.DropdownItemPO
import com.brandoncano.resistancecalculator.data.ResistorColorCodeItemPO

/**
 * Job: Holds predefined lists
 */
object Lists {

    val UNITS = listOf(Symbols.OHMS, Symbols.KOHMS, Symbols.MOHMS, Symbols.GOHMS)
    val CIRCUIT_RESISTOR_COUNT = listOf("2", "3", "4", "5", "6", "7", "8")

    val RESISTOR_SIG_FIGS = listOf(
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

    val RESISTOR_SIG_FIGS_NO_BLACK = RESISTOR_SIG_FIGS.drop(1)

    val RESISTOR_MULTIPLIERS = listOf(
        DropdownItemPO(Colors.BLACK, "${Symbols.X}1"),
        DropdownItemPO(Colors.BROWN, "${Symbols.X}10"),
        DropdownItemPO(Colors.RED, "${Symbols.X}100"),
        DropdownItemPO(Colors.ORANGE, "${Symbols.X}1k"),
        DropdownItemPO(Colors.YELLOW, "${Symbols.X}10k"),
        DropdownItemPO(Colors.GREEN, "${Symbols.X}100k"),
        DropdownItemPO(Colors.BLUE, "${Symbols.X}1M"),
        DropdownItemPO(Colors.VIOLET, "${Symbols.X}10M"),
        DropdownItemPO(Colors.GRAY, "${Symbols.X}100M"),
        DropdownItemPO(Colors.WHITE, "${Symbols.X}1G"),
        DropdownItemPO(Colors.GOLD, "${Symbols.X}0.1"),
        DropdownItemPO(Colors.SILVER, "${Symbols.X}0.01"),
    )

    val RESISTOR_TOLERANCES = listOf(
        DropdownItemPO(Colors.BROWN, "${Symbols.PM}1%"),
        DropdownItemPO(Colors.RED, "${Symbols.PM}2%"),
        DropdownItemPO(Colors.GREEN, "${Symbols.PM}0.5%"),
        DropdownItemPO(Colors.BLUE, "${Symbols.PM}0.25%"),
        DropdownItemPO(Colors.VIOLET, "${Symbols.PM}0.1%"),
        DropdownItemPO(Colors.GRAY, "${Symbols.PM}0.05%"),
        DropdownItemPO(Colors.GOLD, "${Symbols.PM}5%"),
        DropdownItemPO(Colors.SILVER, "${Symbols.PM}10%"),
    )

    val RESISTOR_PPM = listOf(
        DropdownItemPO(Colors.BLACK, "250 ${Symbols.PPM}"),
        DropdownItemPO(Colors.BROWN, "100 ${Symbols.PPM}"),
        DropdownItemPO(Colors.RED, "50 ${Symbols.PPM}"),
        DropdownItemPO(Colors.ORANGE, "15 ${Symbols.PPM}"),
        DropdownItemPO(Colors.YELLOW, "25 ${Symbols.PPM}"),
        DropdownItemPO(Colors.GREEN, "20 ${Symbols.PPM}"),
        DropdownItemPO(Colors.BLUE, "10 ${Symbols.PPM}"),
        DropdownItemPO(Colors.VIOLET, "5 ${Symbols.PPM}"),
        DropdownItemPO(Colors.GRAY, "1 ${Symbols.PPM}"),
    )

    val RESISTOR_COLOR_CODES = listOf(
        ResistorColorCodeItemPO(R.string.color_black, "0", "${Symbols.X}1", "-", "250"),
        ResistorColorCodeItemPO(R.string.color_brown, "1", "${Symbols.X}10", "${Symbols.PM}1%", "100"),
        ResistorColorCodeItemPO(R.string.color_red, "2", "${Symbols.X}100", "${Symbols.PM}2%", "50"),
        ResistorColorCodeItemPO(R.string.color_orange, "3", "${Symbols.X}1k", "-", "15"),
        ResistorColorCodeItemPO(R.string.color_yellow, "4", "${Symbols.X}10k", "-", "25"),
        ResistorColorCodeItemPO(R.string.color_green, "5", "${Symbols.X}100k", "${Symbols.PM}0.5%", "20"),
        ResistorColorCodeItemPO(R.string.color_blue, "6", "${Symbols.X}1M", "${Symbols.PM}0.25%", "10"),
        ResistorColorCodeItemPO(R.string.color_violet, "7", "${Symbols.X}10M", "${Symbols.PM}0.1%", "5"),
        ResistorColorCodeItemPO(R.string.color_gray, "8", "${Symbols.X}100M", "${Symbols.PM}0.05%", "1"),
        ResistorColorCodeItemPO(R.string.color_white, "9", "${Symbols.X}1G", "-", "-"),
        ResistorColorCodeItemPO(R.string.color_gold, "-", "${Symbols.X}0.1", "${Symbols.PM}5%", "-"),
        ResistorColorCodeItemPO(R.string.color_silver, "-", "${Symbols.X}0.01", "${Symbols.PM}10%", "-"),
        ResistorColorCodeItemPO(R.string.color_none, "-", "-", "${Symbols.PM}20%", "-")
    )

    val CODE_LOOKUP_TABLE = listOf(
        CodeValueItemPO("01", "100"), CodeValueItemPO("02", "102"), CodeValueItemPO("03", "105"), CodeValueItemPO("04", "107"),
        CodeValueItemPO("05", "110"), CodeValueItemPO("06", "113"), CodeValueItemPO("07", "115"), CodeValueItemPO("08", "118"),
        CodeValueItemPO("09", "121"), CodeValueItemPO("10", "124"), CodeValueItemPO("11", "127"), CodeValueItemPO("12", "130"),
        CodeValueItemPO("13", "133"), CodeValueItemPO("14", "137"), CodeValueItemPO("15", "140"), CodeValueItemPO("16", "143"),
        CodeValueItemPO("17", "147"), CodeValueItemPO("18", "150"), CodeValueItemPO("19", "154"), CodeValueItemPO("20", "158"),
        CodeValueItemPO("21", "162"), CodeValueItemPO("22", "165"), CodeValueItemPO("23", "169"), CodeValueItemPO("24", "174"),
        CodeValueItemPO("25", "178"), CodeValueItemPO("26", "182"), CodeValueItemPO("27", "187"), CodeValueItemPO("28", "191"),
        CodeValueItemPO("29", "196"), CodeValueItemPO("30", "200"), CodeValueItemPO("31", "205"), CodeValueItemPO("32", "210"),
        CodeValueItemPO("33", "215"), CodeValueItemPO("34", "221"), CodeValueItemPO("35", "226"), CodeValueItemPO("36", "232"),
        CodeValueItemPO("37", "237"), CodeValueItemPO("38", "243"), CodeValueItemPO("39", "249"), CodeValueItemPO("40", "255"),
        CodeValueItemPO("41", "261"), CodeValueItemPO("42", "267"), CodeValueItemPO("43", "274"), CodeValueItemPO("44", "280"),
        CodeValueItemPO("45", "287"), CodeValueItemPO("46", "294"), CodeValueItemPO("47", "301"), CodeValueItemPO("48", "309"),
        CodeValueItemPO("49", "316"), CodeValueItemPO("50", "324"), CodeValueItemPO("51", "332"), CodeValueItemPO("52", "340"),
        CodeValueItemPO("53", "348"), CodeValueItemPO("54", "357"), CodeValueItemPO("55", "365"), CodeValueItemPO("56", "374"),
        CodeValueItemPO("57", "383"), CodeValueItemPO("58", "392"), CodeValueItemPO("59", "402"), CodeValueItemPO("60", "412"),
        CodeValueItemPO("61", "422"), CodeValueItemPO("62", "432"), CodeValueItemPO("63", "442"), CodeValueItemPO("64", "453"),
        CodeValueItemPO("65", "464"), CodeValueItemPO("66", "475"), CodeValueItemPO("67", "487"), CodeValueItemPO("68", "499"),
        CodeValueItemPO("69", "511"), CodeValueItemPO("70", "523"), CodeValueItemPO("71", "536"), CodeValueItemPO("72", "549"),
        CodeValueItemPO("73", "562"), CodeValueItemPO("74", "576"), CodeValueItemPO("75", "590"), CodeValueItemPO("76", "604"),
        CodeValueItemPO("77", "619"), CodeValueItemPO("78", "634"), CodeValueItemPO("79", "649"), CodeValueItemPO("80", "665"),
        CodeValueItemPO("81", "681"), CodeValueItemPO("82", "698"), CodeValueItemPO("83", "715"), CodeValueItemPO("84", "732"),
        CodeValueItemPO("85", "750"), CodeValueItemPO("86", "768"), CodeValueItemPO("87", "787"), CodeValueItemPO("88", "806"),
        CodeValueItemPO("89", "825"), CodeValueItemPO("90", "845"), CodeValueItemPO("91", "866"), CodeValueItemPO("92", "887"),
        CodeValueItemPO("93", "909"), CodeValueItemPO("94", "931"), CodeValueItemPO("95", "953"), CodeValueItemPO("96", "976"),
    )
}
