package com.ashtray.spiderman.common.helpers

import android.util.Log
import com.ashtray.spiderman.BuildConfig

object GPLog {
    private const val TAG = "[mg]"

    fun d(tag: String, message: String?) {
        if (BuildConfig.ENABLE_LOG_PRINTING) {
            Log.d("$TAG[$tag]", message ?: "")
        }
    }

    fun e(tag: String, message: String?) {
        if (BuildConfig.ENABLE_LOG_PRINTING) {
            Log.e("$TAG[$tag]", message ?: "")
        }
    }
}