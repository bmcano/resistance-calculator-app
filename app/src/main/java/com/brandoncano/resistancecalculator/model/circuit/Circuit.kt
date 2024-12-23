package com.brandoncano.resistancecalculator.model.circuit

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.brandoncano.resistancecalculator.constants.Symbols

data class Circuit(
    val resistorInputs: SnapshotStateList<String> = mutableStateListOf(*Array(8) { "" }),
    val sameValues: Boolean = false,
    val resistorCount: Int = 2,
    val units: String = Symbols.OHMS,
    val totalResistance: String = "0",
)