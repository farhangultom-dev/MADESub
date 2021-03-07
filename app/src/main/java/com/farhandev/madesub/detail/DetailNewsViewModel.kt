package com.farhandev.madesub.detail

import androidx.lifecycle.ViewModel
import com.farhandev.madesub.core.domain.model.News
import com.farhandev.madesub.core.domain.usecase.NewsUseCase

class DetailNewsViewModel(private val newsUseCase: NewsUseCase): ViewModel() {
    fun setFavorrite(news: News, newStatus: Boolean)=
        newsUseCase.setFavorite(news, newStatus)
}