package com.example.mastermindgame.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HintGrid(
    blackCount: Int,
    whiteCount: Int
) {
    val pegs = mutableListOf<PegType>().apply {
        repeat(blackCount) { add(PegType.BLACK) }
        repeat(whiteCount) { add(PegType.WHITE) }
        while (size < 4) { add(PegType.EMPTY) }
    }

    Column(
        verticalArrangement = Arrangement.spacedBy(2.dp),
        modifier = Modifier.padding(start = 8.dp)
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(2.dp)) {
            HintPeg(pegs[0])
            HintPeg(pegs[1])
        }
        Row(horizontalArrangement = Arrangement.spacedBy(2.dp)) {
            HintPeg(pegs[2])
            HintPeg(pegs[3])
        }
    }
}