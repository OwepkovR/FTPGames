package ru.owepkovr.freetoplaygames

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.logger.Level
import ru.owepkovr.freetoplaygames.di.appComponent

class App : Application() {
    override fun onCreate() {
        initKoin()
        super.onCreate()
    }

    private fun initKoin() {
        startKoin{
            androidLogger(Level.ERROR)
            androidContext(applicationContext)
            modules(appComponent)
        }
    }
}