package com.example.mastermindgame.model

import androidx.compose.ui.graphics.Color

enum class ColorPeg(val displayName: String, val color: Color) {
    RED("Red", Color.Red),
    GREEN("Green", Color.Green),
    BLUE("Blue", Color.Blue),
    YELLOW("Yellow", Color.Yellow),
    ORANGE("Orange", Color(0xFFFFA500)), // not built-in
    PURPLE("Purple", Color(0xFF800080))  // not built-in
}
