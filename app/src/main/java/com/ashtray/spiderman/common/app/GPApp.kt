package com.ashtray.spiderman.common.app

import android.app.Application
import android.content.Context
import com.ashtray.spiderman.common.helpers.GPConst

class GPApp : Application() {

    companion object {
        private lateinit var instance: GPApp
        private lateinit var sharedPref: GPSharedPref
        private lateinit var repository: GPRepo
        private lateinit var factory: GPFactory

        fun getContext(): Context = instance

        fun getSharedPref(): GPSharedPref = sharedPref

        fun getRepository(): GPRepo = repository

        fun getFactory(): GPFactory = factory
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        sharedPref = GPSharedPref(getSharedPreferences(
            GPConst.SHARED_PREF_FILE_NAME,
            MODE_PRIVATE
        ))
        repository = GPRepo()
        factory = GPFactory()
    }
}