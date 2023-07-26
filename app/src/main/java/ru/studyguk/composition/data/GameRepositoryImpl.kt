package ru.studyguk.composition.data

import ru.studyguk.composition.domain.entity.GameSettings
import ru.studyguk.composition.domain.entity.Level
import ru.studyguk.composition.domain.entity.Question
import ru.studyguk.composition.domain.repository.GameRepository
import kotlin.random.Random
import kotlin.math.max
import kotlin.math.min

object GameRepositoryImpl : GameRepository {

    private const val MIN_SUM_VALUE = 2
    private const val MIN_ANSWER_VALUE = 1

    override fun generateQuestion(maxSumValue: Int, countOfOptions: Int): Question {
        val sum = Random.nextInt(MIN_SUM_VALUE, maxSumValue + 1)
        val visibleNumber = Random.nextInt(MIN_ANSWER_VALUE, sum)
        val rightAnswer = sum - visibleNumber
        val options = HashSet<Int>()
        options.add(rightAnswer)
        val from = max(rightAnswer - countOfOptions, MIN_ANSWER_VALUE)
        val to = min(rightAnswer + countOfOptions, maxSumValue)
        while (options.size < countOfOptions) {
            options.add(Random.nextInt(from, to))
        }
        return Question(sum, visibleNumber, options.toList())
    }

    override fun getGameSettings(level: Level): GameSettings {
        return when(level) {
            Level.TEST -> GameSettings(
                10,
                3,
                50,
                12
            )
            Level.EASY -> GameSettings(
                10,
                10,
                70,
                60
            )
            Level.NORMAL -> GameSettings(
                20,
                20,
                80,
                40
            )
            Level.HARD -> GameSettings(
                30,
                30,
                90,
                35
            )
        }
    }
}