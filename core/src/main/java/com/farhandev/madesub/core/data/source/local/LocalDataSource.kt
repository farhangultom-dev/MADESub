package com.farhandev.madesub.core.data.source.local

import androidx.lifecycle.LiveData
import com.farhandev.madesub.core.data.source.local.entity.NewsEntity
import com.farhandev.madesub.core.data.source.local.room.NewsDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val newsDao: NewsDao){

    fun getNews(): Flow<List<NewsEntity>> = newsDao.getNews()

    fun getFavorite(): Flow<List<NewsEntity>> = newsDao.getFavorite()

    suspend fun insertNews(newsList: List<NewsEntity>) = newsDao.insertNews(newsList)

    fun setFavorite(news: NewsEntity, newState: Boolean){
        news.isFavorite = newState
        newsDao.updateFavoriteNews(news)
    }
}