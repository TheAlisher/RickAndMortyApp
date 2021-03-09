package com.alis.rickandmorty.data.network

import com.alis.rickandmorty.models.character.Character
import com.alis.rickandmorty.models.common.RickAndMortyResponse
import com.alis.rickandmorty.models.episode.Episode
import com.alis.rickandmorty.models.location.Location
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RickAndMortyAPI {

    @GET("/character")
    suspend fun fetchCharacters(): Response<RickAndMortyResponse<Character>>

    @GET("/location")
    suspend fun fetchLocations(): Response<RickAndMortyResponse<Location>>

    @GET("/episode")
    suspend fun fetchEpisodes(): Response<RickAndMortyResponse<Episode>>
}