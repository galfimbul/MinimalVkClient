package com.aevshvetsov.minimalvkclient.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aevshvetsov.minimalvkclient.R
import com.aevshvetsov.minimalvkclient.models.networkmodels.Attachment
import com.aevshvetsov.minimalvkclient.ui.viewholders.AttachmentViewHolder
import com.aevshvetsov.minimalvkclient.ui.viewholders.PhotoViewHolder
import com.aevshvetsov.minimalvkclient.utils.FeedAttachmentType

/**
 * Created by Alexander Shvetsov on 08.09.2020
 */
class MultiplePhotoAttachmentsAdapter : RecyclerView.Adapter<AttachmentViewHolder>() {
    lateinit var attachmentsList: List<Attachment>
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AttachmentViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            FeedAttachmentType.PHOTO.value -> {
                PhotoViewHolder(
                    inflater.inflate(
                        R.layout.multiple_photo_attachment_item,
                        parent, false
                    )
                )
            }
            FeedAttachmentType.VIDEO.value -> {
                PhotoViewHolder(
                    inflater.inflate(
                        R.layout.multiple_photo_attachment_item,
                        parent, false
                    )
                )
            }
            else -> {
                PhotoViewHolder(
                    inflater.inflate(
                        R.layout.multiple_photo_attachment_item,
                        parent, false
                    )
                )
            }
        }
    }

    override fun getItemCount(): Int {
        return if (attachmentsList.size > 5) 5 else attachmentsList.size
    }

    override fun onBindViewHolder(holder: AttachmentViewHolder, position: Int) {
        holder.bind(attachmentsList[position])
    }

    fun setData(attachments: List<Attachment>) {
        attachmentsList = attachments
        notifyDataSetChanged()
    }
}