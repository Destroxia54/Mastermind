package com.example.mastermindgame.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

enum class PegType { BLACK, WHITE, EMPTY }

@Composable
fun HintPeg(type: PegType) {
    val color = when (type) {
        PegType.BLACK -> Color.Black
        PegType.WHITE -> Color.White
        PegType.EMPTY -> Color.LightGray
    }

    Box(
        modifier = Modifier
            .size(12.dp)
            .background(color = color, shape = RoundedCornerShape(2.dp))
            .border(1.dp, Color.DarkGray, shape = RoundedCornerShape(2.dp))
    )
}