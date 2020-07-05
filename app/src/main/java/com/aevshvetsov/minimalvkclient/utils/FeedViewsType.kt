package com.aevshvetsov.minimalvkclient.utils

/**
 * Created by Alexander Shvetsov on 05.07.2020
 */
enum class FeedViewsType(val type: Int) {
    TEXT_ONLY(type = 1),
    WITH_VIDEO(2),
    WITH_PHOTO(3),
    WITH_AUDIO(4)
}