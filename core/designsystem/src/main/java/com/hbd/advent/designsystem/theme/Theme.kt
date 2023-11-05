package com.hbd.advent.designsystem.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf

val LocalCustomColors = staticCompositionLocalOf { customColors }
val LocalCustomTypography = staticCompositionLocalOf { customTypo }

@Composable
fun AdventTheme(
    colors: Colors = AdventTheme.colors,
    typo: Typo = AdventTheme.typography,
    content: @Composable () -> Unit
){
    CompositionLocalProvider(
        LocalCustomColors provides colors,
        LocalCustomTypography provides typo,
        content = content
    )
}

object AdventTheme {
    val colors: Colors
        @Composable
        get() = LocalCustomColors.current
    val typography: Typo
        @Composable
        get() = LocalCustomTypography.current
}