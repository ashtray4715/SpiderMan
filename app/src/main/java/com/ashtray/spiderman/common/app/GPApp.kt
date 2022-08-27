package com.ashtray.spiderman.common.app

import android.app.Application
import android.content.Context

class GPApp : Application() {

    companion object {
        private lateinit var instance: GPApp
        private lateinit var repository: GPRepo
        private lateinit var factory: GPFactory

        fun getContext(): Context = instance

        fun getRepository(): GPRepo = repository

        fun getFactory(): GPFactory = factory
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        repository = GPRepo()
        factory = GPFactory()
    }
}