package com.alis.rickandmorty.data.repositories

import com.alis.rickandmorty.base.BaseRepository
import com.alis.rickandmorty.data.network.retrofit.apiservices.LocationApiService
import javax.inject.Inject

class LocationRepository @Inject constructor(
    private val locationApiService: LocationApiService
) : BaseRepository() {

    fun fetchLocations() = doRequest {
        locationApiService.fetchLocations()
    }
}