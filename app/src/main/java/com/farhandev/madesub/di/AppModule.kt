package com.farhandev.madesub.di

import com.farhandev.madesub.core.domain.usecase.NewsInteractor
import com.farhandev.madesub.core.domain.usecase.NewsUseCase
import com.farhandev.madesub.detail.DetailNewsViewModel
import com.farhandev.madesub.home.HomeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<NewsUseCase> { NewsInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DetailNewsViewModel(get()) }
}