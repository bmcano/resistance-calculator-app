package com.brandoncano.resistancecalculator.data

import java.io.Serializable

sealed class ESeriesCardContent : Serializable {
    data class ValidResistance(val value: String) : ESeriesCardContent()
    data class InvalidTolerance(val value: String) : ESeriesCardContent()
    data class InvalidResistance(val value: String) : ESeriesCardContent()
    data object NoContent: ESeriesCardContent()
}
