package com.example.newsapp.data.model.home

data class MyModel(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)