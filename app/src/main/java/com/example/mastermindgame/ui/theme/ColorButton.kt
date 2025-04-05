package com.example.mastermindgame.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.border
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp


@Composable
fun ColorButton(
    color: Color,
    onClick: () -> Unit,
    showBorder: Boolean = true,
    size: Int = 40 // 👈 default peg size
) {
    val gradient = Brush.radialGradient(
        colors = listOf(
            color.copy(alpha = 0.9f),
            color.copy(alpha = 0.6f),
            Color.Black.copy(alpha = 0.8f)
        ),
        center = Offset(10f, 10f),
        radius = 120f
    )

    Box(
        modifier = Modifier
            .size(size.dp) // 👈 now uses passed-in size
            .clip(CircleShape)
            .background(gradient)
            .then(
                if (showBorder) Modifier.border(2.dp, Color.Black, CircleShape)
                else Modifier
            )
            .clickable { onClick() }
    )
}
