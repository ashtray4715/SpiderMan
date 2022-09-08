package com.ashtray.spiderman.database

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

data class ScoreEntity(
    @ColumnInfo(name = "score_id")
    @PrimaryKey(autoGenerate = true)
    val scoreId: Long = 0,

    @ColumnInfo(name = "game_id")
    val gameId: Long,

    @ColumnInfo(name = "score1")
    val score1: Int = 0,

    @ColumnInfo(name = "score2")
    val score2: Int = 0,

    @ColumnInfo(name = "score3")
    val score3: Int = 0,

    @ColumnInfo(name = "score4")
    val score4: Int = 0,

    @ColumnInfo(name = "score5")
    val score5: Int = 0,

    @ColumnInfo(name = "is_final")
    val isFinal: Boolean
)
