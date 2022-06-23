package com.example.mvvmretrofitapp.network

import com.example.mvvmretrofitapp.model.News
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface InterfaceService {

    @GET(NEWS)
    suspend fun getNews(@Query("category") category: String = "science"): Response<News>

    //https://inshorts.deta.dev/news?category=science
    companion object {
        const val BASE_URL = "https://inshorts.deta.dev/"
        private const val NEWS = "news"
    }
}