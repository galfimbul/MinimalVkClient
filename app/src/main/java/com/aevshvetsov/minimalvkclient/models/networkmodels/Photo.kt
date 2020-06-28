package com.aevshvetsov.minimalvkclient.models.networkmodels

data class Photo(
    val albumID: Long,
    val date: Long,
    val id: Long,
    val ownerID: Long,
    val hasTags: Boolean,
    val accessKey: String,
    val postID: Long,
    val sizes: List<Size>,
    val text: String,
    val userID: Long
) : AttachmentType

data class Size(
    val height: Long,
    val url: String,
    val type: String,
    val width: Long
)