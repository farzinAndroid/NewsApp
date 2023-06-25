package com.example.newsapp.repository

import com.example.newsapp.data.internet.BaseApiCall
import com.example.newsapp.data.internet.CategoryApiInterface
import javax.inject.Inject

class CategoryRepository @Inject constructor(private val api: CategoryApiInterface) : BaseApiCall() {

    suspend fun getNewsBasedOnCategory(category: String) =
        safeApiCall {
            api.getNewsBasedOnCategory(category)
        }

}