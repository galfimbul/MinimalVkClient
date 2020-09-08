package com.aevshvetsov.minimalvkclient.ui.viewholders

import android.view.View
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.aevshvetsov.minimalvkclient.adapters.MultiplePhotoAttachmentsAdapter
import com.aevshvetsov.minimalvkclient.models.appmodels.FeedItemModel
import kotlinx.android.synthetic.main.feed_with_multiple_photo_item.view.*

class FeedWithMultiplePhotoViewHolder(itemView: View) : FeedViewHolder(itemView) {
    private val attachmentsAdapter = MultiplePhotoAttachmentsAdapter()
    private val lm = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
    override fun bind(item: FeedItemModel) {
        super.bindBaseValues(item)
        with(itemView.rv_multiple_photo_attachments) {
            layoutManager = lm
            adapter = attachmentsAdapter
            setHasFixedSize(true)
            attachmentsAdapter.setData(item.attachments)
        }
    }
}