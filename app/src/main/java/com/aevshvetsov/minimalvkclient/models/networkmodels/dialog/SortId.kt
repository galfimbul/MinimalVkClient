package com.aevshvetsov.minimalvkclient.models.networkmodels.dialog


import com.google.gson.annotations.SerializedName

data class SortId(
    @SerializedName("major_id")
    val majorId: Int,
    @SerializedName("minor_id")
    val minorId: Int
)