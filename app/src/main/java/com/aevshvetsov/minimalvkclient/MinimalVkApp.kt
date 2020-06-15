package com.aevshvetsov.minimalvkclient

import android.app.Application
import android.content.Context

/**
 * Created by Alexander Shvetsov on 07.06.2020
 */
class MinimalVkApp : Application() {
    companion object {
        private var instance: MinimalVkApp? = null
        fun applicationContext(): Context {
            return instance!!.applicationContext
        }
    }

    init {
        instance = this
    }
    override fun onCreate() {
        super.onCreate()
    }

}