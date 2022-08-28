package com.ashtray.spiderman.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface AppDao {
    /* SELECT QUERIES */
    @Query("SELECT * FROM game_table")
    fun getAllTheGameEntities(): LiveData<List<GameEntity>>

    /* INSERT QUERIES*/
    @Insert
    fun insertGameEntity(entity: GameEntity)

    /* DELETE QUERIES*/

    /* UPDATE QUERIES*/
}