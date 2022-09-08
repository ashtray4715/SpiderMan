package com.ashtray.spiderman.features.details

import androidx.lifecycle.ViewModel
import com.ashtray.spiderman.common.app.GPRepo
import com.ashtray.spiderman.database.GameEntity
import com.ashtray.spiderman.database.ScoreEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GameDetails3PViewModel @Inject constructor(
    private val repository: GPRepo
) : ViewModel() {
    suspend fun getGameEntity(gameId: Long): GameEntity? {
        return repository.getGameEntity(gameId)
    }

    suspend fun insertNewScore(scoreEntity: ScoreEntity) {
        repository.insertNewScore(scoreEntity)
    }
}