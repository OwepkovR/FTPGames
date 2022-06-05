package ru.owepkovr.freetoplaygames.arch.handler

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class ErrorMessageHandler {

    private val _networkErrorMessageLive = MutableLiveData<String>()
    internal val networkErrorMessageLive: LiveData<String> = _networkErrorMessageLive

    fun showErrorMessage(message: String) {
        _networkErrorMessageLive.postValue(message)
    }

}