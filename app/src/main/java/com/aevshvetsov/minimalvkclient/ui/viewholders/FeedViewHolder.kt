package com.aevshvetsov.minimalvkclient.ui.viewholders

import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aevshvetsov.minimalvkclient.extansions.loadImage
import com.aevshvetsov.minimalvkclient.models.appmodels.FeedItemModel
import kotlinx.android.synthetic.main.base_bottom_views.view.*
import kotlinx.android.synthetic.main.base_top_views.view.*
import kotlinx.android.synthetic.main.feed_with_photo_item.view.*

abstract class FeedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val authorName: TextView = itemView.tv_group_name
    private val publishTime: TextView = itemView.tv_publish_time
    private val viewsCount: TextView = itemView.tv_views_count
    private val likesCount: TextView = itemView.tv_likes_count
    private val commentsCount: TextView = itemView.tv_comments_count
    fun bindBaseValues(item: FeedItemModel) {
        itemView.author_logo.loadImage(item.groupPhotoUrl)
        authorName.text = item.groupName
        publishTime.text = item.postTime
        viewsCount.text = item.views.toString()
        likesCount.text = "${item.likes_count}"
        if (item.canComment == 0) {
            commentsCount.visibility = View.GONE
        } else {
            commentsCount.text = "${item.commentsCount}"
            commentsCount.visibility = View.VISIBLE
        }
        with(itemView) {
            if (item.text.isEmpty()) {
                tv_caption.visibility = View.GONE
            } else {
                Log.d("M_FeedRecyclerAdapter", "${tv_caption.text}\n\n")
                tv_caption.visibility = View.VISIBLE
                tv_caption.text = item.text
            }
        }

    }

    abstract fun bind(item: FeedItemModel)
}

