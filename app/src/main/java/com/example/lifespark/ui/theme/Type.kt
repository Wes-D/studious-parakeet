package com.example.lifespark.ui.theme

import com.example.lifespark.R
import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
val fantasyFontFamily = FontFamily(Font(R.font.josefin_sans, FontWeight.Normal))

val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = fantasyFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = fantasyFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,    // Default bodyMedium font size
        lineHeight = 20.sp,  // Default line height for bodyMedium
        letterSpacing = 0.25.sp
    ),
    bodySmall = TextStyle(
        fontFamily = fantasyFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,    // Default bodySmall font size
        lineHeight = 16.sp,  // Default line height for bodySmall
        letterSpacing = 0.4.sp
    ),
    titleLarge = TextStyle(
        fontFamily = fantasyFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,    // Default titleLarge font size
        lineHeight = 28.sp,  // Default line height for titleLarge
        letterSpacing = 0.sp
    ),
    titleMedium = TextStyle(
        fontFamily = fantasyFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,    // Default titleMedium font size
        lineHeight = 24.sp,  // Default line height for titleMedium
        letterSpacing = 0.15.sp
    ),
    titleSmall = TextStyle(
        fontFamily = fantasyFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,    // Default titleSmall font size
        lineHeight = 20.sp,  // Default line height for titleSmall
        letterSpacing = 0.1.sp
    ),
    labelLarge = TextStyle(
        fontFamily = fantasyFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,    // Default labelLarge font size
        lineHeight = 20.sp,  // Default line height for labelLarge
        letterSpacing = 0.1.sp
    ),
    labelMedium = TextStyle(
        fontFamily = fantasyFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,    // Default labelMedium font size
        lineHeight = 16.sp,  // Default line height for labelMedium
        letterSpacing = 0.5.sp
    ),
    labelSmall = TextStyle(
        fontFamily = fantasyFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 11.sp,    // Default labelSmall font size
        lineHeight = 16.sp,  // Default line height for labelSmall
        letterSpacing = 0.5.sp
    )
)