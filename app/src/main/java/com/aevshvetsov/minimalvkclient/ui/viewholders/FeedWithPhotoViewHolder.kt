package com.aevshvetsov.minimalvkclient.ui.viewholders

import android.view.View
import com.aevshvetsov.minimalvkclient.extansions.loadImage
import com.aevshvetsov.minimalvkclient.models.appmodels.FeedItemModel
import kotlinx.android.synthetic.main.feed_with_photo_item.view.*

class FeedWithPhotoViewHolder(itemView: View) : FeedViewHolder(itemView) {
    override fun bind(item: FeedItemModel) {
        super.bindBaseValues(item)

        itemView.iv_post_photo.loadImage(item.attachmentUrl)
    }
}