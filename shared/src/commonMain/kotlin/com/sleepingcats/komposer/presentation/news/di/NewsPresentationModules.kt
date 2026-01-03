package com.sleepingcats.komposer.presentation.news.di

import com.sleepingcats.komposer.presentation.news.NewsViewModel
import org.koin.dsl.module

val newsPresentationModules = module {
    single<NewsViewModel> { NewsViewModel(getNewsListUseCase = get()) }
}