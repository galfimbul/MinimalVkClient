package com.aevshvetsov.minimalvkclient.adapters

import com.aevshvetsov.minimalvkclient.models.networkmodels.*
import com.aevshvetsov.minimalvkclient.utils.FeedAttachmentType
import com.google.gson.*
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
        return when (val typeName = jsonObject?.get("type")?.asString) {
            FeedAttachmentType.PHOTO.name.toLowerCase() -> {
                /*val photo =
                    context!!.deserialize<Photo>(
                        jsonObject?.get(FeedAttachmentType.PHOTO.name.toLowerCase()),
                        Photo::class.java
                    )
                Attachment(
                    type = typeName,
                    value = photo
                )*/
                getValue(context, typeName, jsonObject, Photo::class.java)
            }
            FeedAttachmentType.VIDEO.name.toLowerCase() -> {
                /*val video = context!!.deserialize<Video>(
                    jsonObject.get(FeedAttachmentType.VIDEO.name.toLowerCase()),
                    Video::class.java
                )
                Attachment(
                    type = typeName,
                    value = video
                )*/
                getValue(context, typeName, jsonObject, Video::class.java)
            }
            FeedAttachmentType.AUDIO.name.toLowerCase() -> {
                /*val audio = context!!.deserialize<Video>(
                    jsonObject.get(FeedAttachmentType.VIDEO.name.toLowerCase()),
                    Audio::class.java
                )
                Attachment(
                    type = typeName,
                    value = audio
                )*/
                getValue(context, typeName, jsonObject, Audio::class.java)
            }
            else -> {
                Attachment("unsupported", UnsupportedAttachmentType())
            }
        }
    }

    fun <T> getValue(
        context: JsonDeserializationContext?,
        typeName: String,
        jsonObject: JsonObject,
        classT: Class<T>
    ): Attachment {
        val value = context!!.deserialize<T>(
            jsonObject.get(typeName),
            classT
        )
        if (value is AttachmentType)
            return Attachment(
                type = typeName,
                value = value as AttachmentType
            )
        else throw JsonParseException("unsupported attachments type")
    }

}