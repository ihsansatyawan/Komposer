package com.sleepingcats.komposer.di

import com.sleepingcats.komposer.data.news.di.newsDataModules
import com.sleepingcats.komposer.domain.news.di.newsDomainModules
import com.sleepingcats.komposer.presentation.news.di.newsPresentationModules
import org.koin.core.module.Module

val newsModules: List<Module> = listOf(
    newsDataModules,
    newsDomainModules,
    newsPresentationModules
)