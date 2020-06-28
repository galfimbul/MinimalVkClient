package com.aevshvetsov.minimalvkclient.repositories

import com.aevshvetsov.minimalvkclient.network.ApiHelper

/**
 * Created by Alexander Shvetsov on 07.06.2020
 */
class FeedRepository(private val feedApi: ApiHelper) {

    suspend fun getFeed() = feedApi.getFeed()
}