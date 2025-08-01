package com.brandoncano.inductancecalculator.util

import androidx.compose.ui.graphics.Color
import com.brandoncano.inductancecalculator.constants.Colors as C
import com.brandoncano.inductancecalculator.constants.Symbols as S
import com.brandoncano.inductancecalculator.ui.theme.black
import com.brandoncano.inductancecalculator.ui.theme.blue
import com.brandoncano.inductancecalculator.ui.theme.brown
import com.brandoncano.inductancecalculator.ui.theme.gold
import com.brandoncano.inductancecalculator.ui.theme.gray
import com.brandoncano.inductancecalculator.ui.theme.green
import com.brandoncano.inductancecalculator.ui.theme.inductor_green
import com.brandoncano.inductancecalculator.ui.theme.inductor_wire
import com.brandoncano.inductancecalculator.ui.theme.orange
import com.brandoncano.inductancecalculator.ui.theme.red
import com.brandoncano.inductancecalculator.ui.theme.silver
import com.brandoncano.inductancecalculator.ui.theme.violet
import com.brandoncano.inductancecalculator.ui.theme.white
import com.brandoncano.inductancecalculator.ui.theme.yellow

/**
 * Job: Find the correct color, either as a string or resource based on the input.
 */
object ColorFinder {

    fun textToColor(text: String): Color {
        return when (text) {
            C.BLACK, "${S.PM}20%" -> black
            C.BROWN, "${S.PM}1%" -> brown
            C.RED, "${S.PM}2%" -> red
            C.ORANGE, "${S.PM}3%" -> orange
            C.YELLOW, "${S.PM}4%" -> yellow
            C.GREEN -> green
            C.BLUE -> blue
            C.VIOLET -> violet
            C.GRAY -> gray
            C.WHITE -> white
            C.GOLD, "${S.PM}5%" -> gold
            C.SILVER, "${S.PM}10%" -> silver
            C.INDUCTOR_WIRE -> inductor_wire
            else -> inductor_green
        }
    }

    fun numberToText(color: Int = -1): String {
        return when (color) {
            0 -> C.BLACK
            1 -> C.BROWN
            2 -> C.RED
            3 -> C.ORANGE
            4 -> C.YELLOW
            5 -> C.GREEN
            6 -> C.BLUE
            7 -> C.VIOLET
            8 -> C.GRAY
            9 -> C.WHITE
            else -> C.INDUCTOR_GREEN
        }
    }

    fun colorToColorText(color: Color): String {
        return when (color) {
            black -> C.BLACK
            brown -> C.BROWN
            red -> C.RED
            orange -> C.ORANGE
            yellow -> C.YELLOW
            green -> C.GREEN
            blue -> C.BLUE
            violet -> C.VIOLET
            gray -> C.GRAY
            white -> C.WHITE
            gold -> C.GOLD
            silver -> C.SILVER
            else -> "" // we don't want an option since this is used for sharing VtC
        }
    }
}
