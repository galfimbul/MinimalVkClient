package com.aevshvetsov.minimalvkclient.adapters

import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aevshvetsov.minimalvkclient.R
import com.aevshvetsov.minimalvkclient.models.appmodels.FeedItemModel
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.base_bottom_views.view.*
import kotlinx.android.synthetic.main.base_top_views.view.*
import kotlinx.android.synthetic.main.feed_with_photo_item.view.*

/**
 * Created by Alexander Shvetsov on 27.06.2020
 */
class FeedRecyclerAdapter : RecyclerView.Adapter<FeedViewHolder>() {
    lateinit var itemsList: List<FeedItemModel>
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return FeedWithPhotoViewHolder(inflater.inflate(R.layout.feed_with_photo_item, parent, false))

    }

    override fun getItemCount(): Int {
        return itemsList.size
    }

    override fun onBindViewHolder(holder: FeedViewHolder, position: Int) {
        holder.bind(itemsList[position])
    }

    fun submitList(list: List<FeedItemModel>) {
        itemsList = list
        notifyDataSetChanged()
    }
}

class FeedWithPhotoViewHolder(itemView: View) : FeedViewHolder(itemView) {
    override fun bind(item: FeedItemModel) {
        with(itemView) {
            tv_group_name.text = item.groupName
            tv_publish_time.text = item.postTime

            if (item.text.isEmpty()) {
                tv_caption.visibility = View.GONE
            } else {
                tv_caption.visibility = View.VISIBLE
                tv_caption.movementMethod = ScrollingMovementMethod()
                tv_caption.text = item.text
            }

            tv_views_count.text = item.views.toString()
        }
        Glide.with(itemView.author_logo).load(item.groupPhotoUrl).into(itemView.author_logo)
        Glide.with(itemView.iv_post_photo).load(item.attachmentUrl).into(itemView.iv_post_photo)
    }
}