package com.alis.rickandmorty.data.repositories

import com.alis.rickandmorty.common.base.BaseRepository
import com.alis.rickandmorty.data.network.dtos.location.toLocation
import com.alis.rickandmorty.data.network.apiservices.LocationApiService
import com.alis.rickandmorty.data.network.pagingsources.LocationPagingSource
import com.alis.rickandmorty.domain.repositories.LocationRepository
import javax.inject.Inject

class LocationRepositoryImpl @Inject constructor(
    private val service: LocationApiService
) : BaseRepository(), LocationRepository {

    override fun fetchLocations(
        name: String?,
        type: String?,
        dimension: String?
    ) = doPagingRequest(
        LocationPagingSource(service, name, type, dimension),
    )

    override fun fetchLocation(id: Int) = doRequest {
        service.fetchLocation(id).toLocation()
    }
}