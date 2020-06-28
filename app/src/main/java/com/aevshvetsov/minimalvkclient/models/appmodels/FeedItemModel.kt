package com.aevshvetsov.minimalvkclient.models.appmodels

import com.aevshvetsov.minimalvkclient.utils.Constants

/**
 * Created by Alexander Shvetsov on 27.06.2020
 */
data class FeedItemModel(
    val groupPhotoUrl: String = Constants.FAKE_IMAGE_URL,
    val groupName: String = "Test Name",
    val postTime: String = "NOW",
    val text: String = "TEST TEXT",
    val attachmentUrl: String = Constants.FAKE_IMAGE_URL,
    val isLiked: Boolean = false,
    val views: Int = 10
)