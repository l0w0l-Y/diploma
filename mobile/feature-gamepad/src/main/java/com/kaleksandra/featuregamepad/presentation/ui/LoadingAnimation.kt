package com.kaleksandra.featuregamepad.presentation.ui

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.kaleksandra.featuregamepad.R

@Composable
fun LoadingAnimation(modifier: Modifier = Modifier, speed: Int = 800, size: Dp = 40.dp) {
    val infiniteTransition = rememberInfiniteTransition("RotationTransition")
    val rotation by infiniteTransition.animateFloat(
        initialValue = -20f, targetValue = 20f, animationSpec = infiniteRepeatable(
            animation = tween(speed),
            repeatMode = RepeatMode.Reverse,
        ), label = "RotationCompletion"
    )
    Box(
        modifier = modifier, contentAlignment = Alignment.Center
    ) {
        Icon(
            modifier = Modifier
                .graphicsLayer {
                    rotationZ = rotation
                }
                .size(size),
            painter = painterResource(id = R.drawable.ic_loading),
            tint = MaterialTheme.colorScheme.primary,
            contentDescription = "",
        )
    }
}