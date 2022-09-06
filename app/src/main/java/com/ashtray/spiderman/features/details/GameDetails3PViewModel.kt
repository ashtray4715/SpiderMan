package com.ashtray.spiderman.features.details

import androidx.lifecycle.ViewModel
import com.ashtray.spiderman.common.app.GPRepo
import com.ashtray.spiderman.database.GameEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class GameDetails3PViewModel @Inject constructor(
    private val repository: GPRepo
) : ViewModel() {
    suspend fun getGameEntity(gameId: Long): GameEntity? {
        return repository.getGameEntity(gameId)
    }
}