package com.example.newsapp.data.model.home

data class TopNewsModel(
    val source:SourceApiModel,
    val author:String,
    val title:String,
    val description:String,
    val url:String,
    val urlToImage:String,
    val publishedAt:String,
    val content:String
)