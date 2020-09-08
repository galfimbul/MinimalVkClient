package com.aevshvetsov.minimalvkclient.models.appmodels

import com.aevshvetsov.minimalvkclient.models.networkmodels.Attachment
import com.aevshvetsov.minimalvkclient.utils.Constants
import com.aevshvetsov.minimalvkclient.utils.FeedViewsType

/**
 * Created by Alexander Shvetsov on 27.06.2020
 */
data class FeedItemModel(
    val groupPhotoUrl: String = Constants.FAKE_IMAGE_URL,
    val groupName: String = "Test Name",
    val postTime: String = "NOW",
    val text: String = "TEST TEXT",
    var attachmentUrl: String = Constants.FAKE_IMAGE_URL,
    var attachments: List<Attachment> = emptyList(),
    val isLiked: Boolean = false,
    val views: Int = 10,
    val canComment: Int = 1,
    val commentsCount: Int = 10,
    val likes_count: Int = 10,
    var attachmentViewType: Int = FeedViewsType.WITH_PHOTO.type
)