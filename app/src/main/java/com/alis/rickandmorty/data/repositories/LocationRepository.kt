package com.alis.rickandmorty.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.alis.rickandmorty.base.BaseRepository
import com.alis.rickandmorty.data.network.retrofit.apiservices.LocationApiService
import com.alis.rickandmorty.data.repositories.pagingsources.LocationPagingSource
import com.alis.rickandmorty.models.location.Location
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocationRepository @Inject constructor(
    private val locationApiService: LocationApiService
) : BaseRepository() {

    fun fetchLocations(): Flow<PagingData<Location>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                LocationPagingSource(locationApiService)
            }
        ).flow
    }
}