package com.ashtray.spiderman.database

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

data class ScoreEntity(
    @ColumnInfo(name = "score_id")
    @PrimaryKey
    val scoreId: Long,

    @ColumnInfo(name = "game_id")
    val gameId: Long,

    @ColumnInfo(name = "score1")
    val score1: Int,

    @ColumnInfo(name = "score2")
    val score2: Int,

    @ColumnInfo(name = "score3")
    val score3: Int,

    @ColumnInfo(name = "score4")
    val score4: Int,

    @ColumnInfo(name = "score5")
    val score5: Int,

    @ColumnInfo(name = "is_final")
    val isFinal: Boolean
)
