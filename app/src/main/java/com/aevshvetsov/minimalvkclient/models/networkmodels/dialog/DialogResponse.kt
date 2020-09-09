package com.aevshvetsov.minimalvkclient.models.networkmodels.dialog


data class DialogResponse(
    val count: Int,
    val groups: List<DialogGroup>,
    val profiles: List<DialogProfile>,
    val items: List<DialogItem>
)