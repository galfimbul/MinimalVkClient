package com.aevshvetsov.minimalvkclient.utils.mappers

import android.util.Log
import com.aevshvetsov.minimalvkclient.models.appmodels.FeedItemModel
import com.aevshvetsov.minimalvkclient.models.networkmodels.*
import com.aevshvetsov.minimalvkclient.utils.Constants
import com.aevshvetsov.minimalvkclient.utils.FeedAttachmentType
import java.text.ParseException
import java.util.*
import java.util.concurrent.TimeUnit


/**
 * Created by Alexander Shvetsov on 27.06.2020
 */
class FeedModelsMapper(response: Response) {
    private val items: List<Item> = response.items
    private val groups: List<Group> = response.groups
    private val profiles: List<Profile> = response.profiles

    fun createListForUI(): List<FeedItemModel> {
        return items.map { item ->
            mapFeedNetworkToApp(item)
        }
    }

    private fun mapFeedNetworkToApp(item: Item): FeedItemModel {
        var group: Group? = null
        var profile: Profile? = null
        val isGroup: Boolean
        isGroup = when {
            item.sourceId < 0 -> {
                group = findGroup(item.sourceId)
                true
            }
            else -> {
                profile = findProfile(item.sourceId)
                false
            }
        }
        val views = item.views.count

        val postTime = convertTimeToText(item.date * Constants.MILLISECONDS_MULTIPLIER)
        when (item.attachments.size) {
            1 -> {
                if (item.attachments[0].type == FeedAttachmentType.PHOTO.name.toLowerCase()) {
                    val photoUrl =
                        (item.attachments[0].value as Photo).sizes.first { it.type == "x" }.url

                    return FeedItemModel(
                        groupName =
                        if (isGroup) group!!.name else "${profile!!.firstName} ${profile.lastName}",
                        groupPhotoUrl = if (isGroup) group!!.photo50 else profile!!.photo50,
                        text = item.text,
                        postTime = postTime,
                        attachmentUrl = photoUrl,
                        isLiked = item.likes.userLikes == 1,
                        views = views
                    )
                } else return FeedItemModel()

            }
            else -> {
                return FeedItemModel()
            }
        }
    }

    private fun findGroup(sourceId: Int): Group {
        return groups.first { it.id == sourceId * -1 }
    }

    private fun findProfile(sourceId: Int): Profile {
        return profiles.first { it.id == sourceId }
    }

    private fun convertTimeToText(postTime: Long): String {
        var convTime: String? = null
        val prefix = ""
        val suffix = "Ago"
        try {
            //val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
            val pasTime: Date = (Date(postTime))
            val nowTime = Date()
            val dateDiff: Long = nowTime.time - pasTime.time
            val second: Long = TimeUnit.MILLISECONDS.toSeconds(dateDiff)
            val minute: Long = TimeUnit.MILLISECONDS.toMinutes(dateDiff)
            val hour: Long = TimeUnit.MILLISECONDS.toHours(dateDiff)
            val day: Long = TimeUnit.MILLISECONDS.toDays(dateDiff)
            if (second < 60) {
                convTime = "$second Seconds $suffix"
            } else if (minute < 60) {
                convTime = "$minute Minutes $suffix"
            } else if (hour < 24) {
                convTime = "$hour Hours $suffix"
            } else if (day >= 7) {
                convTime = if (day > 360) {
                    (day / 360).toString() + " Years " + suffix
                } else if (day > 30) {
                    (day / 30).toString() + " Months " + suffix
                } else {
                    (day / 7).toString() + " Week " + suffix
                }
            } else if (day < 7) {
                convTime = "$day Days $suffix"
            }
        } catch (e: ParseException) {
            e.printStackTrace()
            Log.e("ConvTimeE", e.localizedMessage!!)
        }
        return convTime!!
    }
}