package com.ashtray.spiderman.common.helpers

import java.lang.Exception

class GPSafeRun(log: GPLog, runnable: Runnable) {
    init {
        try {
            runnable.run()
        } catch (e: Exception) {
            log.e("safe run catches an error")
            e.printStackTrace()
        }
    }
}