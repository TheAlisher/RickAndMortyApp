package com.alis.rickandmorty.data.network.apiservices

import com.alis.rickandmorty.data.network.dtos.RickAndMortyResponse
import com.alis.rickandmorty.data.network.dtos.location.LocationDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface LocationApiService {

    @GET("/api/location")
    suspend fun fetchLocations(
        @Query("page") page: Int,
        @Query("name") name: String?,
        @Query("type") type: String?,
        @Query("dimension") dimension: String?
    ): RickAndMortyResponse<LocationDto>

    @GET("/api/location/{id}")
    suspend fun fetchLocation(
        @Path("id") id: Int
    ): LocationDto
}