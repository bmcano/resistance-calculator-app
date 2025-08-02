package com.brandoncano.library.theme

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import com.brandoncano.library.R
import com.brandoncano.library.m3.ComponentPreviews

private val rubikFontFamily = FontFamily(
    Font(R.font.rubik_light, FontWeight.Light),
    Font(R.font.rubik_regular, FontWeight.Normal),
    Font(R.font.rubik_italic, FontWeight.Normal, FontStyle.Italic),
    Font(R.font.rubik_medium, FontWeight.Medium),
    Font(R.font.rubik_bold, FontWeight.Bold)
)

private val bodyFontFamily = rubikFontFamily
private val displayFontFamily = rubikFontFamily
private val baseline = Typography()

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

private val lib_white = Color(0xFFF5F5F5)
private val lib_black = Color(0xFF00000F)

@Composable
fun TextStyle.white() = this.merge(color = lib_white)

@Composable
fun TextStyle.black() = this.merge(color = lib_black)

@Composable
fun TextStyle.gray() = this.merge(color = MaterialTheme.colorScheme.onSurfaceVariant)

@ComponentPreviews
@Composable
private fun RubikTypographyPreview() {
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
