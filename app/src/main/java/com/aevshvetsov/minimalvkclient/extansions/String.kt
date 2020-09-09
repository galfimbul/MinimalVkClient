package com.aevshvetsov.minimalvkclient.extansions

/**
 * Created by Alexander Shvetsov on 09.09.2020
 */
fun String.truncate(value: Int = 16): String {
    val result = this.trim()
    if (result.length <= value) {
        return result
    } else return "${result.substring(0, value).trim()}..."

}