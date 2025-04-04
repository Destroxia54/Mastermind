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

@Composable
fun ColorButton(
    color: Color,
    onClick: () -> Unit,
    size: Int = 48,
    showBorder: Boolean = true
) {
    Box(
        modifier = Modifier
            .size(size.dp)
            .background(color = color, shape = CircleShape)
            .then(
                if (showBorder) Modifier.border(2.dp, Color.Black, CircleShape) else Modifier
            )
            .clickable { onClick() }
    )
}