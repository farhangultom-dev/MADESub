package com.farhandev.madesub.core.di

import androidx.room.Room
import com.farhandev.madesub.core.data.source.NewsRepository
import com.farhandev.madesub.core.data.source.local.LocalDataSource
import com.farhandev.madesub.core.data.source.local.room.NewsDatabase
import com.farhandev.madesub.core.data.source.remote.RemoteDataSource
import com.farhandev.madesub.core.data.source.remote.network.ApiService
import com.farhandev.madesub.core.domain.repository.XNewsRepository
import com.farhandev.madesub.core.utils.AppExecutor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<NewsDatabase>().newsDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            NewsDatabase::class.java, "News.db"
        ).fallbackToDestructiveMigration().build()
    }
}

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutor() }
    single<XNewsRepository> { NewsRepository(get(), get(), get()) }
}