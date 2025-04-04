package com.example.mastermindgame.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mastermindgame.model.ColorPeg
import androidx.compose.ui.graphics.Color
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import com.example.mastermindgame.ui.theme.HintGrid
import com.example.mastermindgame.ui.theme.PegType


@Composable
fun GuessRow(
    guess: List<ColorPeg>,
    black: Int,
    white: Int,
    highlight: Boolean = false
) {
    AnimatedVisibility(
        visible = true,
        enter = fadeIn(animationSpec = tween(300)) + slideInVertically(initialOffsetY = { it / 4 }),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = if (highlight) 8.dp else 4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                guess.forEach { peg ->
                    ColorButton(
                        color = peg.color,
                        onClick = {},
                        showBorder = highlight,
                        size = 32
                    )
                }
            }

            HintGrid(blackCount = black, whiteCount = white)
        }
    }
}