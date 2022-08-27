package com.ashtray.spiderman.common.helpers

import com.ashtray.spiderman.common.helpers.GPLog.e
import java.lang.Exception

class GPSafeRun(mTag: String, runnable: Runnable) {
    init {
        try {
            runnable.run()
        } catch (e: Exception) {
            e(mTag, "safe run catches an error")
            e.printStackTrace()
        }
    }
}