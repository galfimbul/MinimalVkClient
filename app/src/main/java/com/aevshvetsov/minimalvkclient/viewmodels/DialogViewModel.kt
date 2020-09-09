package com.aevshvetsov.minimalvkclient.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.aevshvetsov.minimalvkclient.models.networkmodels.dialog.DialogNetworkModel
import com.aevshvetsov.minimalvkclient.repositories.DialogRepository
import com.aevshvetsov.minimalvkclient.utils.RetrofitBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class DialogViewModel : ViewModel() {
    private val dialogsJob = Job()
    private val repo = DialogRepository(RetrofitBuilder.dialogApi)
    fun getDialogs(): LiveData<DialogNetworkModel> {
        return liveData(Dispatchers.IO + dialogsJob) {
            val dialogs = repo.getDialogs()
            emit(dialogs)
        }
    }

    override fun onCleared() {
        super.onCleared()
        dialogsJob.cancel()
    }
}
