package com.ashtray.spiderman.features.splash

import androidx.lifecycle.ViewModel
import com.ashtray.spiderman.common.app.GPRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    val repository: GPRepo
): ViewModel()