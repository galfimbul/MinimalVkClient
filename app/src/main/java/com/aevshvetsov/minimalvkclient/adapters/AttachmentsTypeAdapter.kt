package com.aevshvetsov.minimalvkclient.adapters

import com.aevshvetsov.minimalvkclient.models.networkmodels.*
import com.aevshvetsov.minimalvkclient.utils.FeedAttachmentType
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonObject
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
                getValue<Photo>(context, typeName, jsonObject)
            }
            FeedAttachmentType.VIDEO.name.toLowerCase() -> {
                getValue<Video>(context, typeName, jsonObject)
            }
            FeedAttachmentType.AUDIO.name.toLowerCase() -> {
                getValue<Audio>(context, typeName, jsonObject)
            }
            else -> {
                Attachment("unsupported", UnsupportedAttachmentType())
            }
        }
    }

    inline fun <reified T : AttachmentType> getValue(
        context: JsonDeserializationContext?,
        typeName: String,
        jsonObject: JsonObject
    ): Attachment {
        val value = context!!.deserialize<T>(
            jsonObject.get(typeName),
            T::class.java
        )
            return Attachment(
                type = typeName,
                value = value as AttachmentType
            )
    }
}