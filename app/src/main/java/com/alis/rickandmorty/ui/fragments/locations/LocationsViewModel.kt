package com.alis.rickandmorty.ui.fragments.locations

import com.alis.rickandmorty.base.BaseViewModel
import com.alis.rickandmorty.data.repositories.LocationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LocationsViewModel @Inject constructor(
    private val repository: LocationRepository
) : BaseViewModel() {

    fun fetchLocations() = repository.fetchLocations()

    fun fetchLocation(id: Int) = repository.fetchLocation(id)
}