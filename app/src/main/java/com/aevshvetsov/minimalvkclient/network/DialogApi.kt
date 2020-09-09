package com.aevshvetsov.minimalvkclient.network


import com.aevshvetsov.minimalvkclient.models.networkmodels.dialog.DialogNetworkModel
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Alexander Shvetsov on 24.06.2020
 */
interface DialogApi {
    @GET("messages.getConversations")
    suspend fun getConversations(
        @Query("count") count: Int,
        @Query("filter") filters: String,
        @Query("extended") extended: Boolean,
        @Query("fields") fields: String,
        @Query("v") v: String
    ): DialogNetworkModel
}