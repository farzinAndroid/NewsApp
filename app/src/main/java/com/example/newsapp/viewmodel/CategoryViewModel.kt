package com.example.newsapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.data.internet.NetworkResult
import com.example.newsapp.data.model.home.TopNewsModel
import com.example.newsapp.repository.CategoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(private val repo: CategoryRepository) : ViewModel() {


    val newsBasedOnCategory = MutableStateFlow<NetworkResult<List<TopNewsModel>>>(NetworkResult.Loading())

    suspend fun getNewsBasedOnCategory(category: String) {
        viewModelScope.launch {
            launch {
                newsBasedOnCategory.emit(repo.getNewsBasedOnCategory(category))
            }
        }
    }

}