package com.alis.rickandmorty.data.network.retrofit.apiservices

import com.alis.rickandmorty.models.common.RickAndMortyResponse
import com.alis.rickandmorty.models.episode.Episode
import retrofit2.http.GET

interface EpisodeApiService {

    @GET("api/episode")
    suspend fun fetchEpisodes(): RickAndMortyResponse<Episode>
}