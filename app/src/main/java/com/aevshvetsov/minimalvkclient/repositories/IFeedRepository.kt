package com.aevshvetsov.minimalvkclient.repositories

/**
 * Created by Alexander Shvetsov on 25.06.2020
 */
interface IFeedRepository {
    suspend fun getFeed()
}