package com.sleepingcats.komposer

import android.app.Application
import com.sleepingcats.komposer.di.sharedModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AndroidApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initializeKoin()
    }

    private fun initializeKoin() {
        val modules = sharedModules
        startKoin {
            androidContext(this@AndroidApp)
            modules(modules)
        }
    }
}