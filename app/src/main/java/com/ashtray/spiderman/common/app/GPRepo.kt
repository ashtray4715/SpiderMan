package com.ashtray.spiderman.common.app

import androidx.lifecycle.LiveData
import com.ashtray.spiderman.database.AppDao
import com.ashtray.spiderman.database.GameEntity
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

    suspend fun insertNewScore(scoreEntity: ScoreEntity) {
        withContext(Dispatchers.IO) {
            //dao.insertNewScore(scoreEntity)
        }
    }

    fun getAllTheGameEntities(): LiveData<List<GameEntity>?> {
        return dao.getAllTheGameEntities()
    }

    suspend fun getGameEntity(gameId: Long): GameEntity? {
        return withContext(Dispatchers.IO) {
            dao.getGameEntity(gameId)
        }
    }
}