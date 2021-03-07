package com.farhandev.madesub.core.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.farhandev.madesub.core.data.source.local.LocalDataSource
import com.farhandev.madesub.core.data.source.remote.RemoteDataSource
import com.farhandev.madesub.core.data.source.remote.network.ApiResponse
import com.farhandev.madesub.core.data.source.remote.response.NewsResponse
import com.farhandev.madesub.core.domain.model.News
import com.farhandev.madesub.core.domain.repository.XNewsRepository
import com.farhandev.madesub.core.utils.AppExecutor
import com.farhandev.madesub.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class NewsRepository(
        private val remoteDataSource: RemoteDataSource,
        private val localDataSource: LocalDataSource,
        private val appExecutor: AppExecutor
): XNewsRepository{

        override fun getAllNews(): Flow<Resource<List<News>>> =
                object: NetworkBoundResource<List<News>, List<NewsResponse>>(appExecutor){
                        override fun loadFromDb(): Flow<List<News>> {
                                return localDataSource.getNews().map{
                                        DataMapper.mapEntitiesToDomain(it)
                                }
                        }

                        override fun shouldFetch(data: List<News>?): Boolean =
                                data == null || data.isEmpty() //true //aktifkan true untuk mode hanya online

                        override suspend fun saveCallResult(data: List<NewsResponse>) {
                                val newsList = DataMapper.mapResponseToEntities(data)
                                localDataSource.insertNews(newsList)
                        }

                        override suspend fun createCall(): Flow<ApiResponse<List<NewsResponse>>> =
                                remoteDataSource.getAllNews()
                }.asFlow()

        override fun getFavorite(): Flow<List<News>> {
                return localDataSource.getFavorite().map{
                        DataMapper.mapEntitiesToDomain(it)
                }
        }

        override fun setFavorite(news: News, state: Boolean) {
                val newsEntity = DataMapper.mapDomainToEntity(news)
                appExecutor.diskIO().execute { localDataSource.setFavorite(newsEntity, state) }
        }
}