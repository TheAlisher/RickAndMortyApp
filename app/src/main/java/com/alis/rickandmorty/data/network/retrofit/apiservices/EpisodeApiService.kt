package com.alis.rickandmorty.data.network.retrofit.apiservices

import com.alis.rickandmorty.data.network.dtos.RickAndMortyResponse
import com.alis.rickandmorty.data.network.dtos.episode.EpisodeDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface EpisodeApiService {

    @GET("/api/episode")
    suspend fun fetchEpisodes(
        @Query("page") page: Int,
        @Query("name") name: String?,
        @Query("episode") episode: String?
    ): RickAndMortyResponse<EpisodeDto>

    @GET("/api/episode/{id}")
    suspend fun fetchEpisode(
        @Path("id") id: Int
    ): EpisodeDto
}