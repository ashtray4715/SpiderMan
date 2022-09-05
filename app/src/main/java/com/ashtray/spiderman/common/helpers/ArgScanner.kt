package com.ashtray.spiderman.common.helpers

import android.os.Bundle

class ArgumentScanner(private val arguments: Bundle?) {
    fun isFromSplashFragment(): Boolean {
        return arguments?.getBoolean(
            GPConst.PK_IS_FROM_SPLASH_FRAGMENT, false
        ) ?: false
    }
}