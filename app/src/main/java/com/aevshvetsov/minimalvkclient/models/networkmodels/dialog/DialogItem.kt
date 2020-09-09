package com.aevshvetsov.minimalvkclient.models.networkmodels.dialog


import com.google.gson.annotations.SerializedName

data class DialogItem(
    val conversation: Conversation,
    @SerializedName("last_message")
    val lastMessage: LastMessage
)