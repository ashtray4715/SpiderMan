package com.ashtray.spiderman.common.helpers

import android.os.Bundle

class ArgScanner(private val arguments: Bundle?) {
    fun isFromSplashFragment(): Boolean {
        return arguments?.getBoolean(
            GPConst.PK_IS_FROM_SPLASH_FRAGMENT, false
        ) ?: false
    }

    fun getGameId(): Long {
        return arguments?.getLong(
            GPConst.PK_SELECTED_GAME_ID, -1L
        ) ?: -1L
    }
}