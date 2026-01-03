package com.sleepingcats.komposer.data.news.repository

import com.sleepingcats.komposer.data.news.model.NewsDto

interface NewsRepository {
    suspend fun getNews(): List<NewsDto>
}