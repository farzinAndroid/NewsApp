package com.example.newsapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.data.internet.NetworkResult
import com.example.newsapp.data.model.home.TopNewsModel
import com.example.newsapp.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repo: HomeRepository) : ViewModel() {


    val topHeadline = MutableStateFlow<NetworkResult<List<TopNewsModel>>>(NetworkResult.Loading())
    val newsBasedOnSource = MutableStateFlow<NetworkResult<List<TopNewsModel>>>(NetworkResult.Loading())
    val newsBasedOnCategory = MutableStateFlow<NetworkResult<List<TopNewsModel>>>(NetworkResult.Loading())


    suspend fun getTopNewsData(source: String) {
        viewModelScope.launch {

            launch {
                topHeadline.emit(repo.getTopHeadlines(source))
            }

        }
    }


    suspend fun getNewsDataBasedOnSource(source: String) {
        viewModelScope.launch {
            launch {
                newsBasedOnSource.emit(repo.getNewsBasedOnSource(source))
            }
        }
    }

}