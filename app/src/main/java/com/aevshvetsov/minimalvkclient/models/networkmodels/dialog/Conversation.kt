package com.aevshvetsov.minimalvkclient.models.networkmodels.dialog


import com.google.gson.annotations.SerializedName

data class Conversation(
    @SerializedName("can_write")
    val canWrite: CanWrite,
    val important: Boolean,
    @SerializedName("in_read")
    val inRead: Int,
    @SerializedName("is_marked_unread")
    val isMarkedUnread: Boolean,
    @SerializedName("last_message_id")
    val lastMessageId: Int,
    @SerializedName("out_read")
    val outRead: Int,
    val peer: Peer,
    @SerializedName("sort_id")
    val sortId: SortId
)