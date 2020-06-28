package com.aevshvetsov.minimalvkclient.adapters

import com.aevshvetsov.minimalvkclient.models.networkmodels.Attachment
import com.aevshvetsov.minimalvkclient.models.networkmodels.Photo
import com.aevshvetsov.minimalvkclient.models.networkmodels.Video
import com.aevshvetsov.minimalvkclient.utils.FeedAttachmentType
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import java.lang.reflect.Type

/**
 * Created by Alexander Shvetsov on 24.06.2020
 */
class AttachmentsTypeAdapter : JsonDeserializer<Attachment> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Attachment {
        val jsonObject = json?.asJsonObject
        val typeName = jsonObject?.get("type")?.asString
        return when {
            typeName.equals(FeedAttachmentType.PHOTO.name.toLowerCase()) -> {
                val photo =
                    context!!.deserialize<Photo>(
                        jsonObject?.get(FeedAttachmentType.PHOTO.name.toLowerCase()),
                        Photo::class.java
                    )
                Attachment(
                    type = typeName!!,
                    value = photo
                )
            }
            typeName.equals(FeedAttachmentType.VIDEO.name.toLowerCase()) -> {
                val video = context!!.deserialize<Video>(
                    jsonObject?.get(FeedAttachmentType.VIDEO.name.toLowerCase()),
                    Video::class.java
                )
                Attachment(
                    type = typeName!!,
                    value = video
                )
            }
            else -> {
                throw JsonParseException("unsupported attachments type")
            }
        }
    }

}