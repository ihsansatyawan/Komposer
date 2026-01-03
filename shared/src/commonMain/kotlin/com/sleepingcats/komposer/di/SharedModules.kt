package com.sleepingcats.komposer.di

import com.sleepingcats.komposer.data.base.di.networkModules
import org.koin.core.module.Module

val sharedModules: List<Module> = collectModules()

private fun collectModules(): List<Module> {
    val modules: ArrayList<Module> = arrayListOf(networkModules)
    modules.addAll(newsModules)
    return modules
}