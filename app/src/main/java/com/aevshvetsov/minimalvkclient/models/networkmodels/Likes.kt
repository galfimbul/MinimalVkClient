package com.aevshvetsov.minimalvkclient.models.networkmodels


import com.google.gson.annotations.SerializedName

data class Likes(
    @SerializedName("can_like")
    val canLike: Int,
    @SerializedName("can_publish")
    val canPublish: Int,
    val count: Int,
    @SerializedName("user_likes")
    val userLikes: Int
)