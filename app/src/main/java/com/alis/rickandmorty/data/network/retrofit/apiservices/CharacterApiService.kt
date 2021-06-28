package com.alis.rickandmorty.data.network.retrofit.apiservices

import com.alis.rickandmorty.models.character.Character
import com.alis.rickandmorty.models.common.RickAndMortyResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharacterApiService {

    @GET("/api/character")
    suspend fun fetchCharacters(
        @Query("page") page: Int
    ): RickAndMortyResponse<Character>

    @GET("/api/character/{id}")
    suspend fun fetchCharacter(
        @Path("id") id: Int
    ): Character
}