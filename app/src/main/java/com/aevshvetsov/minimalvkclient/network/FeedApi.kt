package com.aevshvetsov.minimalvkclient.network


import com.aevshvetsov.minimalvkclient.models.networkmodels.NetworkModels
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Alexander Shvetsov on 24.06.2020
 */
interface FeedApi {
    @GET("newsfeed.get")
    suspend fun getFeed(
        @Query("filters") filters: String,
        @Query("count") count: String,
        @Query("v") v: String
    ): NetworkModels
}