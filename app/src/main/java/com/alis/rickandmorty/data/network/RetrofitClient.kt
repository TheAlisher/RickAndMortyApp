package com.alis.rickandmorty.data.network

import com.alis.rickandmorty.common.constants.Constants
import com.alis.rickandmorty.data.network.interceptors.LoggingInterceptor
import com.alis.rickandmorty.data.network.apiservices.CharacterApiService
import com.alis.rickandmorty.data.network.apiservices.EpisodeApiService
import com.alis.rickandmorty.data.network.apiservices.LocationApiService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitClient {

    private val okHttpClient: OkHttpClient = OkHttpClient()
        .newBuilder()
        .addInterceptor(LoggingInterceptor().provideLoggingInterceptor())
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()

    private val provideRetrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun provideCharacterApiService(): CharacterApiService = provideRetrofit
        .create(CharacterApiService::class.java)

    fun provideLocationApiService(): LocationApiService = provideRetrofit
        .create(LocationApiService::class.java)

    fun provideEpisodeApiService(): EpisodeApiService = provideRetrofit
        .create(EpisodeApiService::class.java)
}