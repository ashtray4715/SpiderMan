package com.ashtray.spiderman.features.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.ashtray.spiderman.common.app.GPRepo
import com.ashtray.spiderman.database.GameEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    val repository: GPRepo
): ViewModel() {
    suspend fun insertNewGame(entity: GameEntity) {
        repository.insertNewGame(entity)
    }

    fun getAllTheGameEntities(): LiveData<List<GameEntity>> {
        return repository.getAllTheGameEntities()
    }
}