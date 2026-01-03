package com.sleepingcats.komposer.data.news.di

import com.sleepingcats.komposer.data.news.NewsService
import com.sleepingcats.komposer.data.news.repository.NewsRepository
import com.sleepingcats.komposer.data.news.repository.NewsRepositoryImpl
import org.koin.dsl.module

val newsDataModules = module {
    single<NewsService> { NewsService(httpClient = get()) }
    single<NewsRepository> { NewsRepositoryImpl(service = get()) }
}