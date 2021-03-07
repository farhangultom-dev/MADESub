package com.farhandev.madesub.core.data.source.remote.network

import com.farhandev.madesub.core.data.source.remote.response.ListResponse
import retrofit2.http.GET

interface ApiService {

    companion object{
        const val apiKey = "abd5255567934cb3a935fa257e71c0d1"
    }

    @GET("top-headlines?country=id&category=technology&apiKey=$apiKey")
    suspend fun getNews(): ListResponse
}