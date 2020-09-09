package com.aevshvetsov.minimalvkclient.models.appmodels

/**
 * Created by Alexander Shvetsov on 09.09.2020
 */
data class DialogItemModel(
    val avatar: String,
    val lastMessage: String,
    var groupName: String = "",
    val messageTime: String
)