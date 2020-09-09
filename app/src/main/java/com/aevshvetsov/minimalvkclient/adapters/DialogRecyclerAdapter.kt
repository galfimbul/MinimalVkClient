package com.aevshvetsov.minimalvkclient.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aevshvetsov.minimalvkclient.R
import com.aevshvetsov.minimalvkclient.models.appmodels.DialogItemModel
import com.aevshvetsov.minimalvkclient.ui.viewholders.DialogViewHolder

/**
 * Created by Alexander Shvetsov on 27.06.2020
 */
class DialogRecyclerAdapter : RecyclerView.Adapter<DialogViewHolder>() {
    private lateinit var itemsList: List<DialogItemModel>
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DialogViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return DialogViewHolder(inflater.inflate(R.layout.dialog_item, parent, false))
    }

    override fun getItemCount(): Int {
        return itemsList.size
    }

    override fun onBindViewHolder(holder: DialogViewHolder, position: Int) {
        holder.bind(itemsList[position])
    }

    fun submitList(list: List<DialogItemModel>) {
        itemsList = list
        notifyDataSetChanged()
    }
}

