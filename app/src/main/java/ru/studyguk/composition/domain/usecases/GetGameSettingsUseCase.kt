package ru.studyguk.composition.domain.usecases

import ru.studyguk.composition.domain.entity.GameSettings
import ru.studyguk.composition.domain.entity.Level
import ru.studyguk.composition.domain.repository.GameRepository

class GetGameSettingsUseCase(private val repository: GameRepository) {

    operator fun invoke(level: Level): GameSettings {
        return repository.getGameSettings(level)
    }
}