package com.aevshvetsov.minimalvkclient.models.networkmodels.dialog


import com.google.gson.annotations.SerializedName

data class Peer(
    val id: Int,
    @SerializedName("local_id")
    val localId: Int,
    val type: String
)