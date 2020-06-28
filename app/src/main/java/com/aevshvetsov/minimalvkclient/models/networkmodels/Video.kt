package com.aevshvetsov.minimalvkclient.models.networkmodels

data class Video(
    val accessKey: String,
    val canComment: Long,
    val canLike: Long,
    val canRepost: Long,
    val canSubscribe: Long,
    val canAddToFaves: Long,
    val canAdd: Long,
    val comments: Long,
    val date: Long,
    val description: String,
    val duration: Long,
    val image: List<Image>,
    val width: Long,
    val height: Long,
    val id: Long,
    val ownerID: Long,
    val title: String,
    val trackCode: String,
    val type: String,
    val views: Long
) : AttachmentType

data class Image(
    val height: Long,
    val url: String,
    val width: Long,
    val withPadding: Long
)