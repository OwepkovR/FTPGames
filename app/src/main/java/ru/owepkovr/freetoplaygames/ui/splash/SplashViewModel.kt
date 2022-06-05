package ru.owepkovr.freetoplaygames.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout
import ru.owepkovr.freetoplaygames.arch.navigation.AppNavigator
import ru.owepkovr.freetoplaygames.arch.ui.BaseViewModel
import ru.owepkovr.freetoplaygames.data.model.network.SingleGameInfo
import ru.owepkovr.freetoplaygames.interactors.NetworkRepository

class SplashViewModel(
    private val repository: NetworkRepository
) : BaseViewModel<SplashViewModelState>() {

    fun auth() {
        state = SplashViewModelState(isLoading = false, authed = true).apply { render() }
    }

    fun navigateToList() {
        AppNavigator.toGamesList().navigate()
    }

    override var state = SplashViewModelState()
}