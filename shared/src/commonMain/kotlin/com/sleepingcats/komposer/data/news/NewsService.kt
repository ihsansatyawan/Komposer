package com.sleepingcats.komposer.data.news

import com.sleepingcats.komposer.data.news.model.NewsDto
import com.sleepingcats.komposer.data.news.model.NewsResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class NewsService(private val httpClient: HttpClient) {
    suspend fun fetchArticles(
        country: String = "us",
        category: String = "business"
    ): List<NewsDto> {
        val response: NewsResponse = httpClient.get("v2/top-headlines") {
            parameter("country", country)
            parameter("category", category)
        }.body()

        return response.articles.orEmpty()
    }
}