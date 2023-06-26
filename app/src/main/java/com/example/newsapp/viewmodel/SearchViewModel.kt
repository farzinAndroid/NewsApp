package com.example.newsapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.data.internet.NetworkResult
import com.example.newsapp.data.model.home.TopNewsModel
import com.example.newsapp.repository.SearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val repo: SearchRepository) : ViewModel() {


    val newsBySearch = MutableStateFlow<NetworkResult<List<TopNewsModel>>>(NetworkResult.Loading())


    @OptIn(FlowPreview::class)
    fun getNewsBySearch(q: String) {
        viewModelScope.launch {
            newsBySearch.emit(repo.getNewsBySearch(q))
        }
    }


    fun validateQuery(query: String): Boolean = query.length >= 3

}