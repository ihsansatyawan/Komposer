package com.sleepingcats.komposer.presentation.news.di

import com.sleepingcats.komposer.presentation.news.NewsViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class NewsInjector : KoinComponent {
    val newsViewModel: NewsViewModel by inject()
}