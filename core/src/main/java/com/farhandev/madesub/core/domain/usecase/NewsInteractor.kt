package com.farhandev.madesub.core.domain.usecase

import androidx.lifecycle.LiveData
import com.farhandev.madesub.core.data.source.Resource
import com.farhandev.madesub.core.domain.model.News
import com.farhandev.madesub.core.domain.repository.XNewsRepository
import kotlinx.coroutines.flow.Flow

class NewsInteractor(private val newsRepository: XNewsRepository): NewsUseCase {
    override fun getAllNews(): Flow<Resource<List<News>>> = newsRepository.getAllNews()

    override fun getFavorite(): Flow<List<News>> = newsRepository.getFavorite()

    override fun setFavorite(news: News, state: Boolean) = newsRepository.setFavorite(news, state)
}