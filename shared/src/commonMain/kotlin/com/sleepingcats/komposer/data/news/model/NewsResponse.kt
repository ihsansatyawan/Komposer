package com.sleepingcats.komposer.data.news.model

import kotlinx.serialization.Serializable

@Serializable
data class NewsResponse(
    val status: String? = null,
    val totalResults: Int? = null,
    val articles: List<NewsDto>? = null
)