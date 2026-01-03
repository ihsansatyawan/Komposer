package com.sleepingcats.komposer.domain.news.usecase

import com.sleepingcats.komposer.data.news.repository.NewsRepository
import com.sleepingcats.komposer.domain.base.model.NoArgs
import com.sleepingcats.komposer.domain.base.usecase.BaseUseCaseAsync
import com.sleepingcats.komposer.domain.news.model.News

class GetNewsListUseCase(private val repository: NewsRepository) : BaseUseCaseAsync<NoArgs, List<News>>() {
    override suspend fun execute(args: NoArgs): List<News> {
        val news = repository.getNews()
        return news.map { news ->
            News(
                title = news.title.orEmpty(),
                description = news.description.orEmpty(),
                date = news.publishedAt.orEmpty(),
                imageUrl = news.urlToImage.orEmpty()
            )
        }
    }
}