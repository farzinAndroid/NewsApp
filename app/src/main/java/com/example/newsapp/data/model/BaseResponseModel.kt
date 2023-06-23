package com.example.newsapp.data.model

data class BaseResponseModel<T>(
    val status:String,
    val totalResult:Int,
    val articles:T
)