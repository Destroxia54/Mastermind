package com.example.mastermindgame.model

data class GuessResult(
    val guess: List<ColorPeg>,
    val blackPegs: Int,
    val whitePegs: Int
)