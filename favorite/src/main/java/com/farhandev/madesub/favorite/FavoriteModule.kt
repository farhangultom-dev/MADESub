package com.farhandev.madesub.favorite

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoriteNewsModule = module {
    viewModel { FavoriteViewModel(get()) }
}