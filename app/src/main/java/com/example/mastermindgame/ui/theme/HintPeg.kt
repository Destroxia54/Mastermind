package com.example.mastermindgame.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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


enum class PegType { BLACK, WHITE, EMPTY }

@Composable
fun HintPeg(type: PegType) {
    val brush = when (type) {
        PegType.BLACK -> Brush.radialGradient(
            colors = listOf(Color(0xFF444444), Color.Black),
            radius = 16f
        )
        PegType.WHITE -> Brush.radialGradient(
            colors = listOf(Color.White, Color(0xFFAAAAAA)),
            radius = 16f
        )
        PegType.EMPTY -> Brush.radialGradient(
            colors = listOf(Color.LightGray, Color.DarkGray),
            radius = 16f
        )
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(12.dp)
            .background(brush = brush, shape = RoundedCornerShape(2.dp))
            .border(1.dp, Color.DarkGray, shape = RoundedCornerShape(2.dp))
    ) {
        if (type == PegType.EMPTY) {
            Text(
                text = "X",
                style = MaterialTheme.typography.labelSmall,
                color = Color.DarkGray
            )
        }
    }
}

