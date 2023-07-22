package ru.studyguk.composition.domain.repository

import ru.studyguk.composition.domain.entity.GameSettings
import ru.studyguk.composition.domain.entity.Level
import ru.studyguk.composition.domain.entity.Question

interface GameRepository {

    fun generateQuestion(maxSumValue: Int, countOfOptions: Int): Question

    fun getGameSettings(level: Level): GameSettings
}