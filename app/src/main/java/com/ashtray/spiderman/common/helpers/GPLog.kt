package com.ashtray.spiderman.common.helpers

import android.util.Log
import com.ashtray.spiderman.BuildConfig

class GPLog(val tag: String) {

    private val globalTag = "[mg]"

    fun d(message: String?) {
        if (BuildConfig.ENABLE_LOG_PRINTING) {
            Log.d("$globalTag[$tag]", message ?: "")
        }
    }

    fun e(message: String?) {
        if (BuildConfig.ENABLE_LOG_PRINTING) {
            Log.e("$globalTag[$tag]", message ?: "")
        }
    }
}