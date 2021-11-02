package com.alis.rickandmorty.domain.repositories

import androidx.paging.PagingData
import com.alis.rickandmorty.common.resource.Resource
import com.alis.rickandmorty.domain.models.location.Location
import kotlinx.coroutines.flow.Flow

interface LocationRepository {

    // TODO; PagingData cannot be in domain layer
    fun fetchLocations(
        name: String? = null,
        type: String? = null,
        dimension: String? = null
    ): Flow<PagingData<Location>>

    fun fetchLocation(id: Int): Flow<Resource<Location>>
}