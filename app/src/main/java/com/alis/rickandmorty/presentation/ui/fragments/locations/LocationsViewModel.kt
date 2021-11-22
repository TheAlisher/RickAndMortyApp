package com.alis.rickandmorty.presentation.ui.fragments.locations

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.alis.rickandmorty.common.base.BaseViewModel
import com.alis.rickandmorty.domain.usecases.location.FetchLocationsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LocationsViewModel @Inject constructor(
    private val fetchLocationsUseCase: FetchLocationsUseCase,
) : BaseViewModel() {

    fun fetchLocations() = fetchLocationsUseCase().cachedIn(viewModelScope)
}