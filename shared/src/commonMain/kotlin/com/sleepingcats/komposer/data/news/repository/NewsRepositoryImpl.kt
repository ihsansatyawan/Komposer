package com.sleepingcats.komposer.data.news.repository

import com.sleepingcats.komposer.data.news.NewsService
import com.sleepingcats.komposer.data.news.model.NewsDto

class NewsRepositoryImpl(private val service: NewsService) : NewsRepository {
    override suspend fun getNews(): List<NewsDto> {
        val news = service.fetchArticles()
        return news
    }
}