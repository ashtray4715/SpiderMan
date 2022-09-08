package com.ashtray.spiderman.common.app

import androidx.lifecycle.LiveData
import com.ashtray.spiderman.database.AppDao
import com.ashtray.spiderman.database.GameEntity
import com.ashtray.spiderman.database.GameWithScores
import com.ashtray.spiderman.database.ScoreEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GPRepo @Inject constructor(val dao: AppDao) {

    suspend fun insertNewGame(entity: GameEntity) {
        withContext(Dispatchers.IO) {
            dao.insertGameEntity(entity)
        }
    }

    suspend fun insertScoreEntity(scoreEntity: ScoreEntity) {
        withContext(Dispatchers.IO) {
            dao.insertScoreEntity(scoreEntity)
        }
    }

    fun getAllTheGameEntities(): LiveData<List<GameEntity>?> {
        return dao.getAllTheGameEntities()
    }

    fun getSingleGameWithScores(gameId: Long): LiveData<GameWithScores?> {
        return dao.getSingleGameWithScores(gameId)
    }

}