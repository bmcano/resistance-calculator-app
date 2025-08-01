package com.brandoncano.library.m3

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.brandoncano.library.R
import com.brandoncano.library.theme.gray

@Composable
private fun getSubtitleLineHeight(textStyle: TextStyle): Dp {
    val subtitleLineHeightDp = with(LocalDensity.current) {
        textStyle.lineHeight.toDp()
    }
    return subtitleLineHeightDp
}

@Composable
private fun TitleContent(
    titleText: String,
    subTitleText: String?,
    titleTextStyle: TextStyle,
    subTitleTextStyle: TextStyle,
) {
    Column {
        Text(
            text = titleText,
            style = titleTextStyle,
            overflow = TextOverflow.Ellipsis,
        )
        subTitleText?.let {
            Text(
                text = subTitleText,
                style = subTitleTextStyle.gray(),
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class) // For TopAppBar
@Composable
fun M3TopAppBar(
    titleText: String,
    subTitleText: String? = null,
    navigationIcon: ImageVector? = null,
    onNavigateBack: () -> Unit = {},

    actions: @Composable RowScope.() -> Unit = {},
    scrollBehavior: TopAppBarScrollBehavior? = null,
) {
    val subtitleLineHeightDp = getSubtitleLineHeight(MaterialTheme.typography.labelMedium)
    val expandedHeight = if (subTitleText != null) {
        TopAppBarDefaults.TopAppBarExpandedHeight + subtitleLineHeightDp
    } else {
        TopAppBarDefaults.TopAppBarExpandedHeight
    }
    TopAppBar(
        title = {
            TitleContent(
                titleText = titleText,
                subTitleText = subTitleText,
                titleTextStyle = MaterialTheme.typography.titleLarge,
                subTitleTextStyle = MaterialTheme.typography.labelMedium,
            )
        },
        modifier = Modifier,
        navigationIcon = {
            if (navigationIcon != null) {
                IconButton(onClick = onNavigateBack) {
                    Icon(
                        imageVector = navigationIcon,
                        contentDescription = "Back",
                    )
                }
            } else {
                Box(
                    modifier = Modifier
                        .minimumInteractiveComponentSize()
                        .size(40.dp),
                    contentAlignment = Alignment.Center,
                ) {
                    // TODO - Rewrite how this is implemented
//                    Image(
//                        painter = painterResource(R.drawable.img_app_icon),
//                        contentDescription = null,
//                    )
                }
            }
        },
        actions = actions,
        expandedHeight = expandedHeight,
        windowInsets = TopAppBarDefaults.windowInsets,
        scrollBehavior = scrollBehavior,
    )
}
