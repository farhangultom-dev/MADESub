package com.farhandev.madesub.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.farhandev.madesub.core.domain.usecase.NewsUseCase

class HomeViewModel(newsUseCase: NewsUseCase): ViewModel() {
    val news = newsUseCase.getAllNews().asLiveData()
}