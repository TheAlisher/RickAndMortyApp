package com.alis.rickandmorty.data.network.retrofit.apiservices

import com.alis.rickandmorty.models.common.RickAndMortyResponse
import com.alis.rickandmorty.models.location.Location
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface LocationApiService {

    @GET("/api/location")
    suspend fun fetchLocations(
        @Query("page") page: Int
    ): RickAndMortyResponse<Location>

    @GET("/api/location/{id}")
    suspend fun fetchLocation(
        @Path("id") id: Int
    ): Location
}