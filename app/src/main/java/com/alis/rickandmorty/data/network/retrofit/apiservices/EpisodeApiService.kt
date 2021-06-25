package com.alis.rickandmorty.data.network.retrofit.apiservices

import com.alis.rickandmorty.models.common.RickAndMortyResponse
import com.alis.rickandmorty.models.episode.Episode
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface EpisodeApiService {

    @GET("api/episode")
    suspend fun fetchEpisodes(
        @Query("page") page: Int
    ): Response<RickAndMortyResponse<Episode>>
}