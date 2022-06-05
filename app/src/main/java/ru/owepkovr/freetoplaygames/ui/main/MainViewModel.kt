package ru.owepkovr.freetoplaygames.ui.main

import ru.owepkovr.freetoplaygames.arch.navigation.AppNavigator
import ru.owepkovr.freetoplaygames.arch.ui.BaseViewModel
import ru.owepkovr.freetoplaygames.interactors.NetworkRepository

class MainViewModel(private val repository: NetworkRepository) : BaseViewModel<MainViewModelState>() {
    override var state = MainViewModelState()
}