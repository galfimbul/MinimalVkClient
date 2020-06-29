package com.aevshvetsov.minimalvkclient.models.networkmodels

/**
 * Created by Alexander Shvetsov on 29.06.2020
 */
class UnsupportedAttachmentType(
    val id: Long = 100,
    val text: String = "Current Attachment is Unsupported in app current version",
    val userID: Long = 100
) : AttachmentType
