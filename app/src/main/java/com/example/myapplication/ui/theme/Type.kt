package com.example.myapplication.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.myapplication.R

// Set of Material typography styles to start with
object Type {
    fun text2xsMedium() = TextStyle(
        fontFamily = FontFamily(Font(R.font.poppins_medium)),
        fontSize = 10.sp,
        lineHeight = 16.sp
    )
    fun text2xsSemiBold() = TextStyle(
        fontFamily = FontFamily(Font(R.font.poppins_semi_bold)),
        fontSize = 12.sp,
        lineHeight = 20.sp
    )

    fun displayXsSemiBold()= TextStyle(
        fontFamily = FontFamily(Font(R.font.poppins_semi_bold)),
        fontSize = 24.sp,
        lineHeight = 32.sp
    )
    fun displayXsSemiBold2()= TextStyle(
        fontFamily = FontFamily(Font(R.font.poppins_semi_bold)),
        fontSize = 30.sp,
        lineHeight = 32.sp
    )

    fun textXlBold()= TextStyle(
        fontFamily = FontFamily(Font(R.font.poppins_bold)),
        fontSize = 11.sp,
        lineHeight = 20.sp
    )

    fun textSmMedium()= TextStyle(
        fontFamily = FontFamily(Font(R.font.poppins_medium)),
        fontSize = 14.sp,
        lineHeight = 20.sp
    )

    fun text2xsRegular() = TextStyle(
        fontFamily = FontFamily(Font(R.font.poppins_regular)),
        fontSize = 10.sp,
        lineHeight = 18.sp
    )
    }
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
