package com.sleepingcats.komposer.di

import org.koin.core.context.startKoin

fun initKoin() {
    val modules = sharedModules
    startKoin { modules(modules) }
}