package com.alis.rickandmorty.data.network.retrofit

import com.alis.rickandmorty.constants.Constants
import com.alis.rickandmorty.data.network.okhttp.interceptors.LoggingInterceptor
import com.alis.rickandmorty.data.network.retrofit.apiservices.CharacterApiService
import com.alis.rickandmorty.data.network.retrofit.apiservices.LocationApiService
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
}