package com.alis.rickandmorty.data.repositories

import androidx.lifecycle.liveData
import com.alis.rickandmorty.data.network.Resource
import com.alis.rickandmorty.data.network.RickAndMortyAPI
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class RickAndMortyRepository @Inject constructor(
    private val api: RickAndMortyAPI
) {

    fun fetchCharacters() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = api.fetchCharacters()))
        } catch (E: Exception) {
            emit(Resource.error(data = null, message = E.message ?: "Error Occurred!"))
        }
    }

    fun fetchLocations() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = api.fetchLocations()))
        } catch (E: Exception) {
            emit(Resource.error(data = null, message = E.message ?: "Error Occurred!"))
        }
    }

    fun fetchEpisodes() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = api.fetchEpisodes()))
        } catch (E: Exception) {
            emit(Resource.error(data = null, message = E.message ?: "Error Occurred!"))
        }
    }
}