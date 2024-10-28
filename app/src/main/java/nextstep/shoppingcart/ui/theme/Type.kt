package nextstep.shoppingcart.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
val Typography =
    Typography(
        headlineMedium =
            TextStyle(
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.Normal,
                fontSize = 22.sp,
                lineHeight = 28.sp,
                color = Gray50,
            ),
        titleLarge =
            TextStyle(
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.W700,
                fontSize = 24.sp,
                lineHeight = 28.sp,
                letterSpacing = 0.5.sp,
                color = Gray20,
            ),
        titleSmall =
            TextStyle(
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.W700,
                fontSize = 16.sp,
                lineHeight = 14.sp,
                letterSpacing = 0.5.sp,
                color = Gray20,
            ),
        bodyMedium =
            TextStyle(
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.Normal,
                fontSize = 20.sp,
                lineHeight = 24.sp,
                letterSpacing = 0.5.sp,
                color = Gray20,
            ),
        bodySmall =
            TextStyle(
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                lineHeight = 20.sp,
                letterSpacing = 0.5.sp,
                color = Gray20,
            ),
        labelMedium =
            TextStyle(
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.W700,
                fontSize = 20.sp,
                lineHeight = 24.sp,
                color = Color.White,
            ),
    )
