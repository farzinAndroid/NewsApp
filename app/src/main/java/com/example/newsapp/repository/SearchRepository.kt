package com.example.newsapp.repository

import com.example.newsapp.data.internet.BaseApiCall
import com.example.newsapp.data.internet.CategoryApiInterface
import com.example.newsapp.data.internet.SearchApiInterface
import javax.inject.Inject

class SearchRepository @Inject constructor(private val api: SearchApiInterface) : BaseApiCall() {

    suspend fun getNewsBySearch(q: String) =
        safeApiCall {
            api.getNewsBySearch(q)
        }

}