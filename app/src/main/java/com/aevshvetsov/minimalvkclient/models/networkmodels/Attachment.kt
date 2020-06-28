package com.aevshvetsov.minimalvkclient.models.networkmodels

import com.google.gson.annotations.SerializedName

data class Attachment(
    val type: String,
    @SerializedName(value = "photo", alternate = ["audio", "video"])
    val value: AttachmentType
)

interface AttachmentType





