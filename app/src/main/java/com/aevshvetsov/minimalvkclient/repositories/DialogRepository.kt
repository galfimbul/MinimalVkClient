package com.aevshvetsov.minimalvkclient.repositories

import com.aevshvetsov.minimalvkclient.models.networkmodels.dialog.DialogNetworkModel
import com.aevshvetsov.minimalvkclient.network.DialogApi

/**
 * Created by Alexander Shvetsov on 07.06.2020
 */
class DialogRepository(private val dialogApi: DialogApi) : IDialogRepository {
    override suspend fun getDialogs(): DialogNetworkModel {
        return dialogApi.getConversations(
            count = 2,
            filters = "all",
            extended = true,
            fields = "id,photo_50,first_name,lats_name",
            v = "5.122"
        )
    }


}