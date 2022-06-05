package ru.owepkovr.freetoplaygames.arch.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class NetworkErrorHandler {

    private val _networkErrorLive = MutableLiveData<Int>()
    val networkErrorLive: LiveData<Int> = _networkErrorLive

    fun setNetworkError(errorCode: Int) {
        _networkErrorLive.postValue(errorCode)
    }

    companion object {
        const val CODE_UNAUTHORIZED = 401
        const val CODE_INTERNAL_ERROR = 500
    }

}