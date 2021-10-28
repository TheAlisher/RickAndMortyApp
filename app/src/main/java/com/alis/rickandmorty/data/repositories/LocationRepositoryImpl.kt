package com.alis.rickandmorty.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.alis.rickandmorty.base.BaseRepository
import com.alis.rickandmorty.data.network.dtos.location.toLocation
import com.alis.rickandmorty.data.network.apiservices.LocationApiService
import com.alis.rickandmorty.data.network.pagingsources.LocationPagingSource
import com.alis.rickandmorty.domain.repositories.LocationRepository
import com.alis.rickandmorty.domain.models.location.Location
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocationRepositoryImpl @Inject constructor(
    private val service: LocationApiService
) : BaseRepository(), LocationRepository {

    override fun fetchLocations(
        name: String?,
        type: String?,
        dimension: String?
    ): Flow<PagingData<Location>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                LocationPagingSource(service, name, type, dimension)
            }
        ).flow
    }

    override fun fetchLocation(id: Int) = doRequest {
        service.fetchLocation(id).toLocation()
    }
}