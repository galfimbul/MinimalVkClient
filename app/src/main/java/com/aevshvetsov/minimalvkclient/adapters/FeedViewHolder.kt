package com.aevshvetsov.minimalvkclient.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.aevshvetsov.minimalvkclient.models.appmodels.FeedItemModel

abstract class FeedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(item: FeedItemModel)
}
