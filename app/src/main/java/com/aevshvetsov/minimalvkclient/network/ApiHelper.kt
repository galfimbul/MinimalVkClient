package com.aevshvetsov.minimalvkclient.network

/**
 * Created by Alexander Shvetsov on 24.06.2020
 */
class ApiHelper(val feedApi: FeedApi) {
    suspend fun getFeed() = feedApi.getFeed(filters = "post", count = "30", v = "5.110")
}