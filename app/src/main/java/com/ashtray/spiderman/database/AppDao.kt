package com.ashtray.spiderman.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface AppDao {
    /* SELECT QUERIES */
    @Query("SELECT * FROM game_table")
    fun getAllTheGameEntities(): LiveData<List<GameEntity>?>

    @Query("SELECT * FROM game_table WHERE game_id=:gameId LIMIT 1")
    fun getSingleGameWithScores(gameId: Long): LiveData<GameWithScores?>

    /* INSERT QUERIES*/
    @Insert
    fun insertGameEntity(entity: GameEntity)

    @Insert
    fun insertScoreEntity(entity: ScoreEntity)

    /* DELETE QUERIES*/

    /* UPDATE QUERIES*/
}