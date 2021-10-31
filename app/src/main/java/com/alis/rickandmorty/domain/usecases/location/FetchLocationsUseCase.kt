package com.alis.rickandmorty.domain.usecases.location

import com.alis.rickandmorty.domain.repositories.LocationRepository
import javax.inject.Inject

class FetchLocationsUseCase @Inject constructor(
    private val repository: LocationRepository
) {
    operator fun invoke(
        name: String? = null,
        type: String? = null,
        dimension: String? = null
    ) = repository.fetchLocations(
        name,
        type,
        dimension
    )
}