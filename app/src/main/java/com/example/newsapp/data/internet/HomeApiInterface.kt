package com.example.newsapp.data.internet

import com.example.newsapp.data.model.BaseResponseModel
import com.example.newsapp.data.model.home.TopNewsModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface HomeApiInterface {

    @GET("top-headlines")
    suspend fun getTopHeadlines(
        @Query("sources") source:String
    ) : Response<BaseResponseModel<List<TopNewsModel>>>

    @GET("everything")
    suspend fun getNewsBasedOnSource(
        @Query("sources") source:String
    ) : Response<BaseResponseModel<List<TopNewsModel>>>

}