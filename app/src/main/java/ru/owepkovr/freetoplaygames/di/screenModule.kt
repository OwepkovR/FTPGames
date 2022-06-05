package ru.owepkovr.freetoplaygames.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.owepkovr.freetoplaygames.ui.gameslist.GamesListViewModel
import ru.owepkovr.freetoplaygames.ui.main.MainViewModel
import ru.owepkovr.freetoplaygames.ui.splash.SplashViewModel

val screenModule = module {
    viewModel { SplashViewModel(get()) }
    viewModel { MainViewModel(get()) }
    viewModel { GamesListViewModel(get()) }
}