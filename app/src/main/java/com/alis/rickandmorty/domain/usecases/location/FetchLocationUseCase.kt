package com.alis.rickandmorty.domain.usecases.location

import com.alis.rickandmorty.domain.repositories.LocationRepository
import javax.inject.Inject

class FetchLocationUseCase @Inject constructor(
    private val repository: LocationRepository
) {
    operator fun invoke(id: Int) = repository.fetchLocation(id)
}