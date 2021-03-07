package com.farhandev.madesub.core.domain.usecase

import androidx.lifecycle.LiveData
import com.farhandev.madesub.core.data.source.Resource
import com.farhandev.madesub.core.domain.model.News
import kotlinx.coroutines.flow.Flow

interface NewsUseCase {
    fun getAllNews(): Flow<Resource<List<News>>>
    fun getFavorite(): Flow <List<News>>
    fun setFavorite(news: News, state: Boolean)
}