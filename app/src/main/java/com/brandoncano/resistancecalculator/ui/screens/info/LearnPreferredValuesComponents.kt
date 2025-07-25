package com.brandoncano.resistancecalculator.ui.screens.info

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.brandoncano.resistancecalculator.ui.composables.m3.M3OutlinedCard
import com.brandoncano.sharedcomponents.composables.AppTable

@Composable
fun ESeriesTable(seriesName: String, values: List<Int>) {
    val rows: List<List<String>> = values
        .chunked(6)
        .map { chunk -> chunk.map { it.toString() } }
    M3OutlinedCard(
        modifier = Modifier.fillMaxWidth()
    ) {
        AppTable(
            columnTitles = listOf(seriesName),
            rows = rows
        )
    }
}
