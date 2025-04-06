package com.example.mastermindgame.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.ui.input.pointer.changedToUp
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.pointerInteropFilter

@Composable
fun ColorButton(
    color: Color,
    onClick: () -> Unit,
    showBorder: Boolean = true,
    size: Int = 40
) {
    var pressed by remember { mutableStateOf(false) }

    val scale by animateFloatAsState(
        targetValue = if (pressed) 0.9f else 1f,
        animationSpec = tween(durationMillis = 100),
        label = "ColorButtonScale"
    )

    val gradient = Brush.radialGradient(
        colors = listOf(
            color.copy(alpha = 0.95f),
            color.copy(alpha = 0.6f),
            Color.Black.copy(alpha = 0.85f)
        ),
        center = Offset(10f, 10f),
        radius = 120f
    )

    Box(
        modifier = Modifier
            .size(size.dp)
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
                shadowElevation = 8f
                shape = CircleShape
                clip = true
            }
            .background(gradient)
            .then(
                if (showBorder) Modifier.border(2.dp, Color.Black, CircleShape)
                else Modifier
            )
            .pointerInput(Unit) {
                awaitPointerEventScope {
                    while (true) {
                        val event = awaitPointerEvent()
                        pressed = event.changes.any { it.pressed }
                        if (!pressed && event.changes.all { it.changedToUp() }) {
                            onClick()
                        }
                    }
                }
            }
    )
}

