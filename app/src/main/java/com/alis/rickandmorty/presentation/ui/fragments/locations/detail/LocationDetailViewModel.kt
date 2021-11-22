package com.alis.rickandmorty.presentation.ui.fragments.locations.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.alis.rickandmorty.common.base.BaseViewModel
import com.alis.rickandmorty.domain.models.location.Location
import com.alis.rickandmorty.domain.usecases.location.FetchLocationUseCase
import com.alis.rickandmorty.presentation.state.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LocationDetailViewModel @Inject constructor(
    private val fetchLocationUseCase: FetchLocationUseCase
) : BaseViewModel() {

    private val _locationState = MutableLiveData<UIState<Location>>()
    val locationState: LiveData<UIState<Location>> = _locationState

    fun fetchLocation(id: Int) {
        subscribeTo(_locationState) {
            fetchLocationUseCase(id)
        }
    }
}