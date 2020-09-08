package com.aevshvetsov.minimalvkclient.ui.viewholders

import android.util.Log
import android.view.View
import com.aevshvetsov.minimalvkclient.models.appmodels.FeedItemModel
import kotlinx.android.synthetic.main.base_bottom_views.view.*
import kotlinx.android.synthetic.main.base_top_views.view.*
import kotlinx.android.synthetic.main.feed_with_photo_item.view.*

class FeedWithVideoViewHolder(view: View) : FeedViewHolder(view) {
    override fun bind(item: FeedItemModel) {
        with(itemView) {
            tv_group_name.text = item.groupName
            tv_publish_time.text = item.postTime

            if (item.text.isEmpty()) {
                tv_caption.visibility = View.GONE
            } else {
                Log.d("M_FeedRecyclerAdapter", "${tv_caption.text}\n\n")
                tv_caption.visibility = View.VISIBLE
                tv_caption.maxLines = Int.MAX_VALUE
                tv_caption.text = item.text
            }
            tv_views_count.text = item.views.toString()
            tv_likes_count.text = "${item.likes_count}"
            if (item.canComment == 0) {
                tv_comments_count.visibility = View.GONE
            } else {
                tv_comments_count.text = "${item.commentsCount}"
                tv_comments_count.visibility = View.VISIBLE
            }
        }
    }
}
