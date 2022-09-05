package com.ashtray.spiderman.features.details

import androidx.lifecycle.ViewModel
import com.ashtray.spiderman.common.app.GPRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GameDetailsViewModel @Inject constructor(
    private val repository: GPRepo
) : ViewModel() {

}