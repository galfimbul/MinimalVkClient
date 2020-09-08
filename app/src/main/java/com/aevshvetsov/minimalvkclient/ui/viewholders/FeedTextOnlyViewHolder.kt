package com.aevshvetsov.minimalvkclient.ui.viewholders

import android.view.View
import com.aevshvetsov.minimalvkclient.models.appmodels.FeedItemModel

/**
 * Created by Alexander Shvetsov on 07.09.2020
 */
class FeedTextOnlyViewHolder(itemView: View) : FeedViewHolder(itemView) {

    override fun bind(item: FeedItemModel) = super.bindBaseValues(item)
}