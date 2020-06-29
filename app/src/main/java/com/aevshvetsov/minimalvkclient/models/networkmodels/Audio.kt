package com.aevshvetsov.minimalvkclient.models.networkmodels


import com.google.gson.annotations.SerializedName

data class Audio(
    @SerializedName("album_id")
    val albumId: Int,
    val artist: String,
    val date: Int,
    val duration: Int,
    @SerializedName("genre_id")
    val genreId: Int,
    val id: Int,
    @SerializedName("owner_id")
    val ownerId: Int,
    val title: String,
    @SerializedName("track_code")
    val trackCode: String,
    val url: String
) : AttachmentType