package com.kaleksandra.featuregamepad.presentation.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun RepeatingButton(
    onPressed: () -> Unit,
    onReleased: () -> Unit,
    content: @Composable () -> Unit,
) {

    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    var isFirstPress by remember { mutableStateOf(true) }

    if (isPressed) {
        if (isFirstPress) {
            isFirstPress = false
            onPressed()
        }

        DisposableEffect(Unit) {
            onDispose {
                onReleased()
            }
        }
    }

    OutlinedButton(
        onClick = { isFirstPress = true },
        interactionSource = interactionSource,
        modifier = Modifier.size(60.dp),
        colors = ButtonDefaults
            .outlinedButtonColors(
                containerColor = MaterialTheme.colorScheme.onPrimary
            )
    ) {
        content()
    }
}

@Composable
fun RepeatingButton(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.onPrimary,
    onPressed: () -> Unit,
    onReleased: () -> Unit,
    content: @Composable () -> Unit,
) {

    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    var isFirstPress by remember { mutableStateOf(true) }

    if (isPressed) {
        if (isFirstPress) {
            isFirstPress = false
            onPressed()
        }

        DisposableEffect(Unit) {
            onDispose {
                onReleased()
            }
        }
    }

    Button(
        onClick = { isFirstPress = true },
        interactionSource = interactionSource,
        modifier = modifier,
        shape = CircleShape,
        colors = ButtonDefaults
            .outlinedButtonColors(
                containerColor = color,
            ),
        border = BorderStroke(2.dp, MaterialTheme.colorScheme.onSecondaryContainer)
    ) {
        content()
    }
}

@Composable
fun RepeatingIconButton(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.onPrimary,
    onPressed: () -> Unit,
    onReleased: () -> Unit,
    content: @Composable () -> Unit,
) {

    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    var isFirstPress by remember { mutableStateOf(true) }

    if (isPressed) {
        if (isFirstPress) {
            isFirstPress = false
            onPressed()
        }

        DisposableEffect(Unit) {
            onDispose {
                onReleased()
            }
        }
    }

    OutlinedIconButton(
        onClick = { isFirstPress = true },
        interactionSource = interactionSource,
        colors = IconButtonDefaults
            .outlinedIconButtonColors(
                containerColor = color,
            ),
        modifier = modifier,
        border = BorderStroke(2.dp, MaterialTheme.colorScheme.onSecondaryContainer)
    ) {
        content()
    }
}