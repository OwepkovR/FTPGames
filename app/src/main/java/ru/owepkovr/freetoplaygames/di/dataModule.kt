package ru.owepkovr.freetoplaygames.di

import org.koin.dsl.module
import ru.owepkovr.freetoplaygames.interactors.NetworkRepository

val dataModule = module {
    single { NetworkRepository(get()) }
}