package com.aevshvetsov.minimalvkclient.models.networkmodels.dialog


import com.google.gson.annotations.SerializedName

data class DialogGroup(
    val id: Int,
    @SerializedName("is_admin")
    val isAdmin: Int,
    @SerializedName("is_advertiser")
    val isAdvertiser: Int,
    @SerializedName("is_closed")
    val isClosed: Int,
    @SerializedName("is_member")
    val isMember: Int,
    val name: String,
    @SerializedName("photo_50")
    val photo50: String,
    @SerializedName("screen_name")
    val screenName: String,
    val type: String
)