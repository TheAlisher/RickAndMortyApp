package com.alis.rickandmorty.domain.usecases.location

import com.alis.rickandmorty.domain.repositories.LocationRepository
import javax.inject.Inject

class FetchLocationsUseCase @Inject constructor(
    private val repository: LocationRepository
) {
    operator fun invoke() = repository.fetchLocations()
}