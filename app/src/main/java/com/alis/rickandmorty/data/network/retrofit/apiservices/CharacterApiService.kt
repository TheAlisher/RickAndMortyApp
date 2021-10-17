package com.alis.rickandmorty.data.network.retrofit.apiservices

import com.alis.rickandmorty.data.network.dtos.RickAndMortyResponse
import com.alis.rickandmorty.data.network.dtos.character.CharacterDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharacterApiService {

    @GET("/api/character")
    suspend fun fetchCharacters(
        @Query("page") page: Int,
        @Query("name") name: String?,
        @Query("status") status: String?,
        @Query("species") species: String?,
        @Query("type") type: String?,
        @Query("gender") gender: String?
    ): RickAndMortyResponse<CharacterDto>

    @GET("/api/character/{id}")
    suspend fun fetchCharacter(
        @Path("id") id: Int
    ): CharacterDto
}