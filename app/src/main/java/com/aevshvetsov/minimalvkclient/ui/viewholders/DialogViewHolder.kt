package com.aevshvetsov.minimalvkclient.ui.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.aevshvetsov.minimalvkclient.extansions.loadImage
import com.aevshvetsov.minimalvkclient.extansions.truncate
import com.aevshvetsov.minimalvkclient.models.appmodels.DialogItemModel
import kotlinx.android.synthetic.main.dialog_item.view.*

class DialogViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val avatar = itemView.iv_avatar
    val message = itemView.tv_last_message
    val time = itemView.tv_dialog_last_message_time
    fun bind(item: DialogItemModel) {
        avatar.loadImage(item.avatar)
        message.text = item.lastMessage.truncate()
        time.text = item.messageTime
    }

}


