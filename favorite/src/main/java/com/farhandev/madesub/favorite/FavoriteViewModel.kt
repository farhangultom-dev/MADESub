package com.farhandev.madesub.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.farhandev.madesub.core.domain.usecase.NewsUseCase

class FavoriteViewModel(newsUseCase: NewsUseCase): ViewModel() {
    val favoriteNews = newsUseCase.getFavorite().asLiveData()
}