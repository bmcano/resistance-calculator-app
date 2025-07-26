package com.brandoncano.resistancecalculator.ui.composables.m3

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.dimensionResource
import com.brandoncano.resistancecalculator.R

/**
 * Reusable scaffold that accounts for edge-to-edge and scroll behaviors
 */
@OptIn(ExperimentalMaterial3Api::class) // For TopAppBar
@Composable
fun M3Scaffold(
    modifier: Modifier = Modifier,
    topBar: @Composable ((scrollBehavior: TopAppBarScrollBehavior) -> Unit) = {},
    bottomBar: @Composable () -> Unit = {},
    scrollBehavior: TopAppBarScrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(),
    content: @Composable ((PaddingValues) -> Unit),
) {
    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = { topBar.invoke(scrollBehavior) },
        bottomBar = bottomBar,
        contentWindowInsets = WindowInsets.safeDrawing,
        content = { content.invoke(it) }
    )
}

/**
 * Reusable screen column that accounts for edge-to-edge and scroll states
 */
@Composable
fun M3ScreenColumn(
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    scrollState: ScrollState = rememberScrollState(),
    content: @Composable (ColumnScope.() -> Unit)
) {
    val sidePadding = dimensionResource(R.dimen.app_side_padding)
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(paddingValues)
            .padding(horizontal = sidePadding),
        horizontalAlignment = horizontalAlignment,
        content = content,
    )
}

// TODO Lazy Column Next?
// CircuitScreen, Info Screens?