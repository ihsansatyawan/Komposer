package com.sleepingcats.komposer.domain.news.di

import com.sleepingcats.komposer.domain.news.usecase.GetNewsListUseCase
import org.koin.dsl.module

val newsDomainModules = module {
    single<GetNewsListUseCase> { GetNewsListUseCase(repository = get()) }
}