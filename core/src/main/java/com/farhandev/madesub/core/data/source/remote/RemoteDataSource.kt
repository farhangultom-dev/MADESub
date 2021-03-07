package com.farhandev.madesub.core.data.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.farhandev.madesub.core.data.source.remote.network.ApiResponse
import com.farhandev.madesub.core.data.source.remote.network.ApiService
import com.farhandev.madesub.core.data.source.remote.response.ListResponse
import com.farhandev.madesub.core.data.source.remote.response.NewsResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource(private val apiService: ApiService){

    fun getAllNews(): Flow<ApiResponse<List<NewsResponse>>> {
        return flow{
            try {
                val response = apiService.getNews()
                val data = response?.articles

                if (data.isNotEmpty()){
                    emit(ApiResponse.Success(response.articles))
                }else{
                    emit(ApiResponse.Empty)
                }
            }catch (e: Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}