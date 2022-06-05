package ru.owepkovr.freetoplaygames.ui.splash

data class SplashViewModelState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val authed: Boolean = false
)