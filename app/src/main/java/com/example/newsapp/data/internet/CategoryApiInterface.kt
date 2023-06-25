package com.example.newsapp.data.internet

import com.example.newsapp.data.model.BaseResponseModel
import com.example.newsapp.data.model.home.TopNewsModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CategoryApiInterface {

    @GET("top-headlines")
    suspend fun getNewsBasedOnCategory(
        @Query("category") category:String,
        @Query("language") lang:String = "en"
    ) : Response<BaseResponseModel<List<TopNewsModel>>>
}