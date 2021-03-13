package com.alis.rickandmorty.di

import com.alis.rickandmorty.data.network.ktor.KtorClient
import com.alis.rickandmorty.data.network.ktor.RickAndMortyRequests
import com.alis.rickandmorty.data.network.retrofit.RetrofitClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideRickAndMortyAPI() = RetrofitClient().provideRickAndMortyAPI()


    @Singleton
    @Provides
    fun provideRickAndMortyRequests() = RickAndMortyRequests(KtorClient().provideClient())
}