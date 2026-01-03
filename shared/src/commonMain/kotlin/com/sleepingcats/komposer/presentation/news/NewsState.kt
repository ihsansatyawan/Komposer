package com.sleepingcats.komposer.presentation.news

import com.sleepingcats.komposer.domain.news.model.News

data class NewsState(
    val isLoading: Boolean = false,
    val news: List<News> = emptyList(),
    val error: String? = null
)
