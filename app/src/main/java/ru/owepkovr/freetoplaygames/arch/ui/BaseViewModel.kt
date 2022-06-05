package ru.owepkovr.freetoplaygames.arch.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.owepkovr.freetoplaygames.arch.navigation.NavigationCommand

abstract class BaseViewModel<S> : ViewModel() {
    private val _navigationLive = MutableLiveData<NavigationCommand>()
    internal val navigationLive: LiveData<NavigationCommand> = _navigationLive

    abstract var state: S

    private val _stateLiveData = MutableLiveData<S>()
    val stateLive: LiveData<S> get() = _stateLiveData

    fun NavigationCommand.navigate() {
        _navigationLive.postValue(this)
    }

    fun S.render() {
        state = this
        viewModelScope.launch(Dispatchers.Main) {
            _stateLiveData.value = state
        }
    }
}