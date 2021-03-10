package com.alis.rickandmorty.di

import com.alis.rickandmorty.data.network.RetrofitClient
import com.alis.rickandmorty.data.network.RickAndMortyAPI
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
    fun provideRickAndMortyAPI(): RickAndMortyAPI {
        return RetrofitClient().provideRickAndMortyAPI()
    }
}