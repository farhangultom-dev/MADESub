package com.farhandev.madesub.core.utils

import com.farhandev.madesub.core.data.source.local.entity.NewsEntity
import com.farhandev.madesub.core.data.source.remote.response.NewsResponse
import com.farhandev.madesub.core.domain.model.News

object DataMapper {
    fun mapResponseToEntities(input: List<NewsResponse>): List<NewsEntity>{
        val newsList = ArrayList<NewsEntity>()
        input.map{
            val news = NewsEntity(
                    publishedAt = it.publishedAt.toString(),
                    author = it.author,
                    urlToImage = it.urlToImage,
                    description = it.description,
                    title = it.title,
                    content = it.content,
                    isFavorite = false
            )
            newsList.add(news)
        }
        return newsList
    }

    fun mapEntitiesToDomain(input: List<NewsEntity>): List<News> =
            input.map {
                News(
                        publishedAt = it.publishedAt,
                        author = it.author,
                        urlToImage = it.urlToImage,
                        description = it.description,
                        title = it.title,
                        content = it.content,
                        isFavorite = it.isFavorite
                )
            }

    fun mapDomainToEntity(input: News) = NewsEntity(
            publishedAt = input.publishedAt.toString(),
            author = input.author,
            urlToImage = input.urlToImage,
            description = input.description,
            title = input.title,
            content = input.content,
            isFavorite = input.isFavorite
    )
}