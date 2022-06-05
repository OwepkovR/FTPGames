package ru.owepkovr.freetoplaygames.arch.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.owepkovr.freetoplaygames.data.model.ui.NetworkErrorUIModel

class NetworkErrorHandler {

    private val _networkErrorLive = MutableLiveData<NetworkErrorUIModel>()
    val networkErrorLive: LiveData<NetworkErrorUIModel> = _networkErrorLive

    fun setNetworkError(errorCode: Int) {
        when (errorCode) {
            UNKNOWN_ERROR_CODE -> {
                _networkErrorLive.postValue(NetworkErrorUIModel("Ошибка подключения", "Проверьте подключение к сети"))
            }
            CODE_INTERNAL_ERROR -> {
                _networkErrorLive.postValue(NetworkErrorUIModel("Ошибка сервера", "Невозможно выполнить запрос к серверу"))
            }
            else -> {
                _networkErrorLive.postValue(NetworkErrorUIModel("Ошибка", "Возникла непредвиденная ошибка"))
            }
        }
    }

    companion object {
        const val UNKNOWN_ERROR_CODE = 999
        const val CODE_UNAUTHORIZED = 401
        const val CODE_INTERNAL_ERROR = 500
    }

}