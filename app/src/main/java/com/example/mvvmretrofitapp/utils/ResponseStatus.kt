package com.example.mvvmretrofitapp.utils

import com.example.mvvmretrofitapp.model.News

sealed interface ResponseStatus {
    class SUCCESS(val news: News): ResponseStatus
    class ERROR(val error: Throwable): ResponseStatus
    class LOADING(val isLoading: Boolean = true) : ResponseStatus
}

