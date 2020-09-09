package com.aevshvetsov.minimalvkclient.models.networkmodels.dialog


import com.google.gson.annotations.SerializedName

data class LastMessage(
    val attachments: List<Any>,
    @SerializedName("conversation_message_id")
    val conversationMessageId: Int,
    val date: Int,
    @SerializedName("from_id")
    val fromId: Int,
    @SerializedName("fwd_messages")
    val fwdMessages: List<Any>,
    val id: Int,
    val important: Boolean,
    @SerializedName("is_hidden")
    val isHidden: Boolean,
    val `out`: Int,
    @SerializedName("peer_id")
    val peerId: Int,
    @SerializedName("random_id")
    val randomId: Int,
    val text: String
)