package com.alis.rickandmorty.ui.fragments.locations

import androidx.paging.PagingData
import com.alis.rickandmorty.base.BaseViewModel
import com.alis.rickandmorty.data.repositories.LocationRepository
import com.alis.rickandmorty.models.location.Location
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class LocationsViewModel @Inject constructor(
    private val repository: LocationRepository
) : BaseViewModel() {

    fun fetchLocations(): Flow<PagingData<Location>> {
        return repository.fetchLocations()
    }
}