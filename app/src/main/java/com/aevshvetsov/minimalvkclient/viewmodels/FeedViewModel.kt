package com.aevshvetsov.minimalvkclient.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.aevshvetsov.minimalvkclient.models.networkmodels.NetworkModels
import com.aevshvetsov.minimalvkclient.network.ApiHelper
import com.aevshvetsov.minimalvkclient.repositories.FeedRepository
import com.aevshvetsov.minimalvkclient.utils.RetrofitBuilder
import kotlinx.coroutines.Dispatchers

class FeedViewModel : ViewModel() {
    val repo = FeedRepository(ApiHelper(RetrofitBuilder.feedApi))
    fun getUsers(): LiveData<NetworkModels> {
        return liveData(Dispatchers.IO) {
            val feedList = repo.getFeed()
            emit(feedList)
        }
    }
}
