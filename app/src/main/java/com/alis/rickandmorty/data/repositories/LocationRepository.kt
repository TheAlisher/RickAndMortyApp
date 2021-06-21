package com.alis.rickandmorty.data.repositories

import androidx.lifecycle.liveData
import com.alis.rickandmorty.data.network.retrofit.apiservices.LocationApiService
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class LocationRepository @Inject constructor(
    private val locationApiService: LocationApiService
) {

    fun fetchLocations() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = locationApiService.fetchLocations()))
        } catch (E: Exception) {
            emit(Resource.error(data = null, message = E.message ?: "Error Occurred!"))
        }
    }
}