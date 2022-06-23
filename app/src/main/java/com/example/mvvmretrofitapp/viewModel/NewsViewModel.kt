package com.example.mvvmretrofitapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mvvmretrofitapp.network.NewsRepositoryInterface
import com.example.mvvmretrofitapp.utils.ResponseStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsRepository: NewsRepositoryInterface,
    private val ioDispatcher: CoroutineDispatcher
) : BaseViewModel() {
    private val _news: MutableLiveData<ResponseStatus> = MutableLiveData(ResponseStatus.LOADING())
    val news: LiveData<ResponseStatus> get() = _news

    fun subscribeToNews() {
        _news.postValue(ResponseStatus.LOADING())
        getAllNews()
        viewModelSafeScope.launch {
            newsRepository.getAllNews()
        }
    }

    /******
     *  Get all news.
     */
    fun getAllNews() {
        viewModelSafeScope.launch {
            newsRepository.nnewsresponse.collect {
                _news.postValue(it)
            }
        }
        viewModelSafeScope.launch(ioDispatcher) {
            newsRepository.getAllNews()
        }
    }
}