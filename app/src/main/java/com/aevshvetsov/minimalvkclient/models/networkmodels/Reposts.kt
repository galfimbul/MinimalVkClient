package com.aevshvetsov.minimalvkclient.models.networkmodels


import com.google.gson.annotations.SerializedName

data class Reposts(
    val count: Int,
    @SerializedName("user_reposted")
    val userReposted: Int
)