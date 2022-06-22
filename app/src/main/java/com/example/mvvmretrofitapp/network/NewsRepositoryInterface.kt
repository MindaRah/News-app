package com.example.mvvmretrofitapp.network

import com.example.mvvmretrofitapp.utils.NullResponseException
import com.example.mvvmretrofitapp.utils.ResponseIsAFailure
import com.example.mvvmretrofitapp.utils.ResponseStatus
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface NewsRepositoryInterface {
    val nnewsresponse: StateFlow<ResponseStatus>
    suspend fun getAllNews()
}

//API
class NewsRepository @Inject constructor(private val interfaceService: InterfaceService) :
    NewsRepositoryInterface {

    val newsstate: MutableStateFlow<ResponseStatus> = MutableStateFlow<ResponseStatus>(ResponseStatus.LOADING())
    override val nnewsresponse: StateFlow<ResponseStatus>
        get() = newsstate


    override suspend fun getAllNews()  {
        newsstate.value=ResponseStatus.LOADING()
        try {
            val response = interfaceService.getNews()
            if (response.isSuccessful) {
                response.body()?.let {
                    newsstate.value=(ResponseStatus.SUCCESS(it))
                } ?: throw NullResponseException()
            } else {
                throw ResponseIsAFailure()
            }
        } catch (e: Exception) {
            newsstate.value=(ResponseStatus.ERROR(e))
        }
    }
}