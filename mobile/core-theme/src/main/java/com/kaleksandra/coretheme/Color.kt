package com.kaleksandra.coretheme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

private val primary = Color(0xFFAE95CC)
private val textSecondary = Color(0xFF727272)
private val lightPrimaryContainer = Color(0xFFF8F8F8)
private val darkBackground = Color(0xFF0C0316)
private val darkOnPrimary = Color(0x99201032)
private val darkPrimaryContainer = Color(0xFFAE95CC)
private val darkOnSecondary = Color(0xFF2D203D)

internal val DarkColors = darkColorScheme(
    primary = primary,
    background = darkBackground,
    onPrimary = darkOnPrimary,
    onSecondary = darkOnSecondary,
    tertiary = textSecondary,
    onSecondaryContainer = darkPrimaryContainer
    //TODO: Add dark colors theme
)

internal val LightColors = lightColorScheme(
    primary = primary,
    onPrimary = darkOnPrimary,
    tertiary = textSecondary,
    onSecondaryContainer = lightPrimaryContainer
    //TODO: Add light colors theme
)