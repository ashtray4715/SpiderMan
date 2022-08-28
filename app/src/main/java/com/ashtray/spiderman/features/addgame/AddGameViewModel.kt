package com.ashtray.spiderman.features.addgame

import androidx.lifecycle.ViewModel
import com.ashtray.spiderman.common.app.GPRepo
import com.ashtray.spiderman.database.GameEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddGameViewModel @Inject constructor(
    private val repository: GPRepo
) : ViewModel() {
    suspend fun insertNewGame(entity: GameEntity) {
        repository.insertNewGame(entity)
    }
}