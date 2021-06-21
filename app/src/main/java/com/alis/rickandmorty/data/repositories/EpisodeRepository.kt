package com.alis.rickandmorty.data.repositories

import com.alis.rickandmorty.base.BaseRepository
import com.alis.rickandmorty.data.network.retrofit.apiservices.EpisodeApiService
import javax.inject.Inject

class EpisodeRepository @Inject constructor(
    private val episodeApiService: EpisodeApiService
) : BaseRepository() {

    fun fetchEpisodes() = doRequest {
        episodeApiService.fetchEpisodes()
    }
}