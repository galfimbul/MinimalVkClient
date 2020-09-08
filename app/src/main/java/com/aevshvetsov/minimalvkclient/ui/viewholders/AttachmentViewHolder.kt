package com.aevshvetsov.minimalvkclient.ui.viewholders

import android.view.View
import androidx.constraintlayout.widget.ConstraintSet
import androidx.recyclerview.widget.RecyclerView
import com.aevshvetsov.minimalvkclient.extansions.loadImage
import com.aevshvetsov.minimalvkclient.models.networkmodels.Attachment
import com.aevshvetsov.minimalvkclient.models.networkmodels.AttachmentType
import com.aevshvetsov.minimalvkclient.models.networkmodels.Photo
import kotlinx.android.synthetic.main.multiple_photo_attachment_item.view.*

sealed class AttachmentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), AttachmentType {
    abstract fun bind(item: Attachment)
}

class PhotoViewHolder(itemView: View) : AttachmentViewHolder(itemView) {
    private val set = ConstraintSet()
    private val constraintLayout = itemView.cl_multiple_photo_item
    private val imgItem = itemView.iv_attachment_item
    override fun bind(item: Attachment) {
        if (item.value is Photo) {
            val photo = (item.value as Photo).sizes.find { it.type == "r" }
            itemView.iv_attachment_item
                .loadImage(photo.let { return@let it!!.url })
            with(set) {
                val posterRatio = String.format("%d:%d", photo?.width, photo?.height)
                clone(constraintLayout)
                setDimensionRatio(imgItem.id, posterRatio)
                applyTo(constraintLayout)
            }
        }
    }
}
