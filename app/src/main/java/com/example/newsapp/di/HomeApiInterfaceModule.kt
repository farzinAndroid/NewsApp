package com.example.newsapp.di

import com.example.newsapp.data.internet.HomeApiInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HomeApiInterfaceModule {


    @Provides
    @Singleton
    fun provideHomeApiInterfaceModule(retrofit: Retrofit):HomeApiInterface =
        retrofit.create(HomeApiInterface::class.java)

}