package com.brandoncano.resistancecalculator.ui.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.brandoncano.resistancecalculator.ui.composables.m3.M3Divider
import com.brandoncano.resistancecalculator.ui.composables.m3.M3OutlinedCard
import com.brandoncano.resistancecalculator.ui.theme.gray
import com.brandoncano.sharedcomponents.composables.AppComponentPreviews

/**
 * A reusable table composable supporting dynamic columns and rows.
 *
 * @param modifier Modifier to apply to the table container.
 * @param columnTitles List of column header strings.
 * @param rows List of rows, each row is a list of cell strings matching columnTitles size.
 * @param headerTextStyle TextStyle for header cells; defaults to headline style.
 * @param cellTextStyle TextStyle for body cells; defaults to subhead style on surfaceVariant.
 * @param showDividers Whether to show dividers between header and rows and between rows; default true.
 */
@Composable
fun M3Table(
    modifier: Modifier = Modifier,
    columnTitles: List<String>,
    rows: List<List<String>>,
    horizontalPadding: Dp = 16.dp,
    verticalPadding: Dp = 8.dp,
    headerTextStyle: TextStyle = MaterialTheme.typography.titleMedium.gray(),
    cellTextStyle: TextStyle = MaterialTheme.typography.bodyMedium.gray(),
    showDividers: Boolean = true,
) {
    val numColumns = columnTitles.size
    val weights = List(numColumns) { 1f / numColumns }
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = horizontalPadding, vertical = verticalPadding),
    ) {
        Row {
            columnTitles.forEachIndexed { index, title ->
                TableCell(
                    text = title,
                    weight = weights.getOrElse(index) { 1f / numColumns },
                    style = headerTextStyle,
                    isHeader = true,
                )
            }
        }
        M3Divider(modifier = Modifier.padding(vertical = 4.dp))
        rows.forEachIndexed { rowIndex, row ->
            Row(modifier = Modifier.fillMaxWidth()) {
                row.forEachIndexed { colIndex, cellText ->
                    TableCell(
                        text = cellText,
                        weight = weights.getOrElse(colIndex) { 1f / numColumns },
                        style = cellTextStyle,
                    )
                }
            }
            if (showDividers && rowIndex < rows.lastIndex) {
                M3Divider(modifier = Modifier.padding(vertical = 4.dp))
            }
        }
    }
}

@Composable
private fun RowScope.TableCell(
    text: String,
    weight: Float,
    style: TextStyle,
    isHeader: Boolean = false,
) {
    Text(
        text = text,
        modifier = Modifier
            .weight(weight)
            .padding(
                start = 8.dp,
                end = 8.dp,
                top = if (isHeader) 8.dp else 4.dp,
                bottom = if (isHeader) 8.dp else 4.dp,
            ),
        style = style,
        textAlign = TextAlign.Center,
    )
}

@AppComponentPreviews
@Composable
private fun AppTablePreviews() {
    val headers = listOf("Column1", "Column2", "Column3", "Column4")
    val rows = listOf(
        listOf("11", "12", "13", "14"),
        listOf("21", "22", "23", "24"),
        listOf("31", "32", "33", "34"),
        listOf("41", "42", "43", "44"),
    )
    Column {
        M3OutlinedCard {
            M3Table(
                columnTitles = headers,
                rows = rows,
                showDividers = true,
            )
        }
        M3OutlinedCard(
            modifier = Modifier.padding(vertical = 16.dp)
        ) {
            M3Table(
                columnTitles = headers.take(3),
                rows = rows.map { row -> row.take(3) },
                horizontalPadding = 8.dp,
                showDividers = false,
            )
        }
        M3OutlinedCard {
            M3Table(
                columnTitles = headers.take(2),
                rows = rows.map { row -> row.take(2) },
                showDividers = false,
            )
        }
    }
}
