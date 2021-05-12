package com.alis.rickandmorty.data.repositories

import androidx.lifecycle.liveData
import com.alis.rickandmorty.data.network.Resource
import com.alis.rickandmorty.data.network._Resource
import com.alis.rickandmorty.data.network.ktor.EpisodeApiService
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class EpisodeRepository @Inject constructor(
    private val episodeApiService: EpisodeApiService
) {

    fun fetchEpisodes() = liveData(Dispatchers.IO) {
        emit(_Resource.Loading())
        try {
            emit(_Resource.Success(data = episodeApiService.fetchEpisodes()))
        } catch (E: Exception) {
            emit(_Resource.Error(data = null, message = E.message ?: "Error Occurred!"))
        }
    }
}