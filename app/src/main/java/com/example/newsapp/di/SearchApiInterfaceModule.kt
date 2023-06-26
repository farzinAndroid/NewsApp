package com.example.newsapp.di

import com.example.newsapp.data.internet.HomeApiInterface
import com.example.newsapp.data.internet.SearchApiInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SearchApiInterfaceModule {


    @Provides
    @Singleton
    fun provideSearchApiInterfaceModule(retrofit: Retrofit):SearchApiInterface =
        retrofit.create(SearchApiInterface::class.java)

}