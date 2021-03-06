package com.example.mvvmretrofitapp.di

import com.example.mvvmretrofitapp.network.InterfaceService
import com.example.mvvmretrofitapp.network.NewsRepository
import com.example.mvvmretrofitapp.network.NewsRepositoryInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
//defining feature
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Provides
    @Singleton
    fun providesOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()

    @Provides
    @Singleton
    fun providesNetworkService(okHttpClient: OkHttpClient): InterfaceService =
        Retrofit.Builder()
            .baseUrl(InterfaceService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(InterfaceService::class.java)

    @Provides
    @Singleton
    fun providesCountryRepository(interfaceService: InterfaceService): NewsRepositoryInterface =
        NewsRepository(interfaceService)

    @Provides
    fun providesIODispatcher(): CoroutineDispatcher = Dispatchers.IO

}