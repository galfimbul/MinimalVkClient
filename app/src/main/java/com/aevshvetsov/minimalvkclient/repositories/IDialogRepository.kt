package com.aevshvetsov.minimalvkclient.repositories

import com.aevshvetsov.minimalvkclient.models.networkmodels.dialog.DialogNetworkModel

/**
 * Created by Alexander Shvetsov on 25.06.2020
 */
interface IDialogRepository {
    suspend fun getDialogs(): DialogNetworkModel
}