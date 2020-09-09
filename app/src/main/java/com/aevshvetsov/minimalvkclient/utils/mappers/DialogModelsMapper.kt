package com.aevshvetsov.minimalvkclient.utils.mappers

import android.util.Log
import com.aevshvetsov.minimalvkclient.models.appmodels.DialogItemModel
import com.aevshvetsov.minimalvkclient.models.networkmodels.dialog.DialogGroup
import com.aevshvetsov.minimalvkclient.models.networkmodels.dialog.DialogItem
import com.aevshvetsov.minimalvkclient.models.networkmodels.dialog.DialogProfile
import com.aevshvetsov.minimalvkclient.models.networkmodels.dialog.DialogResponse
import com.aevshvetsov.minimalvkclient.utils.Constants
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit


/**
 * Created by Alexander Shvetsov on 27.06.2020
 */
class DialogModelsMapper(response: DialogResponse) {
    private val items: List<DialogItem> = response.items
    private val groups: List<DialogGroup> = response.groups
    private val profiles: List<DialogProfile> = response.profiles
    private var group: DialogGroup? = null
    private var profile: DialogProfile? = null

    fun createListForUI(): List<DialogItemModel> {
        return items.map { item ->
            mapFeedNetworkToApp(item)
        }
    }

    private fun mapFeedNetworkToApp(item: DialogItem): DialogItemModel {
        val postTime = convertTimeToText(item.lastMessage.date * Constants.MILLISECONDS_MULTIPLIER)
        val groupName =
            if (isGroup(item.lastMessage.fromId)) group!!.name else "${profile!!.firstName} ${profile!!.lastName}"
        val authorAvatar = if (isGroup(item.lastMessage.fromId)) group!!.photo50 else profile!!.avatarUrl
        val text =
            if (item.lastMessage.out == 1) "Вы: ${item.lastMessage.text}" else item.lastMessage.text

        return DialogItemModel(
            avatar = authorAvatar,
            lastMessage = text,
            messageTime = postTime,
            groupName = groupName
        )
    }

    private fun isGroup(sourceId: Int): Boolean = when {
        sourceId < 0 -> {
            group = findGroup(sourceId)
            true
        }
        else -> {
            profile = findProfile(sourceId)
            false
        }
    }

    private fun findGroup(sourceId: Int): DialogGroup {
        return groups.first { it.id == sourceId * -1 }
    }

    private fun findProfile(sourceId: Int): DialogProfile {
        return profiles.first { it.id == sourceId }
    }

    private fun convertTimeToText(postTime: Long): String {
        var convTime: String? = null
        val prefix = ""
        val suffix = "Ago"
        try {
            val hoursDateFormat = SimpleDateFormat("HH:mm", Locale("ru"))
            val daysDateFormat = SimpleDateFormat("dd-MM", Locale("ru"))
            val pasTime: Date = (Date(postTime))
            val nowTime = Date()
            val dateDiff: Long = nowTime.time - pasTime.time
            val second: Long = TimeUnit.MILLISECONDS.toSeconds(dateDiff)
            val minute: Long = TimeUnit.MILLISECONDS.toMinutes(dateDiff)
            val hour: Long = TimeUnit.MILLISECONDS.toHours(dateDiff)
            val day: Long = TimeUnit.MILLISECONDS.toDays(dateDiff)
            if (second < 60) {
                //convTime = "$second Seconds $suffix"
                convTime = "now"
            } else if (minute < 60) {
                //convTime = "$minute Minutes $suffix"
                convTime = hoursDateFormat.format(pasTime)

            } else if (hour < 24) {
                //convTime = "$hour Hours $suffix"
                convTime = hoursDateFormat.format(pasTime)
            } else if (day >= 7) {
                /*convTime = if (day > 360) {
                    (day / 360).toString() + " Years " + suffix
                } else if (day > 30) {
                    (day / 30).toString() + " Months " + suffix
                } else {
                    (day / 7).toString() + " Week " + suffix
                }
            } else if (day < 7) {
                convTime = "$day Days $suffix"*/
                convTime = daysDateFormat.format(pasTime)
            }
        } catch (e: ParseException) {
            e.printStackTrace()
            Log.e("ConvTimeE", e.localizedMessage!!)
        }
        return convTime!!
    }
}