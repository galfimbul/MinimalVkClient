package com.aevshvetsov.minimalvkclient.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aevshvetsov.minimalvkclient.R
import com.aevshvetsov.minimalvkclient.models.appmodels.FeedItemModel
import com.aevshvetsov.minimalvkclient.ui.viewholders.FeedWithPhotoViewHolder
import com.aevshvetsov.minimalvkclient.ui.viewholders.FeedWithVideoViewHolder
import com.aevshvetsov.minimalvkclient.utils.FeedViewsType

/**
 * Created by Alexander Shvetsov on 27.06.2020
 */
class FeedRecyclerAdapter : RecyclerView.Adapter<FeedViewHolder>() {
    private lateinit var itemsList: List<FeedItemModel>
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            FeedViewsType.WITH_PHOTO.type -> {
                FeedWithPhotoViewHolder(
                    inflater.inflate(
                        R.layout.feed_with_photo_item,
                        parent,
                        false
                    )
                )
            }
            FeedViewsType.WITH_VIDEO.type -> {
                FeedWithVideoViewHolder(inflater.inflate(R.layout.feed_with_photo_item, parent, false))
            }
            else -> FeedWithPhotoViewHolder(
                inflater.inflate(
                    R.layout.feed_with_photo_item,
                    parent,
                    false
                )
            )
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (itemsList[position].attachmentViewType) {
            FeedViewsType.TEXT_ONLY.type -> {
                FeedViewsType.TEXT_ONLY.type
            }
            FeedViewsType.WITH_PHOTO.type -> {
                FeedViewsType.WITH_PHOTO.type
            }
            FeedViewsType.WITH_VIDEO.type -> {
                FeedViewsType.WITH_VIDEO.type
            }
            FeedViewsType.WITH_AUDIO.type -> {
                FeedViewsType.WITH_AUDIO.type
            }
            else -> throw Exception("Unsupported viewType")
        }
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

