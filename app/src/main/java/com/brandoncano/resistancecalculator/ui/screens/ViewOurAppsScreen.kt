package com.brandoncano.resistancecalculator.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.outlined.Apps
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.brandoncano.library.R as baseR
import com.brandoncano.library.data.ArrowCardButtonPO
import com.brandoncano.library.m3.BottomScreenSpacer
import com.brandoncano.library.m3.DesktopScreenPreview
import com.brandoncano.library.m3.LongScreenPreview
import com.brandoncano.library.m3.M3ArrowButtonCardContent
import com.brandoncano.library.m3.M3Divider
import com.brandoncano.library.m3.M3ElevatedCard
import com.brandoncano.library.m3.M3OutlinedCard
import com.brandoncano.library.m3.M3Scaffold
import com.brandoncano.library.m3.M3ScreenColumn
import com.brandoncano.library.m3.M3TopAppBar
import com.brandoncano.library.m3.ScreenPreviews
import com.brandoncano.library.m3.outlinedCardColor
import com.brandoncano.library.theme.gray
import com.brandoncano.resistancecalculator.R
import com.brandoncano.library.firebase.FirebaseRemoteConfigKeys
import com.brandoncano.library.firebase.getStringOrEmpty

@OptIn(ExperimentalMaterial3Api::class) // For TopAppBar
@Composable
fun ViewOurAppsScreen(
    onNavigateBack: () -> Unit,
    onFeatureCardTapped: () -> Unit,
    onMobileAppCardTapped: (String) -> Unit,
    onViewMoreAppsTapped: () -> Unit,
) {
    M3Scaffold(
        topBar = {
            M3TopAppBar(
                titleText = stringResource(id = R.string.view_our_apps_title),
                navigationIcon = Icons.Filled.Close,
                onNavigateBack = onNavigateBack,
                scrollBehavior = it,
            )
        },
    ) { paddingValues ->
        ViewOurAppsScreenContent(
            paddingValues = paddingValues,
            onFeatureCardTapped = onFeatureCardTapped,
            onMobileAppCardTapped = onMobileAppCardTapped,
            onViewMoreAppsTapped = onViewMoreAppsTapped,
        )
    }
}

@Composable
private fun ViewOurAppsScreenContent(
    paddingValues: PaddingValues,
    onFeatureCardTapped: () -> Unit,
    onMobileAppCardTapped: (String) -> Unit,
    onViewMoreAppsTapped: () -> Unit,
) {
    M3ScreenColumn(
        paddingValues = paddingValues,
    ) {
        Spacer(modifier = Modifier.height(32.dp))
        MobileAppFeatureCard(onClick = onFeatureCardTapped)
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = stringResource(id = R.string.view_our_apps_header),
            style = MaterialTheme.typography.titleMedium,
        )
        Spacer(modifier = Modifier.height(12.dp))
        MobileAppCard(
            appName = stringResource(id = R.string.view_our_apps_capacitor),
            appImageRes = baseR.drawable.img_playstore_capacitor,
            appLink = FirebaseRemoteConfigKeys.PLAYSTORE_CAPACITOR.getStringOrEmpty(),
            onClick = onMobileAppCardTapped,
        )
        Spacer(modifier = Modifier.height(12.dp))
        MobileAppCard(
            appName = stringResource(id = R.string.view_our_apps_inductor),
            appImageRes = baseR.drawable.img_playstore_inductor,
            appLink = FirebaseRemoteConfigKeys.PLAYSTORE_INDUCTOR.getStringOrEmpty(),
            onClick = onMobileAppCardTapped,
        )
        Spacer(modifier = Modifier.height(12.dp))
        MobileAppCard(
            appName = stringResource(id = R.string.view_our_apps_ohms),
            appImageRes = baseR.drawable.img_playstore_ohms,
            appLink = FirebaseRemoteConfigKeys.PLAYSTORE_OHMS.getStringOrEmpty(),
            onClick = onMobileAppCardTapped,
        )
        M3Divider(modifier = Modifier.padding(vertical = 16.dp))
        ViewDeveloperProfileCard(onClick = onViewMoreAppsTapped)
        BottomScreenSpacer()
    }
}

@Composable
private fun MobileAppFeatureCard(
    onClick: () -> Unit,
) {
    M3ElevatedCard(
        modifier = Modifier.clickable { onClick.invoke() }
    ) {
        Image(
            painter = painterResource(id = R.drawable.img_playstore_resistor_feature_graphic),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(max = 172.dp)
                .clip(RoundedCornerShape(16.dp)),
            contentScale = ContentScale.Crop,
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = stringResource(id = R.string.view_out_apps_rate_us),
                modifier = Modifier.padding(start = 16.dp),
                style = MaterialTheme.typography.titleMedium.gray(),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Image(
                imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                contentDescription = null,
                modifier = Modifier.padding(end = 16.dp),
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurfaceVariant),
            )
        }
    }
}

@Composable
private fun MobileAppCard(
    appName: String,
    appImageRes: Int,
    appLink: String,
    onClick: (String) -> Unit,
) {
    M3OutlinedCard(
        modifier = Modifier.clickable { onClick.invoke(appLink) },
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = painterResource(id = appImageRes),
                contentDescription = null,
                modifier = Modifier
                    .size(64.dp)
                    .clip(RoundedCornerShape(16.dp)),
            )
            Text(
                text = appName,
                modifier = Modifier.padding(start = 8.dp).weight(1f),
                style = MaterialTheme.typography.titleSmall.gray(),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )
            Spacer(modifier = Modifier.width(8.dp))
            Image(
                imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                contentDescription = null,
                modifier = Modifier
                    .padding(end = 16.dp)
                    .size(24.dp),
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurfaceVariant),
            )
        }
    }
}

@Composable
private fun ViewDeveloperProfileCard(
    onClick: () -> Unit,
) {
    M3OutlinedCard {
        M3ArrowButtonCardContent(
            color = outlinedCardColor(),
            ArrowCardButtonPO(
                imageVector = Icons.Outlined.Apps,
                text = stringResource(R.string.view_our_apps_all_apps),
                onClick = onClick,
            )
        )
    }
}

@ScreenPreviews
@DesktopScreenPreview
@LongScreenPreview
@Composable
private fun ViewOurAppsScreenPreview() {
    ViewOurAppsScreen(
        onNavigateBack = {},
        onFeatureCardTapped = {},
        onMobileAppCardTapped = {},
        onViewMoreAppsTapped = {},
    )
}
