package com.brandoncano.resistancecalculator.ui.theme

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import com.brandoncano.sharedcomponents.composables.AppComponentPreviews
import com.brandoncano.sharedcomponents.text.rubikFontFamily

val bodyFontFamily = rubikFontFamily
val displayFontFamily = rubikFontFamily

val baseline = Typography()
val RubikTypography = Typography(
    displayLarge = baseline.displayLarge.copy(fontFamily = displayFontFamily),
    displayMedium = baseline.displayMedium.copy(fontFamily = displayFontFamily),
    displaySmall = baseline.displaySmall.copy(fontFamily = displayFontFamily),
    headlineLarge = baseline.headlineLarge.copy(fontFamily = displayFontFamily),
    headlineMedium = baseline.headlineMedium.copy(fontFamily = displayFontFamily),
    headlineSmall = baseline.headlineSmall.copy(fontFamily = displayFontFamily),
    titleLarge = baseline.titleLarge.copy(fontFamily = displayFontFamily),
    titleMedium = baseline.titleMedium.copy(fontFamily = displayFontFamily),
    titleSmall = baseline.titleSmall.copy(fontFamily = displayFontFamily),
    bodyLarge = baseline.bodyLarge.copy(fontFamily = bodyFontFamily),
    bodyMedium = baseline.bodyMedium.copy(fontFamily = bodyFontFamily),
    bodySmall = baseline.bodySmall.copy(fontFamily = bodyFontFamily),
    labelLarge = baseline.labelLarge.copy(fontFamily = bodyFontFamily),
    labelMedium = baseline.labelMedium.copy(fontFamily = bodyFontFamily),
    labelSmall = baseline.labelSmall.copy(fontFamily = bodyFontFamily),
)

@AppComponentPreviews
@Composable
private fun AppTypographyPreview() {
    Column {
        Text(text="displayLarge", style = RubikTypography.displayLarge)
        Text(text="displayMedium", style = RubikTypography.displayMedium)
        Text(text="displaySmall", style = RubikTypography.displaySmall)
        Text(text="headlineLarge", style = RubikTypography.headlineLarge)
        Text(text="headlineMedium", style = RubikTypography.headlineMedium)
        Text(text="headlineSmall", style = RubikTypography.headlineSmall)
        Text(text="titleLarge", style = RubikTypography.titleLarge)
        Text(text="titleMedium", style = RubikTypography.titleMedium)
        Text(text="titleSmall", style = RubikTypography.titleSmall)
        Text(text="bodyLarge", style = RubikTypography.bodyLarge)
        Text(text="bodyMedium", style = RubikTypography.bodyMedium)
        Text(text="bodySmall", style = RubikTypography.bodySmall)
        Text(text="labelLarge", style = RubikTypography.labelLarge)
        Text(text="labelMedium", style = RubikTypography.labelMedium)
        Text(text="labelSmall", style = RubikTypography.labelSmall)
    }
}
