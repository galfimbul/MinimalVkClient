package com.aevshvetsov.minimalvkclient

import android.app.Application
import android.content.Context
import com.vk.api.sdk.VK
import com.vk.api.sdk.VKTokenExpiredHandler

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

    private val tokenTracker = object : VKTokenExpiredHandler {
        override fun onTokenExpired() {
        }
    }
    override fun onCreate() {
        super.onCreate()
        VK.addTokenExpiredHandler(tokenTracker)


    }

}