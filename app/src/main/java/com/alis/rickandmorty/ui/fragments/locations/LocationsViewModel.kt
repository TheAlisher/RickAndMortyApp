package com.alis.rickandmorty.ui.fragments.locations

import androidx.lifecycle.LiveData
import com.alis.rickandmorty.base.BaseViewModel
import com.alis.rickandmorty.data.network.Resource
import com.alis.rickandmorty.data.repositories.RickAndMortyRepository
import com.alis.rickandmorty.models.common.RickAndMortyResponse
import com.alis.rickandmorty.models.location.Location
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class LocationsViewModel @Inject constructor(
    private val repository: RickAndMortyRepository
) : BaseViewModel() {

    fun fetchLocations(): LiveData<Resource<Response<RickAndMortyResponse<Location>>>> {
        return repository.fetchLocations()
    }
}