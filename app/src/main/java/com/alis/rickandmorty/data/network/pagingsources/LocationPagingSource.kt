package com.alis.rickandmorty.data.network.pagingsources

import com.alis.rickandmorty.common.base.BasePagingSource
import com.alis.rickandmorty.data.network.dtos.location.toLocation
import com.alis.rickandmorty.data.network.apiservices.LocationApiService
import com.alis.rickandmorty.data.network.dtos.location.LocationDto
import com.alis.rickandmorty.domain.models.location.Location

class LocationPagingSource(
    private val service: LocationApiService,
    private val name: String?,
    private val type: String?,
    private val dimension: String?
) : BasePagingSource<LocationDto, Location>(
    { service.fetchLocations(it, name, type, dimension) },
    { data -> data.map { it.toLocation() } }
)