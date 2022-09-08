package com.ashtray.spiderman.features.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.ashtray.spiderman.common.app.GPRepo
import com.ashtray.spiderman.database.GameWithScores
import com.ashtray.spiderman.database.ScoreEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GameDetails3PViewModel @Inject constructor(
    private val repository: GPRepo
) : ViewModel() {

    fun getSingleGameWithScores(gameId: Long): LiveData<GameWithScores?> {
        return repository.getSingleGameWithScores(gameId)
    }

    suspend fun insertScoreEntity(scoreEntity: ScoreEntity) {
        repository.insertScoreEntity(scoreEntity)
    }
}