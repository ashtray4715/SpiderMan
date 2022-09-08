package com.ashtray.spiderman.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [GameEntity::class, ScoreEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun appDao(): AppDao
}