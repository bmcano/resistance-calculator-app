package com.brandoncano.resistancecalculator.to

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.brandoncano.resistancecalculator.constants.Symbols
import java.io.Serializable

data class Circuit(
    val resistorInputs: SnapshotStateList<String> = mutableStateListOf(*Array(8) { "" }),
    val isSameValues: Boolean = false,
    val resistorCount: Int = 2,
    val units: String = Symbols.OHMS,
    val totalResistance: String = "0",
) : Serializable
