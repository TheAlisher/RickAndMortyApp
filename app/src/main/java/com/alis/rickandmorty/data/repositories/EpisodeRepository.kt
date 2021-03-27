package com.alis.rickandmorty.data.repositories

import androidx.lifecycle.liveData
import com.alis.rickandmorty.data.network.Resource
import com.alis.rickandmorty.data.network.ktor.EpisodeApiService
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class EpisodeRepository @Inject constructor(
    private val episodeApiService: EpisodeApiService
) {

    fun fetchEpisodes() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = episodeApiService.fetchEpisodes()))
        } catch (E: Exception) {
            emit(Resource.error(data = null, message = E.message ?: "Error Occurred!"))
        }
    }
}