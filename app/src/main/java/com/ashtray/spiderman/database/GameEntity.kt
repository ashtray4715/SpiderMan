package com.ashtray.spiderman.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "game_table")
data class GameEntity(

    @PrimaryKey
    val gameId: Long,
    val gameName: String,
    val totalPlayer: Int,
    val playerName1: String,
    val playerName2: String,
    val playerName3: String,
    val playerName4: String = "",
    val playerName5: String = "",
    val playerName6: String = "",
    val targetScore: Int,
    val notifyOnTargetReached: Boolean
)