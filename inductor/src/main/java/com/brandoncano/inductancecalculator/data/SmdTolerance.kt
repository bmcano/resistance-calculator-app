package com.brandoncano.inductancecalculator.data

import com.brandoncano.inductancecalculator.constants.Symbols as S

enum class SmdTolerance(val letter: String, val tolerance: String) {
    A("A", "${S.PM}0.05nH"),
    B("B", "${S.PM}0.1nH"),
    C("C", "${S.PM}0.2nH"),
    D("D", "${S.PM}0.5nH"),
    E("E", "${S.PM}0.05%"),
    F("F", "${S.PM}1%"),
    G("G", "${S.PM}2%"),
    H("H", "${S.PM}3%"),
    J("J", "${S.PM}5%"),
    K("K", "${S.PM}10%"),
    L("L", "${S.PM}15%"),
    M("M", "${S.PM}20%"),
    V("V", "${S.PM}25%"),
    N("N", "${S.PM}30%"),
    Z("Z", "+80%/-20%");

    companion object {
        fun getLetterList(): List<String> {
            return entries.map { it.letter }
        }

        fun getTolerance(letter: String): String {
            return entries.find { it.letter == letter }?.tolerance ?: ""
        }

        fun getTolerancePairs():  List<List<String>> {
            return entries.map { listOf(it.letter, it.tolerance) }
        }
    }
}
