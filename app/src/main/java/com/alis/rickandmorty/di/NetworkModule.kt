package com.alis.rickandmorty.di

import com.alis.rickandmorty.data.network.ktor.EpisodeApiService
import com.alis.rickandmorty.data.network.ktor.KtorClient
import com.alis.rickandmorty.data.network.retrofit.RetrofitClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofitClient() = RetrofitClient()

    @Singleton
    @Provides
    fun provideKtor() = KtorClient().provideClient()

    @Singleton
    @Provides
    fun provideRetrofit(
        retrofitClient: RetrofitClient
    ): Retrofit = retrofitClient.provideRetrofit

    @Singleton
    @Provides
    fun provideCharacterApiService(
        retrofitClient: RetrofitClient, retrofit: Retrofit
    ) = retrofitClient.provideCharacterApiService(retrofit)

    @Singleton
    @Provides
    fun provideLocationApiService(
        retrofitClient: RetrofitClient, retrofit: Retrofit
    ) = retrofitClient.provideLocationApiService(retrofit)

    @Singleton
    @Provides
    fun provideEpisodeApiService(
        client: HttpClient
    ) = EpisodeApiService(client)
}