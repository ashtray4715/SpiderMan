package com.ashtray.spiderman.database

import androidx.room.Embedded
import androidx.room.Relation

data class GameWithScores(
    @Embedded
    val gameEntity: GameEntity,

    @Relation(
        parentColumn = "game_id",
        entityColumn = "game_id"
    )
    val scoreEntities: List<ScoreEntity>
)