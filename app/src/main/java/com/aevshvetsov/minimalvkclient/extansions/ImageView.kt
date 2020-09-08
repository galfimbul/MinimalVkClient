package com.aevshvetsov.minimalvkclient.extansions

import android.widget.ImageView
import com.aevshvetsov.minimalvkclient.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

/**
 * Created by Alexander Shvetsov on 07.09.2020
 */

fun ImageView.loadImage(url: String) {
    val requestOptions = RequestOptions().placeholder(R.drawable.placeholder)
    Glide.with(this)
        .setDefaultRequestOptions(requestOptions)
        .load(url)
        .into(this)
}