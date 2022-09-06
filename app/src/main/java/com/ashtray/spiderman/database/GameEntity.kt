package com.ashtray.spiderman.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "game_table")
data class GameEntity(
    @ColumnInfo(name = "game_id")
    @PrimaryKey
    val gameId: Long,

    @ColumnInfo(name = "game_name")
    val gameName: String,

    @ColumnInfo(name = "total_player")
    val totalPlayer: Int,

    @ColumnInfo(name = "player_name1")
    val playerName1: String,

    @ColumnInfo(name = "player_name2")
    val playerName2: String,

    @ColumnInfo(name = "player_name3")
    val playerName3: String,

    @ColumnInfo(name = "player_name4")
    val playerName4: String = "",

    @ColumnInfo(name = "player_name5")
    val playerName5: String = "",

    @ColumnInfo(name = "target_score")
    val targetScore: Int,

    @ColumnInfo(name = "notify_target_score")
    val notifyOnTargetReached: Boolean
)