package com.example.newsapp.repository

import com.example.newsapp.data.internet.BaseApiCall
import com.example.newsapp.data.internet.HomeApiInterface
import com.example.newsapp.data.internet.NetworkResult
import javax.inject.Inject

class HomeRepository @Inject constructor(private val api: HomeApiInterface) : BaseApiCall() {

    suspend fun getTopHeadlines(source: String) =
        safeApiCall {
            api.getTopHeadlines(source)
        }

    suspend fun getNewsBasedOnSource(source: String) =
        safeApiCall {
            api.getNewsBasedOnSource(source)
        }

}