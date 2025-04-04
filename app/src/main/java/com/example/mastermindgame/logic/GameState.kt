package com.example.mastermindgame.logic

import com.example.mastermindgame.model.ColorPeg
import com.example.mastermindgame.model.GuessResult

object GameState {
    var secretCode: List<ColorPeg> = emptyList()
    var pin: String = ""
    var autoHintEnabled: Boolean = false // default to manual hints
    var guessesLeft: Int = 12
    var codebreakerGuesses: List<GuessResult> = listOf()

    fun reset() {
        secretCode = emptyList()
        pin = ""
        autoHintEnabled = false
        guessesLeft = 12
    }
}
