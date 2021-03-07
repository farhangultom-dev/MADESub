package com.farhandev.madesub.core.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.farhandev.madesub.core.data.source.local.entity.NewsEntity
import com.farhandev.madesub.core.data.source.remote.response.NewsResponse
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {

    @Query("SELECT * FROM news")
    fun getNews(): Flow<List<NewsEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNews(news: List<NewsEntity>)

    @Query("SELECT * FROM news WHERE isFavorite = 1")
    fun getFavorite(): Flow<List<NewsEntity>>

    @Update
    fun updateFavoriteNews(news: NewsEntity)
}