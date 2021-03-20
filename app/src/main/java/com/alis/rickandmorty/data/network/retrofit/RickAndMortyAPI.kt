package com.alis.rickandmorty.data.network.retrofit

import com.alis.rickandmorty.models.character.Character
import com.alis.rickandmorty.models.common.RickAndMortyResponse
import com.alis.rickandmorty.models.episode.Episode
import com.alis.rickandmorty.models.location.Location
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RickAndMortyAPI {

    @GET("/api/character")
    suspend fun fetchCharacters(@Query("page") page: Int): Response<RickAndMortyResponse<Character>>

    @GET("/api/location")
    suspend fun fetchLocations(): Response<RickAndMortyResponse<Location>>

    @GET("/api/episode")
    suspend fun fetchEpisodes(): Response<RickAndMortyResponse<Episode>>
}