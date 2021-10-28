package com.alis.rickandmorty.presentation.ui.fragments.locations

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.alis.rickandmorty.base.BaseViewModel
import com.alis.rickandmorty.domain.usecases.location.FetchLocationUseCase
import com.alis.rickandmorty.domain.usecases.location.FetchLocationsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LocationsViewModel @Inject constructor(
    private val fetchLocationsUseCase: FetchLocationsUseCase,
    private val fetchLocationUseCase: FetchLocationUseCase
) : BaseViewModel() {

    fun fetchLocations() = fetchLocationsUseCase().cachedIn(viewModelScope)

    fun fetchLocation(id: Int) = fetchLocationUseCase(id)
}